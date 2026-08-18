package dev.slne.surf.bitmap.common.command

import dev.slne.surf.api.core.messages.adventure.buildText
import dev.slne.surf.api.core.messages.adventure.sendText
import dev.slne.surf.api.core.messages.adventure.text
import dev.slne.surf.api.core.service.PlayerLookupService
import dev.slne.surf.bitmap.common.head.composeHead
import dev.slne.surf.bitmap.common.head.getHeadRowsByUuid
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.format.TextDecoration

/**
 * Looks up the player behind [playerName], renders their head at the given [scale] and sends it to
 * this audience. A missing [scale] renders the head at its original size.
 *
 * Sends an error message instead when no player with that name exists.
 */
suspend fun Audience.sendPlayerHead(playerName: String, scale: Int?) {
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

    val head = getHeadRowsByUuid(playerUuid, usableScale)

    val extraText = listOf(
        text(""),
        text(""),
        buildText {
            repeat(3) { appendSpace() }
            primary("Special-Drop", TextDecoration.BOLD)
        },
        text(""),
        buildText {
            repeat(3) { appendSpace() }
            variableValue(playerName)
        },
        buildText {
            repeat(3) { appendSpace() }
            spacer("hat eine ")
            variableValue("Elytra")
            spacer(" erhalten!")
        }
    )

    sendText {
        append(composeHead(head, extraText))
    }
}
