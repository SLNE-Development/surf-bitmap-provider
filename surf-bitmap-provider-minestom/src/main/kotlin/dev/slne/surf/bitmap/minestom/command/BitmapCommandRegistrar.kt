package dev.slne.surf.bitmap.minestom.command

import com.google.inject.Inject
import dev.slne.minestom.lobby.api.command.CommandRegistrar

/**
 * Registers the bitmap commands of this plugin.
 */
class BitmapCommandRegistrar @Inject constructor() : CommandRegistrar {
    override fun register() {
        lettergenCommand()
    }
}
