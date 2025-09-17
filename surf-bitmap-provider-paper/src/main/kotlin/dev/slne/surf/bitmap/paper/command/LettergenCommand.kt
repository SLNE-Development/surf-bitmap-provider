package dev.slne.surf.bitmap.paper.command

import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.slne.surf.bitmap.paper.command.subcommands.headCommand
import dev.slne.surf.bitmap.paper.command.subcommands.translateCommand

fun lettergenCommand() = commandAPICommand("lettergen") {
    withPermission(PermissionRegistry.LETTERGEN_COMMAND)

    translateCommand()
    headCommand()
}