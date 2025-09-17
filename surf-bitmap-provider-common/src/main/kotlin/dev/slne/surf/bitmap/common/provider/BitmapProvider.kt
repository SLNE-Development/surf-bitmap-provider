package dev.slne.surf.bitmap.common.provider

import dev.slne.surf.bitmap.common.provider.providers.AlphabetProvider
import dev.slne.surf.bitmap.common.provider.providers.NumberProvider
import dev.slne.surf.bitmap.common.provider.providers.UnknownProvider
import dev.slne.surf.bitmap.common.provider.providers.UtilityProvider
import dev.slne.surf.bitmap.common.utils.BITMAP_BACKGROUND
import dev.slne.surf.bitmap.common.utils.Spacing
import dev.slne.surf.bitmap.common.utils.calculateGlyphSpacing
import dev.slne.surf.surfapi.core.api.messages.adventure.buildText
import dev.slne.surf.surfapi.core.api.messages.builder.SurfComponentBuilder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.ShadowColor
import net.kyori.adventure.text.format.TextColor
import java.util.*

/**
 * Provides functionalities for mapping input strings into visual representations of characters using character
 * providers. It supports translating input text to graphical components or serialized string representations
 * with customizable foreground and background colors.
 */
object BitmapProvider {

    private val providers = LinkedList<Provider>().apply {
        add(AlphabetProvider)
        add(NumberProvider)
        add(UtilityProvider)
        add(UnknownProvider)
    }

    /**
     * Translates the input string into a text component representation, where each character
     * is mapped to a styled component with the specified foreground and background colors.
     *
     * @param input The string to be translated into a text component.
     * @param foregroundColor The foreground color to apply to the characters in the text component.
     * @param backgroundColor The background color to apply behind the characters in the text component.
     * @param shadowColor The shadow color to apply to the characters in the text component.
     * @param affixAmount The number of affix characters to add on each side of the text component for padding.
     *
     * @return A text component representing the input string with the specified styling.
     */
    fun translateToComponent(
        input: String,
        foregroundColor: TextColor,
        backgroundColor: TextColor,
        shadowColor: ShadowColor = ShadowColor.none(),
        affixAmount: Int = 2
    ): Component {
        val input = input.lowercase()
        val translated = translateToCharProviders(input)
        val background = translated.map { it.generateBackground() }
        val glyphs = translated.map { it.char }

        val backgroundString = background.joinToString(Spacing.NEGATIVE_SPACE_ONE.char.toString())
            .dropLast(2)

        return buildText {
            append {
                appendAffix(affixAmount, true)
                text(backgroundString)
                appendAffix(affixAmount, false)

                color(backgroundColor)
            }

            append {
                text(-translated.sumOf { it.width } - affixAmount)
                text(glyphs.joinToString(""))

                color(foregroundColor)
                shadowColor(shadowColor)
            }

            text(calculateGlyphSpacing(affixAmount))
        }
    }
    
    private fun translateOneChar(char: Char) =
        providers.firstNotNullOfOrNull { it.findChar(char) }

    private fun translateToCharProviders(input: String): LinkedList<CharProvider> {
        val list = LinkedList<CharProvider>()

        for (char in input) {
            val provider = translateOneChar(char)

            if (provider != null) {
                list.add(provider)
            }
        }

        return list
    }

    private fun SurfComponentBuilder.appendAffix(
        amount: Int,
        prefix: Boolean,
    ) {
        repeat(amount) {
            if (prefix) {
                append {
                    text(BITMAP_BACKGROUND)
                    text(Spacing.NEGATIVE_SPACE_ONE.char)
                }
            } else {
                append {
                    text(Spacing.NEGATIVE_SPACE_ONE.char)
                    text(BITMAP_BACKGROUND)
                }
            }
        }
    }

}