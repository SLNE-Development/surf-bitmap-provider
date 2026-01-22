package dev.slne.surf.bitmap.provider.placeholders

import dev.slne.surf.bitmap.common.provider.BitmapProvider
import io.github.miniplaceholders.api.Expansion
import io.github.miniplaceholders.api.provider.ExpansionProvider
import io.github.miniplaceholders.api.provider.LoadRequirement
import io.github.miniplaceholders.api.types.Platform
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.ShadowColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.minimessage.tag.Tag

class BitmapExpansionProvider : ExpansionProvider {
    override fun provideExpansion(): Expansion = Expansion.builder("bitmap")
        .author("red")
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
        }.build()

    override fun loadRequirement(): LoadRequirement = LoadRequirement.none()
}