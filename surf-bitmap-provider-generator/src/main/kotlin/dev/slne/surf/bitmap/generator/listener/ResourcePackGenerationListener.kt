package dev.slne.surf.bitmap.generator.listener

import com.github.shynixn.mccoroutine.folia.launch
import com.nexomc.nexo.api.events.resourcepack.NexoPostPackGenerateEvent
import dev.slne.surf.bitmap.generator.plugin
import dev.slne.surf.bitmap.generator.utils.generateBitmapFromConfig
import dev.slne.surf.bitmap.generator.utils.readConfig
import dev.slne.surf.surfapi.core.api.util.mutableObjectSetOf
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.div

object ResourcePackGenerationListener : Listener {

    @EventHandler
    fun onNexoPostPackGenerate(event: NexoPostPackGenerateEvent) {
        val nexoPluginFolderPath = plugin.dataPath.parent / "Nexo"
        val nexoGlyphsPath = nexoPluginFolderPath / "glyphs" / "bitmaps"

        if (!Files.exists(nexoGlyphsPath)) {
            return
        }

        val ymlConfigFilePaths = mutableObjectSetOf<Path>()
        Files.walk(nexoGlyphsPath).forEach { path ->
            if (path.toString().endsWith(".yml")) {
                ymlConfigFilePaths.add(path)
            }
        }

        plugin.launch {
            val parsedYmlConfigFiles = ymlConfigFilePaths.map { it to readConfig(it) }
            val invalidYmlConfigFiles = parsedYmlConfigFiles.filter { it.second == null }

            invalidYmlConfigFiles.forEach { invalid ->
                plugin.logger.warning("Invalid YML config file: ${invalid.first}")
            }

            val ymlConfigFiles = parsedYmlConfigFiles
                .filter { it.second != null }
                .map { it.first to it.second!! }

            ymlConfigFiles.forEach { ymlConfig ->
                val config = ymlConfig.second
                val fileName = ymlConfig.first.toString()
                    .replace("plugins\\Nexo\\glyphs\\bitmaps", "")
                    .replace("plugins/Nexo/glyphs/bitmaps", "")
                    .replace("\\", "/")
                    .replace(".yml", "")
                
                val fileNamePascalCase = fileName
                    .split("/")
                    .map { it.replaceFirstChar { char -> char.uppercase() } }
                    .joinToString("")

                generateBitmapFromConfig(
                    configName = fileNamePascalCase,
                    config = config
                )
            }

            plugin.logger.info("Wrote ${ymlConfigFiles.size} bitmap files to the bitmap directory.")
        }
    }
}
