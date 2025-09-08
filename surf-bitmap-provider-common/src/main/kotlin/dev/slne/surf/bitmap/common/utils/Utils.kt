package dev.slne.surf.bitmap.common.utils

import dev.slne.surf.bitmap.common.utils.Spacing.Companion.NEGATIVE_SPACES
import dev.slne.surf.bitmap.common.utils.Spacing.Companion.POSITIVE_SPACES
import dev.slne.surf.surfapi.core.api.util.objectListOf
import kotlin.math.abs

/**
 * Represents the character used as the default background in bitmap generation.
 *
 * `BITMAP_BACKGROUND` is primarily utilized in the creation of visual representations of text as
 * a placeholder character for the background layers of glyphs. It is used in conjunction with
 * character providers to generate structured patterns or designs for textual components.
 */
const val BITMAP_BACKGROUND = 'ꑁ'

/**
 * Represents a set of constants for negative and positive spacing values represented
 * by specific characters. Each constant defines a unique spacing value and its corresponding
 * character representation.
 *
 * This enum can be used for defining spacing adjustments in character layouts or graphical
 * representations where spacing control is required.
 *
 * @property char The special character representation associated with the spacing value.
 * @property spacing The integer value representing the amount of spacing. Negative values
 * denote reductions in space, and positive values represent increases*/
enum class Spacing(val char: Char, val spacing: Int) {
    NEGATIVE_SPACE_ONE('\uE101', -1),
    NEGATIVE_SPACE_TWO('\uE102', -2),
    NEGATIVE_SPACE_FOUR('\uE103', -4),
    NEGATIVE_SPACE_EIGHT('\uE104', -8),
    NEGATIVE_SPACE_SIXTEEN('\uE105', -16),
    NEGATIVE_SPACE_THIRTY_TWO('\uE106', -32),
    NEGATIVE_SPACE_SIXTY_FOUR('\uE107', -64),
    NEGATIVE_SPACE_ONE_TWENTY_EIGHT('\uE108', -128),
    NEGATIVE_SPACE_TWO_FIFTY_SIX('\uE109', -256),
    NEGATIVE_SPACE_FIVE_TWELVE('\uE110', -512),

    POSITIVE_SPACE_ONE('\uE112', 1),
    POSITIVE_SPACE_TWO('\uE113', 2),
    POSITIVE_SPACE_FOUR('\uE114', 4),
    POSITIVE_SPACE_EIGHT('\uE115', 8),
    POSITIVE_SPACE_SIXTEEN('\uE116', 16),
    POSITIVE_SPACE_THIRTY_TWO('\uE117', 32),
    POSITIVE_SPACE_SIXTY_FOUR('\uE118', 64),
    POSITIVE_SPACE_ONE_TWENTY_EIGHT('\uE119', 128),
    POSITIVE_SPACE_TWO_FIFTY_SIX('\uE120', 256),
    POSITIVE_SPACE_FIVE_TWELVE('\uE121', 512);

    companion object {
        /**
         * A list of objects representing predefined spacing definitions with negative spacing values.
         *
         * This property filters an initial collection of spacing entries to include only those with
         * negative spacing (spacing < 0). The resulting list is sorted by the absolute value of spacing
         * in descending order, ensuring that entries with the most significant negative spacing appear first.
         */
        val NEGATIVE_SPACES = objectListOf(
            *entries
                .filter { it.spacing < 0 }
                .sortedByDescending { abs(it.spacing) }
                .toTypedArray()
        )
        
        /**
         * A collection of predefined spacing objects with positive spacing values, sorted in descending order by spacing.
         *
         * This property is populated by filtering and sorting entries based on their spacing values greater than zero.
         * It is primarily used for generating output where positive spacing is required.
         */
        val POSITIVE_SPACES = objectListOf(
            *entries
                .filter { it.spacing > 0 }
                .sortedByDescending { it.spacing }
                .toTypedArray()
        )
    }
}

/**
 * Calculates the appropriate glyph spacing based on the given spacing value and generates
 * a string representation using predefined spacing characters.
 *
 * @param spacing The integer value representing the desired spacing. Positive values
 *                generate a string with positive spacing, while negative values
 *                generate a string with negative spacing. A value of 0 returns an empty string.
 *
 * @return A string composed of spacing characters that represent the given spacing value.
 *         The string uses predefined positive or negative spacing characters depending
 *         on the input value.
 */
fun calculateGlyphSpacing(spacing: Int): String {
    if (spacing == 0) return ""

    val spaceList = if (spacing < 0) NEGATIVE_SPACES else POSITIVE_SPACES

    return buildString {
        var remaining = spacing
        for (space in spaceList) {
            while ((spacing < 0 && remaining <= space.spacing) || (spacing > 0 && remaining >= space.spacing)) {
                append(space.char)
                remaining -= space.spacing
            }
        }
    }
}