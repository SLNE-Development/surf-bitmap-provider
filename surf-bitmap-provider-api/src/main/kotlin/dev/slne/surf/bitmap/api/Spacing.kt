package dev.slne.surf.bitmap.api

import kotlin.math.abs

enum class Spacing(val char: String, val spacing: Int) {
    NEGATIVE_SPACE_ONE("\uE101", -1),
    NEGATIVE_SPACE_TWO("\uE102", -2),
    NEGATIVE_SPACE_FOUR("\uE103", -4),
    NEGATIVE_SPACE_EIGHT("\uE104", -8),
    NEGATIVE_SPACE_SIXTEEN("\uE105", -16),
    NEGATIVE_SPACE_THIRTY_TWO("\uE106", -32),
    NEGATIVE_SPACE_SIXTY_FOUR("\uE107", -64),
    NEGATIVE_SPACE_ONE_TWENTY_EIGHT("\uE108", -128),
    NEGATIVE_SPACE_TWO_FIFTY_SIX("\uE109", -256),
    NEGATIVE_SPACE_FIVE_TWELVE("\uE110", -512),

    POSITIVE_SPACE_ONE("\uE112", 1),
    POSITIVE_SPACE_TWO("\uE113", 2),
    POSITIVE_SPACE_FOUR("\uE114", 4),
    POSITIVE_SPACE_EIGHT("\uE115", 8),
    POSITIVE_SPACE_SIXTEEN("\uE116", 16),
    POSITIVE_SPACE_THIRTY_TWO("\uE117", 32),
    POSITIVE_SPACE_SIXTY_FOUR("\uE118", 64),
    POSITIVE_SPACE_ONE_TWENTY_EIGHT("\uE119", 128),
    POSITIVE_SPACE_TWO_FIFTY_SIX("\uE120", 256),
    POSITIVE_SPACE_FIVE_TWELVE("\uE121", 512);
}

fun calculateGlyphSpacing(spacing: Int): String {
    if (spacing == 0) return ""

    return buildString {
        var remaining = spacing

        val sorted = Spacing.entries
            .filter { (spacing < 0 && it.spacing < 0) || (spacing > 0 && it.spacing > 0) }
            .sortedByDescending { abs(it.spacing) }

        for (space in sorted) {
            while ((spacing < 0 && remaining <= space.spacing) || (spacing > 0 && remaining >= space.spacing)) {
                append(space.char)
                remaining -= space.spacing
            }
        }
    }
}
