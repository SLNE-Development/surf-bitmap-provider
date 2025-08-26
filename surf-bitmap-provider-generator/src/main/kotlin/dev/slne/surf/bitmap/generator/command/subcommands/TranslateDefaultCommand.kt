package dev.slne.surf.bitmap.generator.command.subcommands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.jorel.commandapi.kotlindsl.subcommand
import dev.slne.surf.bitmap.bitmaps.Bitmaps
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import dev.slne.surf.surfapi.core.api.util.mutableObject2ObjectMapOf

fun CommandAPICommand.translateDefaultCommand() = subcommand("translateDefault") {
    withPermission("surf.bitmap.generator.command.translate-default")

    playerExecutor { player, args ->
        val map = mutableObject2ObjectMapOf(
            "Admin" to Bitmaps.RANK_ADMIN,
            "Management" to Bitmaps.RANK_MANAGEMENT,
            "Entwicklung" to Bitmaps.RANK_DEV,
            "Moderation" to Bitmaps.RANK_SUPPORT,
            "Support" to Bitmaps.RANK_SUPPORT,
            "Designer" to Bitmaps.RANK_TEAM_OTHER,
            "Builder" to Bitmaps.RANK_TEAM_OTHER,
            "Community" to Bitmaps.RANK_TEAM_OTHER,
            "Creator" to Bitmaps.RANK_CONTENT,
            "Contributor" to Bitmaps.RANK_PLAYER_OTHER,
            "Veteran" to Bitmaps.RANK_PLAYER_OTHER,
            "Premium+" to Bitmaps.RANK_PREMIUM,
            "Premium" to Bitmaps.RANK_PREMIUM,
            "Spieler" to Bitmaps.RANK_PLAYER
        )

        player.sendText {
            appendPrefix()
            info("Alle Ränge: ")
            appendNewPrefixedLine()
            appendNewPrefixedLine()

            var index = 0
            map.forEach { (key, value) ->
                if (index != 0) {
                    appendNewPrefixedLine()
                }

                variableKey(key)
                appendSpace()
                text(value.provider.translateToString(key))

                index++
            }
        }
    }
}