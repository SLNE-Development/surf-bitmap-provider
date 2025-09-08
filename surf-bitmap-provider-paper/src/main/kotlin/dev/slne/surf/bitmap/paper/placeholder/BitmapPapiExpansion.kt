package dev.slne.surf.bitmap.paper.placeholder

import dev.slne.surf.surfapi.bukkit.api.hook.papi.expansion.PapiExpansion

object BitmapPapiExpansion : PapiExpansion(
    "bitmap", listOf(
        BitmapPlaceholder
    )
)