package dev.slne.surf.bitmap.generator.config

import org.spongepowered.configurate.objectmapping.ConfigSerializable
import org.spongepowered.configurate.objectmapping.meta.Setting

@ConfigSerializable
data class BitmapConfig(
    val texture: String,
    val ascent: Int = 7,
    val height: Int = 7,

    val rows: Int? = null,
    val columns: Int? = null,

    @Setting("char")
    val char: List<String>? = null,
) {
    val charMap: Map<String, Char>
        get() = run {
            val chars = char ?: return@run emptyMap<String, Char>()

            return@run mapOf(
                "a" to chars[0][0],
                "b" to chars[0][1],
                "c" to chars[0][2],
                "d" to chars[0][3],
                "e" to chars[0][4],
                "f" to chars[0][5],

                "g" to chars[1][0],
                "h" to chars[1][1],
                "i" to chars[1][2],
                "j" to chars[1][3],
                "k" to chars[1][4],
                "l" to chars[1][5],

                "m" to chars[2][0],
                "n" to chars[2][1],
                "o" to chars[2][2],
                "p" to chars[2][3],
                "q" to chars[2][4],
                "r" to chars[2][5],

                "s" to chars[3][0],
                "t" to chars[3][1],
                "u" to chars[3][2],
                "v" to chars[3][3],
                "w" to chars[3][4],
                "x" to chars[3][5],

                "y" to chars[4][0],
                "z" to chars[4][1],
                "+" to chars[4][2],
                "-" to chars[4][3],
                "/" to chars[4][4],
                "\\" to chars[4][5],

                "0" to chars[5][0],
                "1" to chars[5][1],
                "2" to chars[5][2],
                "3" to chars[5][3],
                "4" to chars[5][4],
                "5" to chars[5][5],

                "6" to chars[6][0],
                "7" to chars[6][1],
                "8" to chars[6][2],
                "9" to chars[6][3],
                "_" to chars[6][4],
                "%" to chars[6][5],

                "=" to chars[7][0],
                "#" to chars[7][1],
                "*" to chars[7][2]
            )
        }
}
