package dev.slne.surf.bitmap.velocity.command.subcommands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.bitmap.common.provider.BitmapProvider
import dev.slne.surf.bitmap.velocity.command.PermissionRegistry
import dev.slne.surf.surfapi.core.api.messages.adventure.buildText
import dev.slne.surf.surfapi.core.api.messages.adventure.clickCopiesToClipboard
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor

fun CommandAPICommand.translateCommand() = subcommand("translate") {
    withPermission(PermissionRegistry.LETTERGEN_COMMAND_TRANSLATE)

    textArgument("foregroundColorInput")
    textArgument("backgroundColorInput")
    integerArgument("affixAmount")
    greedyStringArgument("input")

    anyExecutor { sender, args ->
        val foregroundColorInput: String by args
        val backgroundColorInput: String by args
        val affixAmount: Int by args
        val input: String by args

        val foregroundColor =
            TextColor.fromCSSHexString(foregroundColorInput) ?: NamedTextColor.WHITE
        val backgroundColor =
            TextColor.fromCSSHexString(backgroundColorInput) ?: NamedTextColor.BLACK

        sender.sendText {
            appendPrefix()
            info("Translating: ")
            variableValue(input)

            appendNewPrefixedLine()
            appendNewPrefixedLine()

            append {
                append(
                    BitmapProvider.translateToComponent(
                        input,
                        foregroundColor,
                        backgroundColor,
                        affixAmount
                    )
                )

                hoverEvent(buildText {
                    spacer("Click to copy to clipboard")
                })

                clickCopiesToClipboard(
                    BitmapProvider.translateToString(
                        input,
                        foregroundColor,
                        backgroundColor,
                        affixAmount
                    )
                )
            }
        }
    }
}