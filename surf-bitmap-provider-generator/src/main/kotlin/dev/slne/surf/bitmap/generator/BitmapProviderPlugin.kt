package dev.slne.surf.bitmap.generator

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import dev.slne.surf.bitmap.generator.command.lettergenCommand
import dev.slne.surf.bitmap.generator.listener.ResourcePackGenerationListener
import dev.slne.surf.surfapi.bukkit.api.event.register
import org.bukkit.plugin.java.JavaPlugin

val plugin get() = JavaPlugin.getPlugin(BitmapProviderPlugin::class.java)

class BitmapProviderPlugin : SuspendingJavaPlugin() {

    override suspend fun onLoadAsync() {
        saveResource("raw", true)
        saveResource("TemplateBitmap.kt", true)
    }

    override suspend fun onEnableAsync() {
        ResourcePackGenerationListener.register()
        lettergenCommand()
    }

}