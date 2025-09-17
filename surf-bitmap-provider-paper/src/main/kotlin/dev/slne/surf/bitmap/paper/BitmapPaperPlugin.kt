package dev.slne.surf.bitmap.paper

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import dev.slne.surf.bitmap.common.placeholder.BitmapMiniPlaceholderExpansion
import dev.slne.surf.bitmap.paper.command.lettergenCommand
import org.bukkit.plugin.java.JavaPlugin

class BitmapPaperPlugin : SuspendingJavaPlugin() {
    override suspend fun onEnableAsync() {
        BitmapMiniPlaceholderExpansion.build().register()

        lettergenCommand()
    }
}

val plugin get() = JavaPlugin.getPlugin(BitmapPaperPlugin::class.java)