package dev.slne.surf.bitmap.generator.utils

import dev.slne.surf.bitmap.generator.generator.GeneratorResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.file.Path
import javax.imageio.ImageIO
import kotlin.io.path.*

@OptIn(ExperimentalPathApi::class)
suspend fun recolorFolder(
    foregroundHex: String,
    backgroundHex: String,
    inputPath: Path,
    outputPath: Path,
    inputForegroundHex: String = "ffffff",
    inputBackgroundHex: String = "000000",
): GeneratorResult = withContext(Dispatchers.IO) {
    require(inputPath.isDirectory()) { "Input path is not a directory" }
    outputPath.createDirectories()

    inputPath.listDirectoryEntries().filter { it.isRegularFile() }

    val files = inputPath.listDirectoryEntries().filter { it.isRegularFile() }

    if (files.isNotEmpty()) {
        var result = GeneratorResult.FILE_GENERATED

        for (inputFilePath in files) {
            val outputFilePath = outputPath.resolve(inputFilePath.name)
            val recolorResult = recolorTexture(
                foregroundHex,
                backgroundHex,
                inputFilePath,
                outputFilePath,
                inputForegroundHex,
                inputBackgroundHex
            )

            result += recolorResult
        }

        result
    } else {
        GeneratorResult.FILE_NOT_GENERATED
    }
}

suspend fun recolorTexture(
    foregroundHex: String,
    backgroundHex: String,
    inputPath: Path,
    outputPath: Path,
    inputForegroundHex: String = "ffffff",
    inputBackgroundHex: String = "000000",
): GeneratorResult = withContext(Dispatchers.IO) {
    val inputForeground = inputForegroundHex.toInt(16)
    val inputBackground = inputBackgroundHex.toInt(16)
    val foreground = foregroundHex.toInt(16)
    val background = backgroundHex.toInt(16)

    val image = runCatching { inputPath.inputStream().use { ImageIO.read(it) } }.getOrNull()
        ?: return@withContext GeneratorResult.FILE_NOT_GENERATED

    val width = image.width
    val height = image.height

    for (x in 0 until width) {
        for (y in 0 until height) {
            val pixelArgb = image.getRGB(x, y)
            val pixel = pixelArgb and 0x00FFFFFF

            when (pixel) {
                inputForeground -> image.setRGB(x, y, foreground or (0xFF shl 24))
                inputBackground -> image.setRGB(x, y, background or (0xFF shl 24))
            }
        }
    }

    return@withContext runCatching {
        outputPath.outputStream().use { ImageIO.write(image, "png", it) }
        GeneratorResult.FILE_GENERATED
    }.getOrElse {
        GeneratorResult.FILE_NOT_GENERATED
    }
}