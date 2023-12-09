package com.d121211017.gamedeals.data.game

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Game(
    @SerialName(value = "cheapest")
    val cheapest: String?,
    @SerialName(value = "cheapestDealID")
    val cheapestDealID: String?,
    @SerialName(value = "external")
    val `external`: String?,
    @SerialName(value = "gameID")
    val gameID: String?,
    @SerialName(value = "internalName")
    val internalName: String?,
    @SerialName(value = "steamAppID")
    val steamAppID: String?,
    @SerialName(value = "thumb")
    val thumb: String?
)