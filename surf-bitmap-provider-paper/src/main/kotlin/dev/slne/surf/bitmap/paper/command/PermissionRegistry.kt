package dev.slne.surf.bitmap.paper.command

import dev.slne.surf.surfapi.bukkit.api.permission.PermissionRegistry

object PermissionRegistry : PermissionRegistry() {

    private const val PREFIX = "surf.bitmap.provider"
    private const val COMMAND_PREFIX = "$PREFIX.command"

    val LETTERGEN_COMMAND = create("$COMMAND_PREFIX.generator")
    val LETTERGEN_COMMAND_TRANSLATE = create("$COMMAND_PREFIX.generator.translate")
    val LETTERGEN_COMMAND_HEAD = create("$COMMAND_PREFIX.generator.head")

}