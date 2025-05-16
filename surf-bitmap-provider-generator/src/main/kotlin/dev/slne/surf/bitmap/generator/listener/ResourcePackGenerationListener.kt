package dev.slne.surf.bitmap.generator.listener

import com.github.shynixn.mccoroutine.folia.launch
import com.nexomc.nexo.api.events.resourcepack.NexoPostPackGenerateEvent
import dev.slne.surf.bitmap.generator.plugin
import dev.slne.surf.bitmap.generator.utils.generateBitmapFromConfig
import dev.slne.surf.bitmap.generator.utils.readConfig
import dev.slne.surf.surfapi.bukkit.api.extensions.server
import dev.slne.surf.surfapi.core.api.util.mutableObjectSetOf
import dev.slne.surf.surfapi.core.api.util.toObjectSet
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.div
import kotlin.io.path.isRegularFile
import kotlin.io.path.name
import kotlin.io.path.nameWithoutExtension
import kotlin.io.path.notExists
import kotlin.io.path.relativeTo
import kotlin.io.path.walk

object ResourcePackGenerationListener : Listener {

    @OptIn(ExperimentalPathApi::class)
    @EventHandler
    fun onNexoPostPackGenerate(event: NexoPostPackGenerateEvent) {
        val nexoPluginFolderPath = server.pluginsFolder.toPath() / "Nexo"
        val nexoGlyphsPath = nexoPluginFolderPath / "glyphs" / "bitmaps"

        if (nexoGlyphsPath.notExists()) return

        val ymlConfigFilePaths = nexoGlyphsPath.walk()
            .filter { it.isRegularFile() && it.name.endsWith(".yml") }
            .toObjectSet()

        plugin.launch {
            val parsedYmlConfigFiles = ymlConfigFilePaths.map { it to readConfig(it) }
            val invalidYmlConfigFiles = parsedYmlConfigFiles.filter { it.second == null }

            invalidYmlConfigFiles.forEach { (path) ->
                plugin.logger.warning("Invalid YML config file: $path")
            }

            val validConfigs = parsedYmlConfigFiles.mapNotNull { (path, config) ->
                config?.let { path to it }
            }

            validConfigs.forEach { (path, config) ->
                val relativePath = path.relativeTo(nexoGlyphsPath)
                val pascalName = relativePath
                    .joinToString("") { it.nameWithoutExtension.replaceFirstChar { c -> c.uppercase() } }

                generateBitmapFromConfig(
                    configName = pascalName,
                    config = config
                )
            }

            plugin.logger.info("Wrote ${validConfigs.size} bitmap files to the bitmap directory.")
        }
    }
}
