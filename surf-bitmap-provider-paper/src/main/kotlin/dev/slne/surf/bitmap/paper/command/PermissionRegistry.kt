package dev.slne.surf.bitmap.paper.command

import dev.slne.surf.api.paper.permission.PermissionRegistry
import dev.slne.surf.bitmap.common.permission.BitmapPermissions

object PermissionRegistry : PermissionRegistry() {

    val LETTER_GEN_COMMAND = create(BitmapPermissions.LETTER_GEN_COMMAND)
    val LETTER_GEN_COMMAND_TRANSLATE = create(BitmapPermissions.LETTER_GEN_COMMAND_TRANSLATE)
    val LETTER_GEN_COMMAND_HEAD = create(BitmapPermissions.LETTER_GEN_COMMAND_HEAD)

}
