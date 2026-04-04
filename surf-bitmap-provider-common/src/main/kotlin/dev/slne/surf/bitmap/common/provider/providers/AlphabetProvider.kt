package dev.slne.surf.bitmap.common.provider.providers

import dev.slne.surf.api.core.util.mutableObject2ObjectMapOf
import dev.slne.surf.bitmap.common.provider.CharProvider
import dev.slne.surf.bitmap.common.provider.Provider
import dev.slne.surf.bitmap.common.provider.providers.AlphabetProvider.findChar

/**
 * AlphabetProvider is a singleton implementation of the [Provider] interface that provides
 * a mapping of alphabetic characters ('a' through 'z') to corresponding [CharProvider] instances.
 * Each letter is associated with a [CharProvider] representation, which specifies the character
 * and its width.
 *
 * This class uses an internal map to store the mappings between alphabetic characters and their
 * respective [CharProvider] instances. It allows for efficient retrieval of the mapped [CharProvider]
 * by providing the alphabetic character as input.
 *
 * The primary use of this class is to handle mappings for alphabetic characters in contexts where
 * character rendering or manipulation is required, such as in bitmap representations.
 *
 * It overrides the [findChar] function from the [Provider] interface to retrieve the corresponding
 * [CharProvider] instance for a given alphabetic character.
 *
 * @constructor This is a singleton object and does not require explicit instantiation.
 */
data object AlphabetProvider : Provider {
    val a = CharProvider('ꐑ', 6)
    val b = CharProvider('ꐒ', 6)
    val c = CharProvider('ꐓ', 6)
    val d = CharProvider('ꐔ', 6)
    val e = CharProvider('ꐕ', 6)
    val f = CharProvider('ꐖ', 6)
    val g = CharProvider('ꐗ', 6)
    val h = CharProvider('ꐘ', 6)
    val i = CharProvider('ꐙ', 6)
    val j = CharProvider('ꐚ', 6)
    val k = CharProvider('ꐛ', 6)
    val l = CharProvider('ꐜ', 6)
    val m = CharProvider('ꐝ', 6)
    val n = CharProvider('ꐞ', 6)
    val o = CharProvider('ꐟ', 6)
    val p = CharProvider('ꐠ', 6)
    val q = CharProvider('ꐡ', 6)
    val r = CharProvider('ꐢ', 6)
    val s = CharProvider('ꐣ', 6)
    val t = CharProvider('ꐤ', 6)
    val u = CharProvider('ꐥ', 6)
    val v = CharProvider('ꐦ', 6)
    val w = CharProvider('ꐧ', 6)
    val x = CharProvider('ꐨ', 6)
    val y = CharProvider('ꐩ', 6)
    val z = CharProvider('ꐪ', 6)

    private val charMap = mutableObject2ObjectMapOf(
        'a' to a,
        'b' to b,
        'c' to c,
        'd' to d,
        'e' to e,
        'f' to f,
        'g' to g,
        'h' to h,
        'i' to i,
        'j' to j,
        'k' to k,
        'l' to l,
        'm' to m,
        'n' to n,
        'o' to o,
        'p' to p,
        'q' to q,
        'r' to r,
        's' to s,
        't' to t,
        'u' to u,
        'v' to v,
        'w' to w,
        'x' to x,
        'y' to y,
        'z' to z,
    )

    override fun findChar(input: Char): CharProvider? = charMap[input]
}
