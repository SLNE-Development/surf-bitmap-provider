package dev.slne.surf.bitmap.common.head.texture

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TexturesProperty(
    @SerialName("SKIN")
    val skin: TextureProperty? = null,

    @SerialName("CAPE")
    val cape: TextureProperty? = null,
)