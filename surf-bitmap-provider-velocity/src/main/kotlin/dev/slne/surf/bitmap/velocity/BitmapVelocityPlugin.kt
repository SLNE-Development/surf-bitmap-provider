package dev.slne.surf.bitmap.velocity

import com.github.shynixn.mccoroutine.velocity.SuspendingPluginContainer
import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.PluginContainer
import com.velocitypowered.api.proxy.ProxyServer
import dev.slne.surf.bitmap.velocity.command.lettergenCommand
import dev.slne.surf.bitmap.velocity.placeholder.BitmapMiniPlaceholderExpansion

class BitmapVelocityPlugin @Inject constructor(
    val server: ProxyServer,
    val container: PluginContainer,
    private val suspendingContainer: SuspendingPluginContainer
) {

    init {
        instance = this
    }

    @Subscribe
    fun onInitialize(event: ProxyInitializeEvent) {
        suspendingContainer.initialize(this)

        lettergenCommand()
        BitmapMiniPlaceholderExpansion.build().register()
    }

    companion object {
        lateinit var instance: BitmapVelocityPlugin
            private set
    }
}

val plugin get() = BitmapVelocityPlugin.instance
val server get() = plugin.server
val container get() = plugin.container