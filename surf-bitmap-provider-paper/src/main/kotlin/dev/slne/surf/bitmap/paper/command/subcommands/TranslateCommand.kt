package dev.slne.surf.bitmap.paper.command.subcommands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.api.core.messages.adventure.buildText
import dev.slne.surf.api.core.messages.adventure.clickCopiesToClipboard
import dev.slne.surf.api.core.messages.adventure.sendText
import dev.slne.surf.bitmap.common.provider.BitmapProvider
import dev.slne.surf.bitmap.paper.command.PermissionRegistry
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.ShadowColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.minimessage.MiniMessage

fun CommandAPICommand.translateCommand() = subcommand("translate") {
    withPermission(PermissionRegistry.LETTER_GEN_COMMAND_TRANSLATE)

    textArgument("foregroundColorInput")
    textArgument("shadowColorInput")
    textArgument("backgroundColorInput")
    integerArgument("affixAmount")
    greedyStringArgument("input")

    anyExecutor { sender, args ->
        val foregroundColorInput: String by args
        val shadowColorInput: String by args
        val backgroundColorInput: String by args
        val affixAmount: Int by args
        val input: String by args

        val foregroundColor = TextColor.fromHexString(foregroundColorInput)
            ?: NamedTextColor.WHITE
        val backgroundColor = TextColor.fromHexString(backgroundColorInput)
            ?: NamedTextColor.BLACK
        val shadowColor = ShadowColor.fromHexString(shadowColorInput)
            ?: ShadowColor.none()

        sender.sendText {
            appendInfoPrefix()
            info("Translating: ")
            variableValue(input)

            appendNewInfoPrefixedLine()
            appendNewInfoPrefixedLine()

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
}