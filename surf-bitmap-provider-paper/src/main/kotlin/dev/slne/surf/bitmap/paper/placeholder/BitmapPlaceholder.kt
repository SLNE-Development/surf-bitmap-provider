package dev.slne.surf.bitmap.paper.placeholder

import dev.slne.surf.bitmap.common.provider.BitmapProvider
import dev.slne.surf.surfapi.bukkit.api.hook.papi.expansion.PapiPlaceholder
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.ShadowColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
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
        val shadowColor = ShadowColor.fromHexString(shadowHex) ?: ShadowColor.none()
        val backgroundColor = TextColor.fromHexString(backgroundHex) ?: NamedTextColor.BLACK

        return LegacyComponentSerializer.legacySection().serialize(
            BitmapProvider.translateToComponent(
                text,
                foregroundColor,
                backgroundColor,
                shadowColor,
                affixAmount
            )
        )
    }
}