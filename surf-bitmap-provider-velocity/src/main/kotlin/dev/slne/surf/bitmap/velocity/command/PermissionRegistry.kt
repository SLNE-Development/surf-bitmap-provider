package dev.slne.surf.bitmap.velocity.command

object PermissionRegistry {

    private const val PREFIX = "surf.bitmap.provider"
    private const val COMMAND_PREFIX = "$PREFIX.command"

    const val LETTERGEN_COMMAND = "$COMMAND_PREFIX.generator"
    const val LETTERGEN_COMMAND_TRANSLATE = "$COMMAND_PREFIX.generator.translate"

}