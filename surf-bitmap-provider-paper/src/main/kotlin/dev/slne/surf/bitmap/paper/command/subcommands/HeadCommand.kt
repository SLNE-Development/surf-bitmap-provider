package dev.slne.surf.bitmap.paper.command.subcommands

import com.github.shynixn.mccoroutine.folia.launch
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.bitmap.common.head.composeHead
import dev.slne.surf.bitmap.common.head.getHeadRowsByUuid
import dev.slne.surf.bitmap.paper.command.PermissionRegistry
import dev.slne.surf.bitmap.paper.plugin
import dev.slne.surf.surfapi.core.api.messages.adventure.buildText
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import dev.slne.surf.surfapi.core.api.messages.adventure.text
import dev.slne.surf.surfapi.core.api.service.PlayerLookupService
import net.kyori.adventure.text.format.TextDecoration

fun CommandAPICommand.headCommand() = subcommand("head") {
    withPermission(PermissionRegistry.LETTERGEN_COMMAND_HEAD)

    stringArgument("playerName")
    integerArgument("scale", min = 1, optional = true)

    anyExecutor { sender, args ->
        val playerName: String by args
        val scale: Int? by args

        val usableScale = scale ?: 1

        plugin.launch {
            val playerUuid = PlayerLookupService.getUuid(playerName) ?: run {
                sender.sendText {
                    appendPrefix()

                    error("Player ")
                    variableValue(playerName)
                    error(" not found.")
                }

                return@launch
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

            sender.sendText {
                append(composeHead(head, extraText))
            }
        }
    }
}