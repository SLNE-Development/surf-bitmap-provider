package dev.slne.surf.bitmap.generator.command

import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.slne.surf.bitmap.generator.command.subcommands.generateCommand
import dev.slne.surf.bitmap.generator.command.subcommands.translateCommand
import dev.slne.surf.bitmap.generator.utils.PermissionRegistry


fun lettergenCommand() = commandAPICommand("lettergen") {
    withPermission(PermissionRegistry.LETTERGEN_COMMAND)

    generateCommand()
    translateCommand()
}