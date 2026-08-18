package dev.slne.surf.bitmap.common.provider

import dev.slne.surf.bitmap.common.provider.providers.AlphabetProvider
import dev.slne.surf.bitmap.common.provider.providers.NumberProvider
import dev.slne.surf.bitmap.common.provider.providers.UnknownProvider
import dev.slne.surf.bitmap.common.provider.providers.UtilityProvider
import dev.slne.surf.bitmap.common.utils.BITMAP_BACKGROUND
import dev.slne.surf.bitmap.common.utils.Spacing
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class CharProviderTest {

    @Test
    fun `background is padded with negative spaces between the pixels`() {
        assertEquals("$BITMAP_BACKGROUND", CharProvider('a', 1).generateBackground())
        assertEquals(
            listOf(BITMAP_BACKGROUND, Spacing.NEGATIVE_SPACE_ONE.char, BITMAP_BACKGROUND)
                .joinToString(""),
            CharProvider('a', 2).generateBackground()
        )
    }

    @Test
    fun `background of a zero width character is empty`() {
        assertEquals("", CharProvider('a', 0).generateBackground())
    }

    @Test
    fun `alphabet provider covers the lowercase alphabet only`() {
        for (char in 'a'..'z') {
            assertEquals(6, AlphabetProvider.findChar(char)?.width, "Missing glyph for '$char'")
        }

        assertNull(AlphabetProvider.findChar('A'))
        assertNull(AlphabetProvider.findChar('1'))
    }

    @Test
    fun `number provider covers all digits`() {
        for (char in '0'..'9') {
            assertEquals(6, NumberProvider.findChar(char)?.width, "Missing glyph for '$char'")
        }

        assertNull(NumberProvider.findChar('a'))
    }

    @Test
    fun `utility provider maps punctuation to its own widths`() {
        assertEquals(UtilityProvider.euro, UtilityProvider.findChar('€'))
        assertEquals(UtilityProvider.dot, UtilityProvider.findChar('.'))
        assertNull(UtilityProvider.findChar('a'))
    }

    @Test
    fun `unknown provider passes the character through without width`() {
        assertEquals(CharProvider('☃', 0), UnknownProvider.findChar('☃'))
    }
}
