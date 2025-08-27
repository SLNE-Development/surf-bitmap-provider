package dev.slne.surf.bitmap.generator.command.subcommands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.jorel.commandapi.kotlindsl.subcommand
import dev.slne.surf.bitmap.bitmaps.Bitmaps
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap

fun CommandAPICommand.translateDefaultCommand() = subcommand("translateDefault") {
    withPermission("surf.bitmap.generator.command.translate-default")

    playerExecutor { player, args ->
        val map = Object2ObjectLinkedOpenHashMap<String, Bitmaps>()
        
        map["Admin"] = Bitmaps.RANK_ADMIN
        map["Management"] = Bitmaps.RANK_MANAGEMENT
        map["Entwicklung"] = Bitmaps.RANK_DEV
        map["Moderation"] = Bitmaps.RANK_SUPPORT
        map["Support"] = Bitmaps.RANK_SUPPORT
        map["Designer"] = Bitmaps.RANK_TEAM_OTHER
        map["Builder"] = Bitmaps.RANK_TEAM_OTHER
        map["Community"] = Bitmaps.RANK_TEAM_OTHER
        map["Creator"] = Bitmaps.RANK_CONTENT
        map["Contributor"] = Bitmaps.RANK_PLAYER_OTHER
        map["Veteran"] = Bitmaps.RANK_PLAYER_OTHER
        map["Premium+"] = Bitmaps.RANK_PREMIUM
        map["Premium"] = Bitmaps.RANK_PREMIUM
        map["Spieler"] = Bitmaps.RANK_PLAYER

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