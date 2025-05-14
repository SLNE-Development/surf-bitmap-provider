package dev.slne.surf.bitmap.generator.command.subcommands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.bitmap.bitmaps.Bitmaps
import dev.slne.surf.bitmap.generator.utils.PermissionRegistry
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun CommandAPICommand.translateCommand() = subcommand("translate") {
    withPermission(PermissionRegistry.LETTERGEN_COMMAND_TRANSLATE)

    val entries = mutableListOf(*Bitmaps.entries.map { it.name }.toTypedArray())
    if (Bitmaps.entries.isEmpty()) {
        entries.add("no-bitmaps-available")
    }

    multiLiteralArgument("bitmap", *entries.toTypedArray())
    greedyStringArgument("translation")

    anyExecutor { sender, args ->
        val translation: String by args

        val bitmap: String by args
        val bitmaps = Bitmaps.entries.firstOrNull { it.name == bitmap }
            ?: run {
                sender.sendText {
                    appendPrefix()

                    error("Die Bitmap ")
                    variableValue(bitmap)
                    error(" existiert nicht.")
                }

                return@anyExecutor
            }

        val bitmapProvider = bitmaps.provider

        if (translation.isEmpty()) {
            sender.sendText {
                appendPrefix()

                error("Du musst einen Text angeben, der übersetzt werden soll.")
            }

            return@anyExecutor
        }

        val translatable = translation
            .replace(" ", "\\s")
            .replace("\\s", "\t")
            .replace("\\t", "\n")

        sender.sendText {
            appendPrefix()

            success("Die Übersetzung von ")
            variableValue(translation)
            success(" ist ")
            text(bitmapProvider.translateToString(translatable))
            success(".")
        }
    }
}