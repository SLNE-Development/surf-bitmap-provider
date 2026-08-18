package dev.slne.surf.bitmap.minestom.command

import dev.slne.minestom.lobby.api.command.commandapi.dsl.commandAPICommand
import dev.slne.surf.bitmap.common.permission.BitmapPermissions
import dev.slne.surf.bitmap.minestom.command.subcommands.headCommand
import dev.slne.surf.bitmap.minestom.command.subcommands.translateCommand

fun lettergenCommand() = commandAPICommand("lettergen") {
    withPermission(BitmapPermissions.LETTER_GEN_COMMAND)

    translateCommand()
    headCommand()
}
