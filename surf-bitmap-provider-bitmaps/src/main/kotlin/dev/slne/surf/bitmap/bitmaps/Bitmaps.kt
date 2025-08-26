package dev.slne.surf.bitmap.bitmaps

import dev.slne.surf.bitmap.api.BitmapProvider
import dev.slne.surf.bitmap.bitmaps.bitmaps.clans.ClanDefault
import dev.slne.surf.bitmap.bitmaps.bitmaps.ranks.*

enum class Bitmaps(
    val provider: BitmapProvider
) {
    // Clans
    CLAN_DEFAULT(ClanDefault),

    // Ranks
    RANK_ADMIN(RanksAdmin),
    RANK_DEV(RanksDev),
    RANK_SUPPORT(RanksSupport),
    RANK_TEAM_OTHER(RanksOtherteam),
    RANK_PLAYER_OTHER(RanksOtherplayer),
    RANK_PLAYER(RanksPlayer)
}
