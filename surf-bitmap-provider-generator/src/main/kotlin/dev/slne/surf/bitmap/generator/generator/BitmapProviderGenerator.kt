package dev.slne.surf.bitmap.generator.generator

import dev.slne.surf.bitmap.generator.config.BitmapConfig
import dev.slne.surf.bitmap.generator.config.LetterConfig
import dev.slne.surf.bitmap.generator.config.OneLetterConfig
import dev.slne.surf.bitmap.generator.plugin
import dev.slne.surf.bitmap.generator.utils.recolorFolder
import dev.slne.surf.bitmap.generator.utils.replaceConfigKeys
import dev.slne.surf.bitmap.generator.utils.writeConfig
import java.nio.file.Path
import kotlin.io.path.div

class BitmapProviderGenerator(
    val name: String,
    val foregroundHex: String,
    val backgroundHex: String,
    val configPath: Path,
    val texturePath: Path,
    val override: Boolean = false,
) {

    suspend fun generate(): GeneratorResult {
        val textureResult = generateTextures()
        val configResult = generateConfig()

        return textureResult + configResult
    }

    private fun checkPathExists(path: Path) = path.toFile().exists()
    private fun checkPath(path: Path): Boolean = !checkPathExists(path) || override

    private suspend fun generateTextures(): GeneratorResult {
        if (!checkPath(texturePath)) {
            return GeneratorResult.FILE_ALREADY_EXISTS
        }

        return recolorFolder(
            foregroundHex = foregroundHex,
            backgroundHex = backgroundHex,
            inputPath = plugin.dataPath / "raw",
            outputPath = texturePath,
        )
    }

    private suspend fun generateConfig(): GeneratorResult {
        if (!checkPath(configPath / "$name.yml")) {
            return GeneratorResult.FILE_ALREADY_EXISTS
        }

        val texturePathString = texturePath.toString()
            .replace("plugins\\Nexo\\pack\\assets\\minecraft\\textures\\", "")
            .replace("plugins/Nexo/pack/assets/minecraft/textures/", "")
            .replace("\\", "/")

        val config = LetterConfig(
            bitmapName = name,
            acuteAccent = OneLetterConfig("${texturePathString}/acute_accent.png"),
            ampersand = OneLetterConfig("${texturePathString}/ampersand.png"),
            bracketClose = OneLetterConfig("${texturePathString}/bracket_close.png"),
            bracketOpen = OneLetterConfig("${texturePathString}/bracket_open.png"),
            circumflex = OneLetterConfig("${texturePathString}/circumflex.png"),
            colon = OneLetterConfig("${texturePathString}/colon.png"),
            comma = OneLetterConfig("${texturePathString}/comma.png"),
            curlyBracketOpen = OneLetterConfig("${texturePathString}/curly_bracket_open.png"),
            curlyBracketClose = OneLetterConfig("${texturePathString}/curly_bracket_close.png"),
            degree = OneLetterConfig("${texturePathString}/degree.png"),
            dollar = OneLetterConfig("${texturePathString}/dollar.png"),
            dot = OneLetterConfig("${texturePathString}/dot.png"),
            doubleQuote = OneLetterConfig("${texturePathString}/double_quote.png"),
            euro = OneLetterConfig("${texturePathString}/euro.png"),
            exclamationMark = OneLetterConfig("${texturePathString}/exclamation_mark.png"),
            graveAccent = OneLetterConfig("${texturePathString}/grave_accent.png"),
            greaterThan = OneLetterConfig("${texturePathString}/greaterThan.png"),
            hyphen = OneLetterConfig("${texturePathString}/hyphen.png"),
            lessThan = OneLetterConfig("${texturePathString}/less_than.png"),
            pipe = OneLetterConfig("${texturePathString}/pipe.png"),
            questionMark = OneLetterConfig("${texturePathString}/question_mark.png"),
            section = OneLetterConfig("${texturePathString}/section.png"),
            semicolon = OneLetterConfig("${texturePathString}/semicolon.png"),
            singleQuote = OneLetterConfig("${texturePathString}/single_quote.png"),
            spacerOne = OneLetterConfig("${texturePathString}/spacer_one.png"),
            spacerTwo = OneLetterConfig("${texturePathString}/spacer_two.png"),
            squareBracketClose = OneLetterConfig("${texturePathString}/square_bracket_close.png"),
            squareBracketOpen = OneLetterConfig("${texturePathString}/square_bracket_open.png"),
            tilde = OneLetterConfig("${texturePathString}/tilde.png"),
            bitmap = BitmapConfig(
                texture = "${texturePathString}/bitmap.png",
                rows = 8,
                columns = 6
            ),
        )

        val writeResult = writeConfig(
            name = name,
            configPath = configPath,
            config = config,
        )

        if (writeResult != GeneratorResult.FILE_GENERATED) {
            return writeResult
        }

        return replaceConfigKeys(config, configPath)
    }

}