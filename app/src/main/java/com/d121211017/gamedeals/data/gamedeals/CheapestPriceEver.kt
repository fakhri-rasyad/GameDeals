package com.d121211017.gamedeals.data.gamedeals

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheapestPriceEver(
    @SerialName(value = "date")
    val date: Int,
    @SerialName(value = "price")
    val price: String
)