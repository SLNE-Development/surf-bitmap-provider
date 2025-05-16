package dev.slne.surf.bitmap.generator.config

import org.spongepowered.configurate.objectmapping.ConfigSerializable

@ConfigSerializable
data class OneLetterConfig(
    val texture: String,
    val ascent: Int = 7,
    val height: Int = 7,
    val char: Char? = null
)
