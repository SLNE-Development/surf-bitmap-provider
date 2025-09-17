package dev.slne.surf.bitmap.paper

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import dev.slne.surf.bitmap.paper.command.lettergenCommand
import dev.slne.surf.bitmap.paper.placeholder.BitmapPapiExpansion
import dev.slne.surf.surfapi.bukkit.api.hook.papi.papiHook
import org.bukkit.plugin.java.JavaPlugin

class BitmapPaperPlugin : SuspendingJavaPlugin() {
    override suspend fun onEnableAsync() {
        papiHook.register(BitmapPapiExpansion)
        lettergenCommand()
    }
}

val plugin get() = JavaPlugin.getPlugin(BitmapPaperPlugin::class.java)