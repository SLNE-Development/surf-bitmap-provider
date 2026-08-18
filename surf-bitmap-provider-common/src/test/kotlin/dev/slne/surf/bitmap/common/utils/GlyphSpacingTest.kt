package dev.slne.surf.bitmap.common.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GlyphSpacingTest {

    @Test
    fun `no spacing yields an empty string`() {
        assertEquals("", calculateGlyphSpacing(0))
    }

    @Test
    fun `a single space maps to its own glyph`() {
        assertEquals("${Spacing.POSITIVE_SPACE_ONE.char}", calculateGlyphSpacing(1))
        assertEquals("${Spacing.NEGATIVE_SPACE_ONE.char}", calculateGlyphSpacing(-1))
    }

    @Test
    fun `spacing is composed from the largest glyphs first`() {
        assertEquals(
            "${Spacing.POSITIVE_SPACE_EIGHT.char}${Spacing.POSITIVE_SPACE_ONE.char}",
            calculateGlyphSpacing(9)
        )
        assertEquals(
            "${Spacing.NEGATIVE_SPACE_TWO.char}${Spacing.NEGATIVE_SPACE_ONE.char}",
            calculateGlyphSpacing(-3)
        )
    }

    @Test
    fun `spacing beyond the largest glyph repeats it`() {
        assertEquals(
            "${Spacing.POSITIVE_SPACE_FIVE_TWELVE.char}".repeat(2),
            calculateGlyphSpacing(1024)
        )
    }

    @Test
    fun `every spacing value sums up to its input`() {
        for (spacing in -600..600) {
            val glyphs = calculateGlyphSpacing(spacing)
            val sum = glyphs.sumOf { char ->
                Spacing.entries.first { it.char == char }.spacing
            }

            assertEquals(spacing, sum, "Spacing $spacing was composed incorrectly")
        }
    }
}
