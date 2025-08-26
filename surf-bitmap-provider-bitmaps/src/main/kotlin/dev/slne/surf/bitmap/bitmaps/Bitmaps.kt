package dev.slne.surf.bitmap.bitmaps

import dev.slne.surf.bitmap.api.BitmapProvider
import dev.slne.surf.bitmap.bitmaps.bitmaps.ClanDefault
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
    RANK_TEAM_OTHER(RanksOtherTeam),
    RANK_PLAYER_OTHER(RanksOtherPlayer),
    RANK_PLAYER(RanksPlayer)
}
