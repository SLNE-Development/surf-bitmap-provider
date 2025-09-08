package dev.slne.surf.bitmap.common.provider.providers

import dev.slne.surf.bitmap.common.provider.CharProvider
import dev.slne.surf.bitmap.common.provider.Provider
import dev.slne.surf.bitmap.common.provider.providers.NumberProvider.findChar
import dev.slne.surf.surfapi.core.api.util.mutableObject2ObjectMapOf

/**
 * NumberProvider is a singleton implementation of the [Provider] interface that provides a mapping
 * of numeric characters ('0' through '9') to corresponding [CharProvider] instances. Each numeric character
 * is associated with a [CharProvider] representation, which specifies the character and its width.
 *
 * This class uses an internal map to store the mappings between numeric characters and their respective
 * [CharProvider] instances. It allows for efficient retrieval of the mapped [CharProvider] by providing
 * the numeric character as input.
 *
 * The primary use of this class is to handle mappings for numeric characters in contexts where character
 * rendering or manipulation is required, such as in bitmap representations.
 *
 * It overrides the [findChar] function from the [Provider] interface to retrieve the corresponding
 * [CharProvider] instance for a given numeric character.
 *
 * @constructor This is a singleton object and does not require explicit instantiation.
 */
data object NumberProvider : Provider {
    val zero = CharProvider('ꐯ', 6)
    val one = CharProvider('ꐰ', 6)
    val two = CharProvider('ꐱ', 6)
    val three = CharProvider('ꐲ', 6)
    val four = CharProvider('ꐳ', 6)
    val five = CharProvider('ꐴ', 6)
    val six = CharProvider('ꐵ', 6)
    val seven = CharProvider('ꐶ', 6)
    val eight = CharProvider('ꐷ', 6)
    val nine = CharProvider('ꐸ', 6)

    private val charMap = mutableObject2ObjectMapOf(
        '0' to zero,
        '1' to one,
        '2' to two,
        '3' to three,
        '4' to four,
        '5' to five,
        '6' to six,
        '7' to seven,
        '8' to eight,
        '9' to nine,
    )

    override fun findChar(input: Char): CharProvider? = charMap[input]
}
