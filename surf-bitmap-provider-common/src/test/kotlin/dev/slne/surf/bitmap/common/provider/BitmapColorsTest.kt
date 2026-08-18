package dev.slne.surf.bitmap.common.provider

import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.ShadowColor
import net.kyori.adventure.text.format.TextColor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BitmapColorsTest {

    @Test
    fun `foreground color is parsed from hex`() {
        assertEquals(TextColor.color(0x123456), parseForegroundColor("#123456"))
    }

    @Test
    fun `foreground color falls back to white`() {
        assertEquals(NamedTextColor.WHITE, parseForegroundColor("not a color"))
    }

    @Test
    fun `background color is parsed from hex`() {
        assertEquals(TextColor.color(0xABCDEF), parseBackgroundColor("#abcdef"))
    }

    @Test
    fun `background color falls back to black`() {
        assertEquals(NamedTextColor.BLACK, parseBackgroundColor(""))
    }

    @Test
    fun `shadow color is parsed from hex`() {
        assertEquals(ShadowColor.shadowColor(0x11, 0x22, 0x33, 0x44), parseShadowColor("#11223344"))
    }

    @Test
    fun `shadow color falls back to none`() {
        assertEquals(ShadowColor.none(), parseShadowColor("#123456"))
    }
}
