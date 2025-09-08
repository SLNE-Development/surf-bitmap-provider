package dev.slne.surf.bitmap.common.head

import com.github.benmanes.caffeine.cache.Caffeine
import dev.slne.surf.bitmap.common.head.image.getFirstHeadPixelsFromImage
import dev.slne.surf.bitmap.common.head.image.getImageFromUrl
import dev.slne.surf.bitmap.common.head.image.getSecondHeadPixelsFromImage
import dev.slne.surf.bitmap.common.head.texture.decodeTextureString
import dev.slne.surf.bitmap.common.head.texture.getTextureStringByUuid
import dev.slne.surf.bitmap.common.utils.Pixels
import dev.slne.surf.bitmap.common.utils.calculateGlyphSpacing
import dev.slne.surf.surfapi.core.api.messages.adventure.buildText
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.ShadowColor
import net.kyori.adventure.text.format.TextColor
import java.net.URI
import java.util.*

private val headPixelCache =
    Caffeine.newBuilder().build<UUID, HeadResponse> { uuid ->
        val textures = getTextureStringByUuid(uuid) ?: TODO("Implement steves")
        val textureProperty = decodeTextureString(textures)
        val skinUrl = textureProperty.textures.skin?.url ?: TODO("Implement steves")
        val skinImage = getImageFromUrl(URI.create(skinUrl).toURL())

        val firstLayer = getFirstHeadPixelsFromImage(skinImage)
        val secondLayer = getSecondHeadPixelsFromImage(skinImage)

        HeadResponse(firstLayer, secondLayer)
    }

fun composeHead(rows: List<Component>, text: List<Component> = listOf()) = buildText {
    rows.forEachIndexed { i, row ->
        if (i > 0) {
            appendNewline()
        }

        append(row)

        val textComponent = text.getOrNull(i)

        if (textComponent != null) {
            append(textComponent)
        }
    }
}

fun getHeadRowsByUuid(uuid: UUID, scale: Int = 1): List<Component> {
    val (firstLayer, secondLayer) = headPixelCache.get(uuid)

    return getHeadRowsByLayers(listOf(firstLayer, secondLayer), scale)
}

private fun mergeHeadLayers(layers: List<HeadArray>): HeadArray {
    val merged = Array(HEAD_SIZE) { Array<TextColor?>(HEAD_SIZE) { null } }

    for (layer in layers) {
        for (y in 0 until HEAD_SIZE) {
            for (x in 0 until HEAD_SIZE) {
                val color = layer[x][y]

                if (color != null) {
                    merged[x][y] = color
                }
            }
        }
    }

    return merged
}

fun getHeadRowsByLayers(
    layers: List<HeadArray>,
    scale: Int = 1
): List<Component> {
    val array = mergeHeadLayers(layers)
    val rows = mutableListOf<Component>()

    for (y in 0 until HEAD_SIZE) {
        val row = buildText {
            for (x in 0 until HEAD_SIZE) {
                val color = array[x][y]

                repeat(scale) {
                    text(calculateGlyphSpacing(-1))

                    if (color == null) {
                        text(calculateGlyphSpacing(9))
                    } else {
                        append(coloredPixel(color))
                    }
                }
            }
        }

        repeat(scale) {
            rows.add(row)
        }
    }

    return rows
}


private fun coloredPixel(color: TextColor, applyShadow: Boolean = true) = buildText {
    text(Pixels.PIXEL_9.char, color)

    if (applyShadow) {
        shadowColor(ShadowColor.none())
    }
}