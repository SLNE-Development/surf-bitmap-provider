package dev.slne.surf.bitmap.minestom

import com.google.inject.Singleton
import dev.slne.minestom.lobby.api.plugin.MinestomPluginEntrypoint

@Singleton
class BitmapMinestomEntrypoint : MinestomPluginEntrypoint {
    override suspend fun start() = Unit
}
