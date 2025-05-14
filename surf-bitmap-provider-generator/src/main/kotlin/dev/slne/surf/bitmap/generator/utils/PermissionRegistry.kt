package dev.slne.surf.bitmap.generator.utils

import dev.slne.surf.surfapi.bukkit.api.permission.PermissionRegistry

object PermissionRegistry : PermissionRegistry() {

    private const val PREFIX = "surf.bitmap.provider"
    private const val COMMAND_PREFIX = "$PREFIX.command"

    val LETTERGEN_COMMAND = create("$COMMAND_PREFIX.generator")
    val LETTERGEN_COMMAND_GENERATE = create("$COMMAND_PREFIX.generator.generate")
    val LETTERGEN_COMMAND_TRANSLATE = create("$COMMAND_PREFIX.generator.translate")

}