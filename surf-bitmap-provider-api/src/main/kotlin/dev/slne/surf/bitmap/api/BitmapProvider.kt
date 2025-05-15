package dev.slne.surf.bitmap.api

import dev.slne.surf.surfapi.core.api.util.char2CharMapOf
import dev.slne.surf.surfapi.core.api.util.mutableCharListOf
import it.unimi.dsi.fastutil.chars.CharList

open class BitmapProvider(
    val name: String,
    a: Char,
    b: Char,
    c: Char,
    d: Char,
    e: Char,
    f: Char,
    g: Char,
    h: Char,
    i: Char,
    j: Char,
    k: Char,
    l: Char,
    m: Char,
    n: Char,
    o: Char,
    p: Char,
    q: Char,
    r: Char,
    s: Char,
    t: Char,
    u: Char,
    v: Char,
    w: Char,
    x: Char,
    y: Char,
    z: Char,
    zero: Char,
    one: Char,
    two: Char,
    three: Char,
    four: Char,
    five: Char,
    six: Char,
    seven: Char,
    eight: Char,
    nine: Char,
    acuteAccent: Char,
    ampersand: Char,
    bracketClose: Char,
    bracketOpen: Char,
    circumflex: Char,
    colon: Char,
    comma: Char,
    curlyBracketOpen: Char,
    curlyBracketClose: Char,
    degree: Char,
    dollar: Char,
    dot: Char,
    doubleQuote: Char,
    euro: Char,
    exclamationMark: Char,
    graveAccent: Char,
    greaterThan: Char,
    hyphen: Char,
    lessThan: Char,
    pipe: Char,
    questionMark: Char,
    section: Char,
    semicolon: Char,
    singleQuote: Char,
    squareBracketClose: Char,
    squareBracketOpen: Char,
    tilde: Char,
    private val spacerOne: Char,
    private val spacerTwo: Char,
) {
    private val translatedLettersToGlyph = char2CharMapOf(
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
        '\t' to spacerOne,
        '\n' to spacerTwo,
    )

    /**
     * Translate a string to glyphs using the provided mapping.
     *
     * This function maps each character in the input string to its corresponding glyph
     * using the `translatedLettersToGlyph` map. If a character is not found in the map,
     * it remains unchanged in the output.
     *
     * @param input The input string to be translated.
     * @return A list of characters where each character is either a glyph or the original character.
     */
    open fun translateToGlyphs(input: String): CharList = input
        .mapTo(mutableCharListOf()) {
            translatedLettersToGlyph.getOrDefault(
                it.lowercaseChar(),
                it
            )
        }

    /**
     * Translate a string to a formatted string with glyphs.
     * This function translates the input string to glyphs and formats it with spacers and negative space characters.
     *
     * @param input The input string to be translated.
     * @return A formatted string with glyphs, spacers, and negative space characters.
     */
    open fun translateToString(input: String): String {
        val translated = translateToGlyphs(input)
        val estimatedLength = translated.size * 2 + 4

        return buildString(estimatedLength) {
            append(spacerTwo)
            append(Spacing.NEGATIVE_SPACE_ONE.char)

            for (i in 0 until translated.size) {
                append(translated.getChar(i))
                if (i != translated.size - 1) {
                    append(Spacing.NEGATIVE_SPACE_ONE.char)
                }
            }

            append(Spacing.NEGATIVE_SPACE_ONE.char)
            append(spacerOne)
        }
    }
}