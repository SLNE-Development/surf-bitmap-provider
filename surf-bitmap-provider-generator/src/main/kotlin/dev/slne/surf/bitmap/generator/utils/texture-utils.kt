package dev.slne.surf.bitmap.generator.utils

import dev.slne.surf.bitmap.generator.generator.GeneratorResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.file.Path
import javax.imageio.ImageIO

suspend fun recolorFolder(
    foregroundHex: String,
    backgroundHex: String,
    inputPath: Path,
    outputPath: Path,
    inputForegroundHex: String = "ffffff",
    inputBackgroundHex: String = "000000",
): GeneratorResult = withContext(Dispatchers.IO) {
    if (!inputPath.toFile().isDirectory) {
        throw IllegalArgumentException("Input path is not a directory")
    }

    if (!outputPath.toFile().exists()) {
        outputPath.toFile().mkdirs()
    }

    val files =
        inputPath.toFile().listFiles() ?: return@withContext GeneratorResult.FILE_NOT_GENERATED

    var result = GeneratorResult.FILE_GENERATED

    for (file in files) {
        if (file.isDirectory) {
            continue
        }

        val inputFilePath = file.toPath()
        val outputFilePath = outputPath.resolve(file.name)

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

    return@withContext result
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
    
    val image = runCatching { ImageIO.read(inputPath.toFile()) }.getOrElse {
        return@withContext GeneratorResult.FILE_NOT_GENERATED
    }
    val width = image.width
    val height = image.height

    for (x in 0 until width) {
        for (y in 0 until height) {
            val pixelArgb = image.getRGB(x, y)
            val pixel = pixelArgb and 0x00FFFFFF

            if (pixel == inputForeground) {
                image.setRGB(x, y, foreground or (0xFF shl 24))
            } else if (pixel == inputBackground) {
                image.setRGB(x, y, background or (0xFF shl 24))
            }
        }
    }

    return@withContext runCatching {
        ImageIO.write(image, "png", outputPath.toFile())
        GeneratorResult.FILE_GENERATED
    }.getOrNull() ?: GeneratorResult.FILE_NOT_GENERATED
}