package dev.slne.surf.bitmap.common.head.texture

import dev.slne.surf.bitmap.common.head.headJson
import dev.slne.surf.bitmap.common.head.session.PlayerSession
import kotlinx.serialization.Serializable
import java.net.URI
import java.util.*

@Serializable
data class BaseTexturesProperty(
    val timestamp: Long,
    val profileId: String,
    val profileName: String,
    val textures: TexturesProperty
)

fun decodeTextureString(textures: String): BaseTexturesProperty {
    val decoded = String(Base64.getDecoder().decode(textures))

    return headJson.decodeFromString(BaseTexturesProperty.serializer(), decoded)
}

fun getTextureStringByUuid(uuid: UUID): String? {
    val uuid = uuid.toString().replace("-", "")
    val url = "https://sessionserver.mojang.com/session/minecraft/profile/$uuid?unsigned=false"

    val connection = URI.create(url).toURL().openConnection()
    connection.addRequestProperty("User-Agent", "Mozilla/5.0")
    connection.connect()

    val response = connection.getInputStream().bufferedReader().readText()
    val session = headJson.decodeFromString(PlayerSession.serializer(), response)

    return session.properties.firstOrNull { it.name == "textures" }?.value
}