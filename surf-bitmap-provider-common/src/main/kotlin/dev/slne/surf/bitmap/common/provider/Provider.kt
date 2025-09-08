package dev.slne.surf.bitmap.common.provider

/**
 * Represents a functional interface that provides a mechanism for retrieving a corresponding
 * [CharProvider] instance for a given character. Implementations define specific mappings
 * between input characters and their respective [CharProvider] objects.
 */
fun interface Provider {

    /**
     * Finds and provides a `CharProvider` corresponding to the given character.
     *
     * @param input The character for which a `CharProvider` should be found.
     * @return A `CharProvider` instance if a match is found, or `null` if no match is available.
     */
    fun findChar(input: Char): CharProvider?

}