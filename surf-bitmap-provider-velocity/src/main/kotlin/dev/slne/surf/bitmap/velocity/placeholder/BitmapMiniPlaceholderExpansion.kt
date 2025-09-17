package dev.slne.surf.bitmap.velocity.placeholder

import dev.slne.surf.bitmap.common.provider.BitmapProvider
import io.github.miniplaceholders.api.Expansion
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.ShadowColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.minimessage.tag.Tag

val BitmapMiniPlaceholderExpansion = Expansion.builder("bitmap")
    .globalPlaceholder("translate") { queue, ctx ->
        val foregroundColorInput = queue.popOr { "foreground is required" }.value()
        val shadowColorInput = queue.popOr { "shadow is required" }.value()
        val backgroundColorInput = queue.popOr { "background is required" }.value()
        val affixAmount = queue.popOr { "affix amount is required" }.asInt().asInt
        val input = buildString {
            while (queue.hasNext()) {
                append(queue.pop().value())

                if (queue.hasNext()) append(" ")
            }
        }

        val foregroundColor = TextColor.fromHexString(foregroundColorInput) ?: NamedTextColor.WHITE
        val shadowColor = ShadowColor.fromHexString(shadowColorInput) ?: ShadowColor.none()
        val backgroundColor = TextColor.fromHexString(backgroundColorInput) ?: NamedTextColor.BLACK

        Tag.selfClosingInserting(
            BitmapProvider.translateToComponent(
                input,
                foregroundColor,
                backgroundColor,
                shadowColor,
                affixAmount
            )
        )
    }