package dev.slne.surf.bitmap.velocity

import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import dev.slne.surf.bitmap.velocity.command.lettergenCommand
import dev.slne.surf.bitmap.velocity.placeholder.BitmapMiniPlaceholderExpansion

class BitmapVelocityPlugin {
    @Subscribe
    fun onInitialize(event: ProxyInitializeEvent) {
        lettergenCommand()
        BitmapMiniPlaceholderExpansion.build().register()
    }
}