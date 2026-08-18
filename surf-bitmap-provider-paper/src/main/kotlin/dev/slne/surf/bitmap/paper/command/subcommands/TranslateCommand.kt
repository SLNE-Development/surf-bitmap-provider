package dev.slne.surf.bitmap.paper.command.subcommands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.bitmap.common.command.sendBitmapTranslation
import dev.slne.surf.bitmap.paper.command.PermissionRegistry

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

        sender.sendBitmapTranslation(
            foregroundColorInput,
            shadowColorInput,
            backgroundColorInput,
            affixAmount,
            input
        )
    }
}
