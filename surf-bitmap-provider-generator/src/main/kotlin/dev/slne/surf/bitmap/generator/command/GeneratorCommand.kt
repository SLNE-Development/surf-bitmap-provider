package dev.slne.surf.bitmap.generator.command

import com.github.shynixn.mccoroutine.folia.launch
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.bitmap.generator.generator.BitmapProviderGenerator
import dev.slne.surf.bitmap.generator.generator.GeneratorResult
import dev.slne.surf.bitmap.generator.plugin
import dev.slne.surf.bitmap.generator.utils.PermissionRegistry
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.command.CommandSender
import kotlin.io.path.div
import kotlin.time.measureTime

fun generatorCommand() = commandAPICommand("lettergen") {
    withPermission(PermissionRegistry.GENERATOR_COMMAND)

    textArgument("name")
    textArgument("foregroundHex")
    textArgument("backgroundHex")
    textArgument("configPath")
    textArgument("texturePath")
    booleanArgument("force", true)

    anyExecutor { sender, args ->
        val name: String by args
        val foregroundHex: String by args
        val backgroundHex: String by args
        val configPath: String by args
        val texturePath: String by args
        val force: Boolean? by args
        val override = force ?: false

        if (name.length !in 3..64) {
            sender.sendText {
                appendPrefix()

                error("Der Name muss zwischen 3 und 64 Zeichen lang sein.")
            }
            return@anyExecutor
        }

        val realForegroundHex = foregroundHex.replace("#", "")
        val realBackgroundHex = backgroundHex.replace("#", "")

        if (realForegroundHex.length != 6 || realBackgroundHex.length != 6) {
            sender.sendText {
                appendPrefix()

                error("Die Hex-Farben müssen 6 Zeichen (7 Zeichen mit #) lang sein.")
            }

            return@anyExecutor
        }

        if (!checkValidHex(realForegroundHex) || !checkValidHex(realBackgroundHex)) {
            sender.sendText {
                appendPrefix()

                error("Die Hex-Farben müssen aus Zahlen und Buchstaben von a bis f bestehen.")
            }

            return@anyExecutor
        }

        if (configPath.isBlank()) {
            sender.sendText {
                appendPrefix()

                error("Der Pfad muss angegeben werden.")
            }

            return@anyExecutor
        }

        if (texturePath.isBlank()) {
            sender.sendText {
                appendPrefix()

                error("Der Pfad muss angegeben werden.")
            }

            return@anyExecutor
        }

        val nexoPluginFolderPath = plugin.dataPath.parent / "Nexo"
        val nexoGlyphsPath = nexoPluginFolderPath / "glyphs" / "bitmaps"
        val nexoTexturesPath =
            nexoPluginFolderPath / "pack" / "assets" / "minecraft" / "textures" / "bitmaps"

        val realConfigPath = nexoGlyphsPath / configPath
        val realTexturePath = nexoTexturesPath / texturePath / name

        val generator = BitmapProviderGenerator(
            name = name,
            foregroundHex = realForegroundHex,
            backgroundHex = realBackgroundHex,
            configPath = realConfigPath,
            texturePath = realTexturePath,
            override = override
        )

        plugin.launch {
            sender.sendText {
                appendPrefix()
                success("Starte Generierung:")
            }

            sendOptionLine(sender, "Name", name)
            sendOptionLine(sender, "Foreground", foregroundHex)
            sendOptionLine(sender, "Background", backgroundHex)
            sendOptionLine(sender, "Config Path", configPath)
            sendOptionLine(sender, "Texture Path", texturePath)
            sendOptionLine(sender, "Override", override.toString())

            val result: GeneratorResult
            val duration = measureTime {
                result = generator.generate()
            }

            when (result) {
                GeneratorResult.FILE_GENERATED -> {
                    sender.sendText {
                        appendPrefix()

                        info("Die Generierung war erfolgreich.")
                    }
                }

                GeneratorResult.FILE_ALREADY_EXISTS -> {
                    sender.sendText {
                        appendPrefix()

                        error("Eine der zu generierenden Dateien existiert bereits.")
                    }
                }

                GeneratorResult.FILE_NOT_GENERATED -> {
                    sender.sendText {
                        appendPrefix()

                        error("Eine der zu generierenden Dateien konnte nicht generiert werden.")
                    }
                }
            }

            sender.sendText {
                appendPrefix()

                info("Generierung für ")
                variableValue(name)
                info(" abegschlossen in ")
                variableValue("${duration.inWholeMilliseconds}ms")
                info(".")
            }
        }
    }
}

private fun sendOptionLine(sender: CommandSender, key: String, value: String) {
    sender.sendText {
        appendPrefix()
        variableKey(" - $key: ")
        variableValue(value)
    }
}

private fun checkValidHex(hex: String): Boolean {
    if (hex.length != 6) {
        return false
    }

    for (c in hex) {
        if (!c.isDigit() && c !in 'a'..'f') {
            return false
        }
    }

    return true
}