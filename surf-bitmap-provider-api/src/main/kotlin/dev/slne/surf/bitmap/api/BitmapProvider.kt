package dev.slne.surf.bitmap.api

open class BitmapProvider(
    a: String,
    b: String,
    c: String,
    d: String,
    e: String,
    f: String,
    g: String,
    h: String,
    i: String,
    j: String,
    k: String,
    l: String,
    m: String,
    n: String,
    o: String,
    p: String,
    q: String,
    r: String,
    s: String,
    t: String,
    u: String,
    v: String,
    w: String,
    x: String,
    y: String,
    z: String,
    zero: String,
    one: String,
    two: String,
    three: String,
    four: String,
    five: String,
    six: String,
    seven: String,
    eight: String,
    nine: String,
    acuteAccent: String,
    ampersand: String,
    bracketClose: String,
    bracketOpen: String,
    circumflex: String,
    colon: String,
    comma: String,
    curlyBracketOpen: String,
    curlyBracketClose: String,
    degree: String,
    dollar: String,
    dot: String,
    doubleQuote: String,
    euro: String,
    exclamationMark: String,
    graveAccent: String,
    greaterThan: String,
    hyphen: String,
    lessThan: String,
    pipe: String,
    questionMark: String,
    section: String,
    semicolon: String,
    singleQuote: String,
    squareBracketClose: String,
    squareBracketOpen: String,
    tilde: String,
    private val spacerOne: String,
    private val spacerTwo: String,
) {
    private val translatedLettersToGlyph = mapOf(
        "a" to a,
        "b" to b,
        "c" to c,
        "d" to d,
        "e" to e,
        "f" to f,
        "g" to g,
        "h" to h,
        "i" to i,
        "j" to j,
        "k" to k,
        "l" to l,
        "m" to m,
        "n" to n,
        "o" to o,
        "p" to p,
        "q" to q,
        "r" to r,
        "s" to s,
        "t" to t,
        "u" to u,
        "v" to v,
        "w" to w,
        "x" to x,
        "y" to y,
        "z" to z,
        "0" to zero,
        "1" to one,
        "2" to two,
        "3" to three,
        "4" to four,
        "5" to five,
        "6" to six,
        "7" to seven,
        "8" to eight,
        "9" to nine,
        "´" to acuteAccent,
        "&" to ampersand,
        ")" to bracketClose,
        "(" to bracketOpen,
        "^" to circumflex,
        ":" to colon,
        "," to comma,
        "{" to curlyBracketOpen,
        "}" to curlyBracketClose,
        "°" to degree,
        "$" to dollar,
        "." to dot,
        "\"" to doubleQuote,
        "€" to euro,
        "!" to exclamationMark,
        "`" to graveAccent,
        ">" to greaterThan,
        "-" to hyphen,
        "<" to lessThan,
        "|" to pipe,
        "?" to questionMark,
        "§" to section,
        ";" to semicolon,
        "'" to singleQuote,
        "]" to squareBracketClose,
        "[" to squareBracketOpen,
        "~" to tilde,
        "\t" to spacerOne,
        "\n" to spacerTwo,
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
    open fun translateToGlyphs(input: String) =
        input.map { translatedLettersToGlyph[it.toString().lowercase()] ?: it }.toList()

    /**
     * Translate a string to a formatted string with glyphs.
     * This function translates the input string to glyphs and formats it with spacers and negative space characters.
     *
     * @param input The input string to be translated.
     * @return A formatted string with glyphs, spacers, and negative space characters.
     */
    open fun translateToString(input: String): String {
        val translated = translateToGlyphs(input)

        return "$spacerTwo${Spacing.NEGATIVE_SPACE_ONE.char}${translated.joinToString(Spacing.NEGATIVE_SPACE_ONE.char)}${Spacing.NEGATIVE_SPACE_ONE.char}$spacerOne"
    }
}