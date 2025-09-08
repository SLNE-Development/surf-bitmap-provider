package dev.slne.surf.bitmap.paper.placeholder

import dev.slne.surf.bitmap.common.provider.BitmapProvider
import dev.slne.surf.surfapi.bukkit.api.hook.papi.expansion.PapiPlaceholder
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import org.bukkit.OfflinePlayer

object BitmapPlaceholder : PapiPlaceholder("translate") {
    override fun parse(
        player: OfflinePlayer,
        args: List<String>
    ): String {
        val foregroundHex = args[0]
        val backgroundHex = args[1]
        val affixAmount = args[2].toInt()
        val text = args.subList(3, args.size).joinToString(" ")

        val foregroundColor = TextColor.fromHexString(foregroundHex) ?: NamedTextColor.WHITE
        val backgroundColor = TextColor.fromHexString(backgroundHex) ?: NamedTextColor.BLACK

        return BitmapProvider.translateToString(text, foregroundColor, backgroundColor, affixAmount)
    }
}