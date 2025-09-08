package dev.slne.surf.bitmap.common.head.session

import kotlinx.serialization.Serializable

@Serializable
data class PlayerSessionProperty(
    val name: String,
    val value: String,
)