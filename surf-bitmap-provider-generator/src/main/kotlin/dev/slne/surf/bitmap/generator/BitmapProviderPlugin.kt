package dev.slne.surf.bitmap.generator

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import dev.slne.surf.bitmap.generator.command.generatorCommand
import dev.slne.surf.bitmap.generator.listener.ResourcePackGenerationListener
import dev.slne.surf.surfapi.bukkit.api.event.register
import org.bukkit.plugin.java.JavaPlugin

val plugin get() = JavaPlugin.getPlugin(BitmapProviderPlugin::class.java)

class BitmapProviderPlugin : SuspendingJavaPlugin() {

    override suspend fun onEnableAsync() {
        ResourcePackGenerationListener.register()
        generatorCommand()
    }

}