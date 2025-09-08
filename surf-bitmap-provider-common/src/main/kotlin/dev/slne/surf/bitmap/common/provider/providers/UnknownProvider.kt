package dev.slne.surf.bitmap.common.provider.providers

import dev.slne.surf.bitmap.common.provider.CharProvider
import dev.slne.surf.bitmap.common.provider.Provider

/**
 * UnknownProvider is a singleton implementation of the [Provider] interface that provides
 * a default [CharProvider] instance for any given input character. This implementation
 * always maps the input character to a [CharProvider] object having the same character
 * and a default width of 0.
 *
 * This class can be used as a fallback implementation when no specific mapping for a
 * character is provided by other [Provider] implementations.
 *
 * @constructor This is a singleton object and does not require explicit instantiation.
 */
data object UnknownProvider : Provider {
    override fun findChar(input: Char) = CharProvider(
        input, 0
    )
}