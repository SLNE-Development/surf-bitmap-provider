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
import net.kyori.adventure.text.format.ShadowColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.minimessage.MiniMessage
import java.util.*

/**
 * Provides functionalities for mapping input strings into visual representations of characters using character
 * providers. It supports translating input text to graphical components or serialized string representations
 * with customizable foreground and background colors.
 */
object BitmapProvider {

    /**
     * A list of character providers that are responsible for mapping input characters to their
     * respective `CharProvider` instances. This collection includes various predefined providers
     * such as `AlphabetProvider`, `NumberProvider`, `UtilityProvider`, and `UnknownProvider`.
     *
     * These providers are used to resolve characters to their appropriate representations,
     * allowing for flexible and extensible character translation mechanisms.
     */
    private val providers = LinkedList<Provider>().apply {
        add(AlphabetProvider)
        add(NumberProvider)
        add(UtilityProvider)
        add(UnknownProvider)
    }

    /**
     * Translates a single character into its corresponding `CharProvider` using available providers.
     *
     * @param char The character to be translated into a `CharProvider`.
     * @return The `CharProvider` corresponding to the given character, or `null` if no match is found.
     */
    private fun translateOneChar(char: Char) = providers.firstNotNullOfOrNull { it.findChar(char) }

    /**
     * Translates an input string into a list of `CharProvider` instances, where each character
     * in the string is mapped to its corresponding `CharProvider` representation if available.
     *
     * @param input The string to be translated into a list of `CharProvider` instances.
     *              Each character is individually analyzed and potentially mapped.
     * @return A `LinkedList` of `CharProvider` instances corresponding to the characters
     *         in the input string. Characters without a matching `CharProvider` are ignored.
     */
    fun translateToCharProviders(input: String): LinkedList<CharProvider> {
        val list = LinkedList<CharProvider>()

        for (char in input) {
            val provider = translateOneChar(char)

            if (provider != null) {
                list.add(provider)
            }
        }

        return list
    }

    /**
     * Translates the input string into a text component representation, where each character
     * is mapped to a styled component with the specified foreground and background colors.
     *
     * @param input The string to be translated into a text component.
     * @param foregroundColor The foreground color to apply to the characters in the text component.
     * @param backgroundColor The background color to apply behind the characters in the text component.
     * @param affixAmount The number of affix characters to add on each side of the text component for padding.
     *
     * @return A text component representing the input string with the specified styling.
     */
    fun translateToComponent(
        input: String,
        foregroundColor: TextColor,
        backgroundColor: TextColor,
        affixAmount: Int = 1
    ) = buildText {
        val translated = translateToCharProviders(input)
        val background = translated.map { it.generateBackground() }
        val glyphs = translated.map { it.char }
        val backshift =
            calculateGlyphSpacing(-translated.sumOf { it.width } - 1 - affixAmount)

        append {
            appendAffix(affixAmount, true, backgroundColor)
            text(background.joinToString(Spacing.NEGATIVE_SPACE_ONE.char.toString()))
            appendAffix(affixAmount, false, backgroundColor)

            color(backgroundColor)
        }

        append {
            text(backshift)
            text(glyphs.joinToString(Spacing.NEGATIVE_SPACE_ONE.char.toString()))

            color(foregroundColor)
            shadowColor(ShadowColor.none())
        }

        text("<reset>")
    }

    private fun SurfComponentBuilder.appendAffix(
        amount: Int,
        prefix: Boolean,
        backgroundColor: TextColor
    ) {
        repeat(amount) {
            if (prefix) {
                append {
                    text(BITMAP_BACKGROUND)
                    text(Spacing.NEGATIVE_SPACE_ONE.char)

                    color(backgroundColor)
                }
            } else {
                append {
                    text(Spacing.NEGATIVE_SPACE_ONE.char)
                    text(BITMAP_BACKGROUND)

                    color(backgroundColor)
                }
            }
        }
    }

    /**
     * Converts an input string into its serialized representation as a text component,
     * applying specified foreground and background colors to the characters.
     *
     * @param input The input string that will be translated into a serialized string.
     * @param foregroundColor The color to be applied as the foreground for the text.
     * @param backgroundColor The color to be applied as the background for the text.
     * @param affixAmount The number of affix characters to add on each side of the text for padding.
     *
     * @return A serialized string representation of the input text with the specified colors.
     */
    fun translateToString(
        input: String,
        foregroundColor: TextColor,
        backgroundColor: TextColor,
        affixAmount: Int = 1
    ) = MiniMessage.miniMessage()
        .serialize(translateToComponent(input, foregroundColor, backgroundColor, affixAmount))

}