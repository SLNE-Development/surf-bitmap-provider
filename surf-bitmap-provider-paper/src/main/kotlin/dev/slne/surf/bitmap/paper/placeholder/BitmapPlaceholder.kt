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
        val shadowHex = args[1]
        val backgroundHex = args[2]
        val affixAmount = args[3].toInt()
        val text = args.subList(4, args.size).joinToString(" ")

        val foregroundColor = TextColor.fromHexString(foregroundHex) ?: NamedTextColor.WHITE
        val shadowColor = TextColor.fromHexString(shadowHex)
        val backgroundColor = TextColor.fromHexString(backgroundHex) ?: NamedTextColor.BLACK

        return BitmapProvider.translateToString(
            text,
            foregroundColor,
            shadowColor,
            backgroundColor,
            affixAmount
        )
    }
}