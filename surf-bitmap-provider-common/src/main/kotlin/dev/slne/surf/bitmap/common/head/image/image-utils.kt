package dev.slne.surf.bitmap.common.head.image

import dev.slne.surf.bitmap.common.head.*
import net.kyori.adventure.text.format.TextColor
import java.awt.Color
import java.awt.Image
import java.awt.image.BufferedImage
import java.net.URL
import javax.imageio.ImageIO

fun getFirstHeadPixelsFromImage(image: Image) =
    getPixelsFromImage(image, HEAD_OFFSET_X, HEAD_OFFSET_Y)

fun getSecondHeadPixelsFromImage(image: Image) =
    getPixelsFromImage(image, SECOND_HEAD_OFFSET_X, SECOND_HEAD_OFFSET_Y)

private fun getPixelsFromImage(image: Image, offsetX: Int, offsetY: Int): HeadArray {
    val bufferedImage = convertImageToBufferedImage(image)
    val rows = Array(8) { Array<TextColor?>(8) { null } }

    for (y in 0 until HEAD_SIZE) {
        for (x in 0 until HEAD_SIZE) {
            val rgb = bufferedImage.getRGB(offsetX + x, offsetY + y)
            val color = Color(rgb, true)

            rows[x][y] = if (color.alpha == 0) {
                null
            } else {
                TextColor.color(color.red, color.green, color.blue)
            }
        }
    }

    return rows
}

fun convertImageToBufferedImage(image: Image): BufferedImage {
    return if (image is BufferedImage) {
        image
    } else {
        val bufferedImage =
            BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB)
        val graphics = bufferedImage.createGraphics()

        graphics.drawImage(image, 0, 0, null)
        graphics.dispose()

        bufferedImage
    }
}

fun getImageFromUrl(url: URL): Image {
    val connection = url.openConnection()

    connection.addRequestProperty("User-Agent", "Mozilla/5.0")
    connection.connect()

    return ImageIO.read(connection.getInputStream())
}