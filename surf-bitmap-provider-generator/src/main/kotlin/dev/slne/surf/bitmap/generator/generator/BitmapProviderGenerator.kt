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
import kotlin.io.path.notExists

class BitmapProviderGenerator(
    val name: String,
    val foregroundHex: String,
    val shadowHex: String,
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

    private fun checkPath(path: Path): Boolean = path.notExists() || override

    private suspend fun generateTextures(): GeneratorResult {
        if (!checkPath(texturePath)) {
            return GeneratorResult.FILE_ALREADY_EXISTS
        }

        return recolorFolder(
            foregroundHex = foregroundHex,
            shadowHex = shadowHex,
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
            .replace("plugins\\Nexo\\pack\\assets\\surf\\textures\\", "")
            .replace("plugins/Nexo/pack/assets/surf/textures/", "")
            .replace("\\", "/")

        val prefixedTexturePathString = "surf:$texturePathString"

        val config = LetterConfig(
            bitmapName = name,
            acuteAccent = OneLetterConfig("${prefixedTexturePathString}/acute_accent.png"),
            ampersand = OneLetterConfig("${prefixedTexturePathString}/ampersand.png"),
            bracketClose = OneLetterConfig("${prefixedTexturePathString}/bracket_close.png"),
            bracketOpen = OneLetterConfig("${prefixedTexturePathString}/bracket_open.png"),
            circumflex = OneLetterConfig("${prefixedTexturePathString}/circumflex.png"),
            colon = OneLetterConfig("${prefixedTexturePathString}/colon.png"),
            comma = OneLetterConfig("${prefixedTexturePathString}/comma.png"),
            curlyBracketOpen = OneLetterConfig("${prefixedTexturePathString}/curly_bracket_open.png"),
            curlyBracketClose = OneLetterConfig("${prefixedTexturePathString}/curly_bracket_close.png"),
            degree = OneLetterConfig("${prefixedTexturePathString}/degree.png"),
            dollar = OneLetterConfig("${prefixedTexturePathString}/dollar.png"),
            dot = OneLetterConfig("${prefixedTexturePathString}/dot.png"),
            doubleQuote = OneLetterConfig("${prefixedTexturePathString}/double_quote.png"),
            euro = OneLetterConfig("${prefixedTexturePathString}/euro.png"),
            exclamationMark = OneLetterConfig("${prefixedTexturePathString}/exclamation_mark.png"),
            graveAccent = OneLetterConfig("${prefixedTexturePathString}/grave_accent.png"),
            greaterThan = OneLetterConfig("${prefixedTexturePathString}/greater_than.png"),
            hyphen = OneLetterConfig("${prefixedTexturePathString}/hyphen.png"),
            lessThan = OneLetterConfig("${prefixedTexturePathString}/less_than.png"),
            pipe = OneLetterConfig("${prefixedTexturePathString}/pipe.png"),
            questionMark = OneLetterConfig("${prefixedTexturePathString}/question_mark.png"),
            section = OneLetterConfig("${prefixedTexturePathString}/section.png"),
            semicolon = OneLetterConfig("${prefixedTexturePathString}/semicolon.png"),
            singleQuote = OneLetterConfig("${prefixedTexturePathString}/single_quote.png"),
            spacerOne = OneLetterConfig("${prefixedTexturePathString}/spacer_one.png"),
            spacerTwo = OneLetterConfig("${prefixedTexturePathString}/spacer_two.png"),
            squareBracketClose = OneLetterConfig("${prefixedTexturePathString}/square_bracket_close.png"),
            squareBracketOpen = OneLetterConfig("${prefixedTexturePathString}/square_bracket_open.png"),
            tilde = OneLetterConfig("${prefixedTexturePathString}/tilde.png"),
            bitmap = BitmapConfig(
                texture = "${prefixedTexturePathString}/bitmap.png",
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