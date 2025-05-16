package dev.slne.surf.bitmap.bitmaps

import dev.slne.surf.bitmap.api.BitmapProvider
import dev.slne.surf.bitmap.bitmaps.bitmaps.*

enum class Bitmaps(
    val provider: BitmapProvider
) {
    // Clan Bitmaps
    CLAN_ARCTICRUSH(ClanArcticrush),
    CLAN_AURORASHADE(ClanAurorashade),
    CLAN_BLUSHCORE(ClanBlushcore),
    CLAN_BUBBLEBYTE(ClanBubblebyte),
    CLAN_BURNSTEEL(ClanBurnsteel),
    CLAN_CLOUDSHIFT(ClanCloudshift),
    CLAN_CRYSTALCORE(ClanCrystalcore),
    CLAN_CYBERPEACH(ClanCyberpeach),
    CLAN_DAYBLOOM(ClanDaybloom),
    CLAN_DREAMJAM(ClanDreamjam),
    CLAN_EMBERSTORM(ClanEmberstorm),
    CLAN_GHOSTMIST(ClanGhostmist),
    CLAN_GLITCHMELT(ClanGlitchmelt),
    CLAN_GLITCHWAVE(ClanGlitchwave),
    CLAN_HAZEBURST(ClanHazeburst),
    CLAN_ICEDRIFT(ClanIcedrift),
    CLAN_ICEFORGE(ClanIceforge),
    CLAN_LEAFDUST(ClanLeafdust),
    CLAN_MAGMAREEF(ClanMagmareef),
    CLAN_MINTGLOW(ClanMintglow),
    CLAN_MINTQUAKE(ClanMintquake),
    CLAN_NEONFIZZ(ClanNeonfizz),
    CLAN_NEONPULSE(ClanNeonpulse),
    CLAN_NIGHTCOLA(ClanNightcola),
    CLAN_PEACHWAVE(ClanPeachwave),
    CLAN_PLASMAFLARE(ClanPlasmaflare),
    CLAN_SKYBYTE(ClanSkybyte),
    CLAN_SNOWFLASH(ClanSnowflash),
    CLAN_SUNSETNOVA(ClanSunsetnova),
    CLAN_SYNTHVORTEX(ClanSynthvortex),
    CLAN_TOXICBEAT(ClanToxicbeat),
    CLAN_TWILIGHTCORE(ClanTwilightcore),
    CLAN_VENOMBYTE(ClanVenombyte),
    CLAN_VIOLETNOVA(ClanVioletnova),
    CLAN_VOIDPOP(ClanVoidpop),
    CLAN_VOIDSPARK(ClanVoidspark),

    // Rank Bitmaps
    RANK_ADMINISTRATOR(RankAdministrator),
    RANK_BUILDER(RankBuilder),
    RANK_CONTENT(RankContent),
    RANK_CONTRIBUTOR(RankContributor),
    RANK_DEVELOPER(RankDeveloper),
    RANK_MODERATOR(RankModerator),
    RANK_PLAYER(RankPlayer),
    RANK_PREMIUM(RankPremium),
    RANK_SUPPORTER(RankSupporter),
    RANK_VETERAN(RankVeteran),
}
