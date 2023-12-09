package com.d121211017.gamedeals.data.model.deals

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDetail(
    @SerialName(value = "cheapestPriceEver")
    val cheapestPriceEver: CheapestPriceEver,
    @SerialName(value = "deals")
    val deals: List<Deal>,
    @SerialName(value = "info")
    val info: Info
)