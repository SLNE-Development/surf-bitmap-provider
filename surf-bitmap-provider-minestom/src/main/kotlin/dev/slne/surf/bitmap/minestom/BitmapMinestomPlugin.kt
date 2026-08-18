package dev.slne.surf.bitmap.minestom

import com.google.auto.service.AutoService
import dev.slne.minestom.lobby.api.plugin.MinestomPlugin
import dev.slne.minestom.lobby.api.plugin.annotation.MinestomPluginMeta
import dev.slne.surf.bitmap.minestom.command.BitmapCommandRegistrar

@AutoService(MinestomPlugin::class)
@MinestomPluginMeta(
    "surf-bitmap-provider-minestom",
    dependsOn = ["surf-api-minestom"]
)
class BitmapMinestomPlugin : MinestomPlugin(BitmapMinestomEntrypoint::class.java) {
    override fun configurePlugin() {
        bindCommandRegistrar<BitmapCommandRegistrar>()
    }
}
