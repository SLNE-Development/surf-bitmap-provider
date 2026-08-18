package dev.slne.surf.bitmap.common.permission

/**
 * Platform-neutral registry of all permission node strings used by the bitmap provider.
 */
object BitmapPermissions {

    private const val PREFIX = "surf.bitmap.provider"
    private const val COMMAND_PREFIX = "$PREFIX.command"

    const val LETTER_GEN_COMMAND = "$COMMAND_PREFIX.generator"
    const val LETTER_GEN_COMMAND_TRANSLATE = "$COMMAND_PREFIX.generator.translate"
    const val LETTER_GEN_COMMAND_HEAD = "$COMMAND_PREFIX.generator.head"

}
