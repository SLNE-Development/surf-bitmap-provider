package dev.slne.surf.bitmap.minestom.command.subcommands

import dev.slne.surf.api.minestom.command.CommandAPICommand
import dev.slne.surf.api.minestom.command.dsl.*
import dev.slne.surf.bitmap.common.command.sendPlayerHead
import dev.slne.surf.bitmap.common.permission.BitmapPermissions

fun CommandAPICommand.headCommand() = withSubcommand(
    subcommand("head") {
        withPermission(BitmapPermissions.LETTER_GEN_COMMAND_HEAD)

        stringArgument("playerName")
        integerArgument("scale", min = 1, optional = true)

        anyExecutorSuspend { sender, args ->
            val playerName: String by args
            val scale: Int? by args

            sender.sendPlayerHead(playerName, scale, null)
        }
    }
)
