package dev.slne.surf.bitmap.common.head.texture

import kotlinx.serialization.Serializable

@Serializable
data class TextureProperty(
    val url: String,
    val metadata: TexturePropertyMetadata? = null
)