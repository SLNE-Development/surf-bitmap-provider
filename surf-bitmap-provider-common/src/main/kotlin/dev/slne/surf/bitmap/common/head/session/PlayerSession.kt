package dev.slne.surf.bitmap.common.head.session

import kotlinx.serialization.Serializable

@Serializable
data class PlayerSession(
    val id: String,
    val name: String,
    val properties: List<PlayerSessionProperty>,
)