package dev.slne.surf.bitmap.paper

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import dev.slne.surf.bitmap.paper.placeholder.BitmapPapiExpansion
import dev.slne.surf.surfapi.bukkit.api.hook.papi.papiHook

class BitmapPaperPlugin : SuspendingJavaPlugin() {
    override suspend fun onEnableAsync() {
        papiHook.register(BitmapPapiExpansion)
    }
}