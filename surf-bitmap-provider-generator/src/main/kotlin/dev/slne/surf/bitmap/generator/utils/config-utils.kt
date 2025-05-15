package dev.slne.surf.bitmap.generator.utils

import dev.slne.surf.bitmap.generator.config.LetterConfig
import dev.slne.surf.bitmap.generator.generator.GeneratorResult
import dev.slne.surf.bitmap.generator.plugin
import dev.slne.surf.surfapi.core.api.util.object2ObjectMapOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.spongepowered.configurate.kotlin.extensions.get
import org.spongepowered.configurate.kotlin.objectMapperFactory
import org.spongepowered.configurate.yaml.YamlConfigurationLoader
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.*

suspend fun readConfig(
    configPath: Path
): LetterConfig? = withContext(Dispatchers.IO) {
    val bitmapName = configPath.fileName.toString().removeSuffix(".yml")
    val revertedConfig = revertConfigKeys(configPath, bitmapName)
    val node = YamlConfigurationLoader.builder()
        .path(configPath)
        .defaultOptions { options ->
            options.serializers { builder ->
                builder.registerAnnotatedObjects(objectMapperFactory())
            }
        }
        .buildAndLoadString(revertedConfig)

    node.get<LetterConfig>()
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

    runCatching {
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
    val charMap = config.bitmap.charMap

    val replacements = object2ObjectMapOf(
        "a" to charMap['a'],
        "b" to charMap['b'],
        "c" to charMap['c'],
        "d" to charMap['d'],
        "e" to charMap['e'],
        "f" to charMap['f'],
        "g" to charMap['g'],
        "h" to charMap['h'],
        "i" to charMap['i'],
        "j" to charMap['j'],
        "k" to charMap['k'],
        "l" to charMap['l'],
        "m" to charMap['m'],
        "n" to charMap['n'],
        "o" to charMap['o'],
        "p" to charMap['p'],
        "q" to charMap['q'],
        "r" to charMap['r'],
        "s" to charMap['s'],
        "t" to charMap['t'],
        "u" to charMap['u'],
        "v" to charMap['v'],
        "w" to charMap['w'],
        "x" to charMap['x'],
        "y" to charMap['y'],
        "z" to charMap['z'],
        "zero" to charMap['0'],
        "one" to charMap['1'],
        "two" to charMap['2'],
        "three" to charMap['3'],
        "four" to charMap['4'],
        "five" to charMap['5'],
        "six" to charMap['6'],
        "seven" to charMap['7'],
        "eight" to charMap['8'],
        "nine" to charMap['9'],
        "slash" to charMap['/'],
        "backslash" to charMap['\\'],
        "underscore" to charMap['_'],
        "plus" to charMap['+'],
        "percent" to charMap['%'],
        "equal" to charMap['='],
        "hash" to charMap['#'],
        "star" to charMap['*'],

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
    )

    var filledTemplate = templateContent
    for ((key, value) in replacements) {
        filledTemplate = filledTemplate.replace("{{$key}}", value.toString())
    }

    filledTemplate = filledTemplate.replace("{{bitmapName}}", config.bitmapName)
    filledTemplate = filledTemplate.replace("TemplateBitmap", configName)

    val configOutputPath = outputPath / "$configName.kt"
    configOutputPath.createParentDirectories()
    configOutputPath.writeText(filledTemplate)
}

suspend fun revertConfigKeys(configPath: Path, bitmapName: String): String =
    withContext(Dispatchers.IO) {
        if (configPath.notExists()) {
            return@withContext ""
        }

        val fileContent = configPath.readText()
        val keyRegex = Regex("""(?m)^${bitmapName}_([a-z0-9_]+):""")

        fileContent.replace(keyRegex) { match ->
            val originalKey = match.groupValues[1]
            "$originalKey:"
        }
    }

private val replaceConfigKeysPattern = """(?m)^([a-z0-9_]+):""".toRegex()
suspend fun replaceConfigKeys(config: LetterConfig, configPath: Path): GeneratorResult =
    withContext(Dispatchers.IO) {
        val bitmapName = config.bitmapName
        val configFilePath = configPath / "$bitmapName.yml"

        val fileContent = configFilePath.readText()
        val updatedContent = fileContent.replace(replaceConfigKeysPattern) { match ->
            val key = match.groupValues[1]
            "${bitmapName}_$key:"
        }

        configFilePath.writeText(updatedContent)

        GeneratorResult.FILE_GENERATED
    }