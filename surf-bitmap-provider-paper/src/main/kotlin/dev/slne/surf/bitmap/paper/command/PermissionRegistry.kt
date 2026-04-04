package dev.slne.surf.bitmap.paper.command

import dev.slne.surf.api.paper.permission.PermissionRegistry

object PermissionRegistry : PermissionRegistry() {

    private const val PREFIX = "surf.bitmap.provider"
    private const val COMMAND_PREFIX = "$PREFIX.command"

    val LETTER_GEN_COMMAND = create("$COMMAND_PREFIX.generator")
    val LETTER_GEN_COMMAND_TRANSLATE = create("$COMMAND_PREFIX.generator.translate")
    val LETTER_GEN_COMMAND_HEAD = create("$COMMAND_PREFIX.generator.head")

}