package dev.slne.surf.bitmap.common.command

import dev.slne.surf.api.core.messages.adventure.sendText
import dev.slne.surf.api.core.service.PlayerLookupService
import dev.slne.surf.bitmap.common.head.composeHead
import dev.slne.surf.bitmap.common.head.getHeadRowsByUuid
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component

/**
 * Looks up the player behind [playerName], renders their head at the given [scale] and sends it to
 * this audience. A missing [scale] renders the head at its original size.
 *
 * Sends an error message instead when no player with that name exists.
 */
suspend fun Audience.sendPlayerHead(
    playerName: String,
    scale: Int?,
    extraTest: (MutableList<Component>.() -> Unit)?
) {
    val usableScale = scale ?: 1

    val playerUuid = PlayerLookupService.getUuid(playerName) ?: run {
        sendText {
            appendErrorPrefix()

            error("Player ")
            variableValue(playerName)
            error(" not found.")
        }

        return
    }

    val extraText = mutableListOf<Component>().apply {
        extraTest?.invoke(this)
    }

    sendText {
        append(composeHead(getHeadRowsByUuid(playerUuid, usableScale), extraText))
    }
}
