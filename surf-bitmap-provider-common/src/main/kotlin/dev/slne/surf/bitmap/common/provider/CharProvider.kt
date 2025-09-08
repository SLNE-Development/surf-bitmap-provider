package dev.slne.surf.bitmap.common.provider

import dev.slne.surf.bitmap.common.utils.BITMAP_BACKGROUND
import dev.slne.surf.bitmap.common.utils.Spacing

/**
 * Represents a character provider that specifies a character and its width,
 * and allows the generation of a background representation for that character.
 *
 * @property char The character associated with this provider.
 * @property width The width of the character, which determines the spacing or position.
 */
data class CharProvider(
    val char: Char,
    val width: Int,
) {

    /**
     * Generates a background pattern as a string represented by the current instance's width.
     *
     * The method constructs a string using the `BACKGROUND` value for each unit of width.
     * For every position after the first, a specific spacing character (`Spacing.NEGATIVE_SPACE_ONE`) is appended
     * before appending the `BACKGROUND` character. This creates a patterned effect in the resulting string.
     *
     * @return A string that contains the generated background pattern.
     */
    fun generateBackground() = buildString {
        repeat(width) {
            if (it > 0) {
                append(Spacing.NEGATIVE_SPACE_ONE.char.toString())
            }

            append(BITMAP_BACKGROUND)
        }
    }
}
