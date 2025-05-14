package dev.slne.surf.bitmap.generator.config

import org.spongepowered.configurate.objectmapping.ConfigSerializable
import org.spongepowered.configurate.objectmapping.meta.Setting

@ConfigSerializable
data class LetterConfig(
    val bitmap: BitmapConfig,

    @Setting("acute_accent")
    val acuteAccent: OneLetterConfig,

    @Setting("ampersand")
    val ampersand: OneLetterConfig,

    @Setting("bracket_close")
    val bracketClose: OneLetterConfig,

    @Setting("bracket_open")
    val bracketOpen: OneLetterConfig,

    @Setting("circum_flex")
    val circumflex: OneLetterConfig,

    @Setting("colon")
    val colon: OneLetterConfig,

    @Setting("comma")
    val comma: OneLetterConfig,

    @Setting("curly_bracket_close")
    val curlyBracketClose: OneLetterConfig,

    @Setting("curly_bracket_open")
    val curlyBracketOpen: OneLetterConfig,

    @Setting("degree")
    val degree: OneLetterConfig,

    @Setting("dollar")
    val dollar: OneLetterConfig,

    @Setting("dot")
    val dot: OneLetterConfig,

    @Setting("double_quote")
    val doubleQuote: OneLetterConfig,

    @Setting("euro")
    val euro: OneLetterConfig,

    @Setting("exclamation_mark")
    val exclamationMark: OneLetterConfig,

    @Setting("grave_accent")
    val graveAccent: OneLetterConfig,

    @Setting("greater_than")
    val greaterThan: OneLetterConfig,

    @Setting("hyphen")
    val hyphen: OneLetterConfig,

    @Setting("less_than")
    val lessThan: OneLetterConfig,

    @Setting("pipe")
    val pipe: OneLetterConfig,

    @Setting("question_mark")
    val questionMark: OneLetterConfig,

    @Setting("section")
    val section: OneLetterConfig,

    @Setting("semicolon")
    val semicolon: OneLetterConfig,

    @Setting("single_quote")
    val singleQuote: OneLetterConfig,

    @Setting("spacer_one")
    val spacerOne: OneLetterConfig,

    @Setting("spacer_two")
    val spacerTwo: OneLetterConfig,

    @Setting("square_bracket_close")
    val squareBracketClose: OneLetterConfig,

    @Setting("square_bracket_open")
    val squareBracketOpen: OneLetterConfig,

    @Setting("tilde")
    val tilde: OneLetterConfig,
)
