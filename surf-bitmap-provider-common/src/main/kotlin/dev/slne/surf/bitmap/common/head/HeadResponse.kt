package dev.slne.surf.bitmap.common.head

import kotlinx.serialization.json.Json
import net.kyori.adventure.text.format.TextColor

typealias HeadArray = Array<Array<TextColor?>>

const val HEAD_OFFSET_X = 8
const val HEAD_OFFSET_Y = 8
const val SECOND_HEAD_OFFSET_X = 40
const val SECOND_HEAD_OFFSET_Y = 8
const val HEAD_SIZE = 8

data class HeadResponse(
    val firstLayer: HeadArray,
    val secondLayer: HeadArray
)

val headJson = Json {
    ignoreUnknownKeys = true
}