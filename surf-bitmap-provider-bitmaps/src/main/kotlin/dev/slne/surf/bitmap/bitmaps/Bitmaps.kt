package dev.slne.surf.bitmap.bitmaps

import dev.slne.surf.bitmap.api.BitmapProvider
import dev.slne.surf.bitmap.bitmaps.bitmaps.*

enum class Bitmaps(
    val provider: BitmapProvider
) {
    // Clan Dark
    CLAN_BURNSTEEL(ClanBurnsteel),
    CLAN_CLOUDSHIFT(ClanCloudshift),
    CLAN_ICEFORGE(ClanIceforge),
    CLAN_NEONPULSE(ClanNeonpulse),
    CLAN_TWILIGHTCORE(ClanTwilightcore),
    CLAN_VENOMBYTE(ClanVenombyte),
    CLAN_VOIDSPARK(ClanVoidspark),

    // Clan Light
    CLAN_LEAFDUST(ClanLeafdust),
    CLAN_PEACHWAVE(ClanPeachwave),
    CLAN_GHOSTMIST(ClanGhostmist),
    CLAN_SKYBYTE(ClanSkybyte),
    CLAN_DAYBLOOM(ClanDaybloom),
    CLAN_BLUSHCORE(ClanBlushcore),
    CLAN_MINTGLOW(ClanMintglow),

    // Clan Funky
    CLAN_NIGHTCOLA(ClanNightcola),
    CLAN_SUNSETNOVA(ClanSunsetnova),
    CLAN_GLITCHMELT(ClanGlitchmelt),
    CLAN_DREAMJAM(ClanDreamjam),
    CLAN_CRYSTALCORE(ClanCrystalcore),
    CLAN_TOXICBEAT(ClanToxicbeat),
    CLAN_HAZEBURST(ClanHazeburst),
    CLAN_VOIDPOP(ClanVoidpop),
    CLAN_BUBBLEBYTE(ClanBubblebyte),
    CLAN_SYNTHVORTEX(ClanSynthvortex),
    ;
}