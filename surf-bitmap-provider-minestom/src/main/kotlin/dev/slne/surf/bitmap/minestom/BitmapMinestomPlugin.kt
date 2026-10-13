package dev.slne.surf.bitmap.minestom

import dev.slne.surf.api.minestom.plugin.SurfMinestomPlugin
import dev.slne.surf.bitmap.minestom.command.lettergenCommand

object BitmapMinestomPlugin : SurfMinestomPlugin() {
    override suspend fun onEnable() {
        lettergenCommand()
    }
}
