package dev.slne.surf.bitmap.common.command

import dev.slne.surf.api.core.messages.adventure.buildText
import dev.slne.surf.api.core.messages.adventure.clickCopiesToClipboard
import dev.slne.surf.api.core.messages.adventure.sendText
import dev.slne.surf.bitmap.common.provider.BitmapProvider
import dev.slne.surf.bitmap.common.provider.parseBackgroundColor
import dev.slne.surf.bitmap.common.provider.parseForegroundColor
import dev.slne.surf.bitmap.common.provider.parseShadowColor
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.minimessage.MiniMessage

/**
 * Translates [input] into its bitmap representation and sends it to this audience, together with a
 * hover and click action that copies the serialized component to the clipboard.
 *
 * The color inputs are parsed as hex colors and fall back to their defaults when they are invalid.
 */
fun Audience.sendBitmapTranslation(
    foregroundColorInput: String,
    shadowColorInput: String,
    backgroundColorInput: String,
    affixAmount: Int,
    input: String
) {
    val foregroundColor = parseForegroundColor(foregroundColorInput)
    val backgroundColor = parseBackgroundColor(backgroundColorInput)
    val shadowColor = parseShadowColor(shadowColorInput)

    sendText {
        appendInfoPrefix()
        info("Translating: ")
        variableValue(input)

        appendNewInfoPrefixedLine(2)

        val component = BitmapProvider.translateToComponent(
            input,
            foregroundColor,
            backgroundColor,
            shadowColor,
            affixAmount
        )

        append {
            append(component)

            hoverEvent(buildText {
                spacer("Click to copy to clipboard")
            })

            clickCopiesToClipboard(MiniMessage.miniMessage().serialize(component))
        }
    }
}
