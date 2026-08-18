package dev.slne.surf.bitmap.common.provider

import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.ShadowColor
import net.kyori.adventure.text.format.TextColor

/**
 * Parses [input] as the foreground color of a bitmap, falling back to
 * [NamedTextColor.WHITE] when it is no valid hex color.
 */
fun parseForegroundColor(input: String): TextColor =
    TextColor.fromHexString(input) ?: NamedTextColor.WHITE

/**
 * Parses [input] as the background color of a bitmap, falling back to
 * [NamedTextColor.BLACK] when it is no valid hex color.
 */
fun parseBackgroundColor(input: String): TextColor =
    TextColor.fromHexString(input) ?: NamedTextColor.BLACK

/**
 * Parses [input] as the shadow color of a bitmap, falling back to [ShadowColor.none] when it is
 * no valid hex color.
 */
fun parseShadowColor(input: String): ShadowColor =
    ShadowColor.fromHexString(input) ?: ShadowColor.none()
