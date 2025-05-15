package dev.slne.surf.bitmap.generator.command.subcommands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.bitmap.api.BitmapProvider
import dev.slne.surf.bitmap.bitmaps.Bitmaps
import dev.slne.surf.bitmap.generator.utils.PermissionRegistry
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.command.CommandSender
import java.util.*

fun CommandAPICommand.translateCommand() = subcommand("translate") {
    withPermission(PermissionRegistry.LETTERGEN_COMMAND_TRANSLATE)

    val entries = mutableListOf(*Bitmaps.entries.map { it.name }.toTypedArray())
    if (Bitmaps.entries.isEmpty()) {
        entries.add("no-bitmaps-available")
    }
    entries.add("ALL")

    multiLiteralArgument("bitmap", *entries.toTypedArray())
    greedyStringArgument("translation")

    anyExecutor { sender, args ->
        val translation: String by args
        val bitmap: String by args

        val bitmapsEntry = Bitmaps.entries.firstOrNull { it.name == bitmap }
        val bitmaps = mutableListOf<Bitmaps>()

        if (bitmapsEntry != null) {
            bitmaps.add(bitmapsEntry)
        }

        if (bitmap == "ALL") {
            bitmaps.addAll(Bitmaps.entries)
        }

        if (bitmap.isEmpty() || (bitmapsEntry == null && bitmap != "ALL")) {
            sender.sendText {
                appendPrefix()

                error("Die Bitmap ")
                variableValue(bitmap)
                error(" existiert nicht.")
            }

            return@anyExecutor
        }

        bitmaps.forEach { oneBitmap ->
            translateWithBitmapProvider(
                sender = sender,
                bitmapProvider = oneBitmap.provider,
                translation = translation
            )
        }
    }
}

private fun translateWithBitmapProvider(
    sender: CommandSender,
    bitmapProvider: BitmapProvider,
    translation: String
) {
    if (translation.isEmpty()) {
        sender.sendText {
            appendPrefix()

            error("Du musst einen Text angeben, der übersetzt werden soll.")
        }

        return
    }

    val translatable = translation
        .replace(" ", "\\s")
        .replace("\\s", "\t")
        .replace("\\t", "\n")

    sender.sendText {
        appendPrefix()

        primary(
            "[${
                bitmapProvider.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }
            }] "
        )
        variableValue(translation)
        success(" -> ")
        text(bitmapProvider.translateToString(translatable))
    }
}