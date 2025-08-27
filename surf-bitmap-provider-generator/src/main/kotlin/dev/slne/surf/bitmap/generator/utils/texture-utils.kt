package dev.slne.surf.bitmap.generator.utils

import dev.slne.surf.bitmap.generator.generator.GeneratorResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.file.Path
import javax.imageio.ImageIO
import kotlin.io.path.*
import kotlin.math.abs

suspend fun recolorFolder(
    foregroundHex: String,
    shadowHex: String,
    backgroundHex: String,
    inputPath: Path,
    outputPath: Path,
    inputForegroundHex: String = "ffffff",
    inputShadowHex: String = "808080",
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
                shadowHex,
                backgroundHex,
                inputFilePath,
                outputFilePath,
                inputForegroundHex,
                inputShadowHex,
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
    shadowHex: String,
    backgroundHex: String,
    inputPath: Path,
    outputPath: Path,
    inputForegroundHex: String = "ffffff",
    inputShadowHex: String = "808080",
    inputBackgroundHex: String = "000000",
): GeneratorResult = withContext(Dispatchers.IO) {
    val inputForeground = inputForegroundHex.toInt(16)
    val inputShadow = inputShadowHex.toInt(16)
    val inputBackground = inputBackgroundHex.toInt(16)
    val foreground = foregroundHex.toInt(16)
    val shadow = shadowHex.toInt(16)
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
                inputShadow -> image.setRGB(x, y, shadow or (0xFF shl 24))
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

fun hexToHsl(hex: String): FloatArray {
    val r = Integer.valueOf(hex.substring(0, 2), 16) / 255.0f
    val g = Integer.valueOf(hex.substring(2, 4), 16) / 255.0f
    val b = Integer.valueOf(hex.substring(4, 6), 16) / 255.0f

    val max = maxOf(r, g, b)
    val min = minOf(r, g, b)
    val h: Float
    val s: Float
    val l = (max + min) / 2.0f

    if (max == min) {
        h = 0.0f
        s = 0.0f
    } else {
        val d = max - min
        s = if (l > 0.5f) d / (2.0f - max - min) else d / (max + min)

        h = when (max) {
            r -> (g - b) / d + if (g < b) 6 else 0
            g -> (b - r) / d + 2
            else -> (r - g) / d + 4
        } / 6.0f
    }

    return floatArrayOf(h * 360.0f, s * 100.0f, l * 100.0f)
}

fun hslToHex(hsl: FloatArray): String {
    val c = (1.0f - abs(2.0f * hsl[2] / 100.0f - 1.0f)) * (hsl[1] / 100.0f)
    val x = c * (1.0f - abs((hsl[0] / 60.0f) % 2 - 1.0f))
    val m = hsl[2] / 100.0f - c / 2.0f

    val (r1, g1, b1) = when {
        hsl[0] < 60.0f -> floatArrayOf(c, x, 0.0f)
        hsl[0] < 120.0f -> floatArrayOf(x, c, 0.0f)
        hsl[0] < 180.0f -> floatArrayOf(0.0f, c, x)
        hsl[0] < 240.0f -> floatArrayOf(0.0f, x, c)
        hsl[0] < 300.0f -> floatArrayOf(x, 0.0f, c)
        else -> floatArrayOf(c, 0.0f, x)
    }

    val r = ((r1 + m) * 255.0f).toInt().coerceIn(0, 255)
    val g = ((g1 + m) * 255.0f).toInt().coerceIn(0, 255)
    val b = ((b1 + m) * 255.0f).toInt().coerceIn(0, 255)

    return String.format("%02x%02x%02x", r, g, b)
}

fun darkenHex(hex: String, factor: Float): String {
    val hsl = hexToHsl(hex)
    hsl[2] = (hsl[2] * factor).coerceIn(0.0f, 100.0f)

    return hslToHex(hsl)
}

fun lightenHex(hex: String, factor: Float): String {
    val hsl = hexToHsl(hex)
    hsl[2] = (hsl[2] + (100.0f - hsl[2]) * factor).coerceIn(0.0f, 100.0f)

    return hslToHex(hsl)
}