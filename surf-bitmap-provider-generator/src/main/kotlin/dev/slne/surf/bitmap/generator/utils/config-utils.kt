package dev.slne.surf.bitmap.generator.utils

import dev.slne.surf.bitmap.generator.config.LetterConfig
import dev.slne.surf.bitmap.generator.generator.GeneratorResult
import dev.slne.surf.bitmap.generator.plugin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.spongepowered.configurate.kotlin.extensions.get
import org.spongepowered.configurate.kotlin.objectMapperFactory
import org.spongepowered.configurate.yaml.YamlConfigurationLoader
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.div
import kotlin.io.path.writeText

suspend fun readConfig(
    configPath: Path
): LetterConfig? = withContext(Dispatchers.IO) {
    val bitmapName = configPath.fileName.toString().removeSuffix(".yml")
    val rervertedConfig = revertConfigKeys(configPath, bitmapName)
    val node = YamlConfigurationLoader.builder()
        .path(configPath)
        .defaultOptions { options ->
            options.serializers { builder ->
                builder.registerAnnotatedObjects(objectMapperFactory())
            }
        }
        .buildAndLoadString(rervertedConfig)

    return@withContext node.get<LetterConfig>()
}

suspend fun writeConfig(
    name: String,
    configPath: Path,
    config: LetterConfig
): GeneratorResult = withContext(Dispatchers.IO) {
    val loader = YamlConfigurationLoader.builder()
        .path(configPath / "$name.yml")
        .defaultOptions { options ->
            options.serializers { builder ->
                builder.registerAnnotatedObjects(objectMapperFactory())
            }
        }
        .build()

    val node = loader.createNode()
    node.set(config)

    return@withContext runCatching {
        loader.save(node)
        GeneratorResult.FILE_GENERATED
    }.getOrNull() ?: GeneratorResult.FILE_NOT_GENERATED
}

suspend fun generateBitmapFromConfig(
    templatePath: Path = plugin.dataPath / "TemplateBitmap.kt",
    outputPath: Path = plugin.dataPath / "bitmaps",
    configName: String,
    config: LetterConfig,
) = withContext(Dispatchers.IO) {
    val templateContent = Files.readString(templatePath)

    val replacements = mapOf(
        "a" to config.bitmap.charMap["a"],
        "b" to config.bitmap.charMap["b"],
        "c" to config.bitmap.charMap["c"],
        "d" to config.bitmap.charMap["d"],
        "e" to config.bitmap.charMap["e"],
        "f" to config.bitmap.charMap["f"],
        "g" to config.bitmap.charMap["g"],
        "h" to config.bitmap.charMap["h"],
        "i" to config.bitmap.charMap["i"],
        "j" to config.bitmap.charMap["j"],
        "k" to config.bitmap.charMap["k"],
        "l" to config.bitmap.charMap["l"],
        "m" to config.bitmap.charMap["m"],
        "n" to config.bitmap.charMap["n"],
        "o" to config.bitmap.charMap["o"],
        "p" to config.bitmap.charMap["p"],
        "q" to config.bitmap.charMap["q"],
        "r" to config.bitmap.charMap["r"],
        "s" to config.bitmap.charMap["s"],
        "t" to config.bitmap.charMap["t"],
        "u" to config.bitmap.charMap["u"],
        "v" to config.bitmap.charMap["v"],
        "w" to config.bitmap.charMap["w"],
        "x" to config.bitmap.charMap["x"],
        "y" to config.bitmap.charMap["y"],
        "z" to config.bitmap.charMap["z"],
        "zero" to config.bitmap.charMap["0"],
        "one" to config.bitmap.charMap["1"],
        "two" to config.bitmap.charMap["2"],
        "three" to config.bitmap.charMap["3"],
        "four" to config.bitmap.charMap["4"],
        "five" to config.bitmap.charMap["5"],
        "six" to config.bitmap.charMap["6"],
        "seven" to config.bitmap.charMap["7"],
        "eight" to config.bitmap.charMap["8"],
        "nine" to config.bitmap.charMap["9"],

        "acuteAccent" to config.acuteAccent.char,
        "ampersand" to config.ampersand.char,
        "bracketClose" to config.bracketClose.char,
        "bracketOpen" to config.bracketOpen.char,
        "circumflex" to config.circumflex.char,
        "colon" to config.colon.char,
        "comma" to config.comma.char,
        "curlyBracketOpen" to config.curlyBracketOpen.char,
        "curlyBracketClose" to config.curlyBracketClose.char,
        "degree" to config.degree.char,
        "dollar" to config.dollar.char,
        "dot" to config.dot.char,
        "doubleQuote" to config.doubleQuote.char,
        "euro" to config.euro.char,
        "exclamationMark" to config.exclamationMark.char,
        "graveAccent" to config.graveAccent.char,
        "greaterThan" to config.greaterThan.char,
        "hyphen" to config.hyphen.char,
        "lessThan" to config.lessThan.char,
        "pipe" to config.pipe.char,
        "questionMark" to config.questionMark.char,
        "section" to config.section.char,
        "singleQuote" to config.singleQuote.char,
        "semicolon" to config.semicolon.char,
        "tilde" to config.tilde.char,
        "squareBracketOpen" to config.squareBracketOpen.char,
        "squareBracketClose" to config.squareBracketClose.char,
        "spacerOne" to config.spacerOne.char,
        "spacerTwo" to config.spacerTwo.char,

        "bitmapName" to config.bitmapName,
    )

    var filledTemplate = templateContent
    for ((key, value) in replacements) {
        filledTemplate = filledTemplate.replace("{{$key}}", value.toString())
    }

    filledTemplate = filledTemplate.replace("TemplateBitmap", configName)

    val configOutputPath = outputPath / "$configName.kt"
    Files.createDirectories(configOutputPath.parent)

    configOutputPath.writeText(filledTemplate)
}

suspend fun revertConfigKeys(configPath: Path, bitmapName: String): String =
    withContext(Dispatchers.IO) {
        if (!Files.exists(configPath)) {
            return@withContext ""
        }

        val fileContent = Files.readString(configPath)
        val keyRegex = Regex("""(?m)^${bitmapName}_([a-z0-9_]+):""")

        return@withContext fileContent.replace(keyRegex) { match ->
            val originalKey = match.groupValues[1]
            "$originalKey:"
        }
    }

suspend fun replaceConfigKeys(config: LetterConfig, configPath: Path): GeneratorResult =
    withContext(Dispatchers.IO) {
        val bitmapName = config.bitmapName
        val configFilePath = configPath / "$bitmapName.yml"

        val fileContent = Files.readString(configFilePath)
        val keyRegex = Regex("""(?m)^([a-z0-9_]+):""")

        val updatedContent = fileContent.replace(keyRegex) { match ->
            val key = match.groupValues[1]
            "${bitmapName}_$key:"
        }

        configFilePath.writeText(updatedContent)

        return@withContext GeneratorResult.FILE_GENERATED
    }