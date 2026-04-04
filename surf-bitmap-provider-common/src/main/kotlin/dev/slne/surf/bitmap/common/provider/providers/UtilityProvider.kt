package dev.slne.surf.bitmap.common.provider.providers

import dev.slne.surf.api.core.util.mutableObject2ObjectMapOf
import dev.slne.surf.bitmap.common.provider.CharProvider
import dev.slne.surf.bitmap.common.provider.Provider

/**
 * UtilityProvider is a singleton implementation of the [Provider] interface that maps a predefined set
 * of special characters to their corresponding [CharProvider] representations. Each character has a unique
 * [CharProvider] instance associated with it, representing the character and its width.
 *
 * The available mappings include a wide variety of symbols and punctuation marks, such as '+', '-', '*',
 * '/', '=', and others, to allow for flexible rendering or manipulation of these characters within
 * the bitmap-related contexts.
 *
 * This class uses an internal map to store these character mappings for efficient retrieval.
 *
 * @constructor This is a singleton object and does not require explicit instantiation.
 */
data object UtilityProvider : Provider {
    val plus = CharProvider('ꐫ', 6)
    val underscore = CharProvider('ꐹ', 6)
    val percent = CharProvider('ꐺ', 6)
    val equal = CharProvider('ꐻ', 6)
    val hash = CharProvider('ꐼ', 6)
    val star = CharProvider('ꐽ', 6)
    val acuteAccent = CharProvider('ꑂ', 3)
    val ampersand = CharProvider('ꑃ', 7)
    val bracketClose = CharProvider('ꑄ', 3)
    val bracketOpen = CharProvider('ꑅ', 3)
    val circumflex = CharProvider('ꑆ', 4)
    val colon = CharProvider('ꑇ', 2)
    val comma = CharProvider('ꑉ', 3)
    val curlyBracketOpen = CharProvider('ꑊ', 4)
    val curlyBracketClose = CharProvider('ꑋ', 4)
    val degree = CharProvider('ꑌ', 4)
    val dollar = CharProvider('ꑍ', 8)
    val dot = CharProvider('ꑔ', 2)
    val doubleQuote = CharProvider('ꑕ', 4)
    val euro = CharProvider('ꑖ', 9)
    val exclamationMark = CharProvider('ꑗ', 2)
    val graveAccent = CharProvider('ꑘ', 3)
    val greaterThan = CharProvider('ꑙ', 4)
    val hyphen = CharProvider('ꐬ', 6)
    val lessThan = CharProvider('ꑛ', 4)
    val pipe = CharProvider('ꑜ', 2)
    val questionMark = CharProvider('ꑝ', 5)
    val section = CharProvider('ꑞ', 8)
    val semicolon = CharProvider('ꑟ', 3)
    val singleQuote = CharProvider('ꑠ', 2)
    val squareBracketClose = CharProvider('ꑡ', 3)
    val squareBracketOpen = CharProvider('ꑢ', 3)
    val tilde = CharProvider('ꑣ', 5)
    val slash = CharProvider('ꐭ', 6)
    val backslash = CharProvider('ꐮ', 6)

    private val charMap = mutableObject2ObjectMapOf(
        '+' to plus,
        '_' to underscore,
        '%' to percent,
        '=' to equal,
        '#' to hash,
        '*' to star,
        '´' to acuteAccent,
        '&' to ampersand,
        ')' to bracketClose,
        '(' to bracketOpen,
        '^' to circumflex,
        ':' to colon,
        ',' to comma,
        '{' to curlyBracketOpen,
        '}' to curlyBracketClose,
        '°' to degree,
        '$' to dollar,
        '.' to dot,
        '\"' to doubleQuote,
        '€' to euro,
        '!' to exclamationMark,
        '`' to graveAccent,
        '>' to greaterThan,
        '-' to hyphen,
        '<' to lessThan,
        '|' to pipe,
        '?' to questionMark,
        '§' to section,
        ';' to semicolon,
        '\'' to singleQuote,
        ']' to squareBracketClose,
        '[' to squareBracketOpen,
        '~' to tilde,
        '/' to slash,
        '\\' to backslash,
    )

    override fun findChar(input: Char): CharProvider? = charMap[input]
}
