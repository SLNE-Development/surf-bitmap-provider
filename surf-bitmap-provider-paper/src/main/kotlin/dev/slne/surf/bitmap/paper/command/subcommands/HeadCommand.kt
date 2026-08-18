package dev.slne.surf.bitmap.paper.command.subcommands

import com.github.shynixn.mccoroutine.folia.launch
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.bitmap.common.command.sendPlayerHead
import dev.slne.surf.bitmap.paper.command.PermissionRegistry
import dev.slne.surf.bitmap.paper.plugin

fun CommandAPICommand.headCommand() = subcommand("head") {
    withPermission(PermissionRegistry.LETTER_GEN_COMMAND_HEAD)

    stringArgument("playerName")
    integerArgument("scale", min = 1, optional = true)

    anyExecutor { sender, args ->
        val playerName: String by args
        val scale: Int? by args

        plugin.launch {
            sender.sendPlayerHead(playerName, scale)
        }
    }
}
