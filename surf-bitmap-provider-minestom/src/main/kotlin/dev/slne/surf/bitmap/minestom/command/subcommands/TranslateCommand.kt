package dev.slne.surf.bitmap.minestom.command.subcommands

import dev.slne.minestom.lobby.api.command.commandapi.CommandAPICommand
import dev.slne.minestom.lobby.api.command.commandapi.dsl.*
import dev.slne.surf.bitmap.common.command.sendBitmapTranslation
import dev.slne.surf.bitmap.common.permission.BitmapPermissions

fun CommandAPICommand.translateCommand() = withSubcommand(
    subcommand("translate") {
        withPermission(BitmapPermissions.LETTER_GEN_COMMAND_TRANSLATE)

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
)
