package com.d121211017.gamedeals.data.gamedeals

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Deal(
    @SerialName(value = "dealID")
    val dealID: String,
    @SerialName(value = "price")
    val price: String,
    @SerialName(value = "retailPrice")
    val retailPrice: String,
    @SerialName(value = "savings")
    val savings: String,
    @SerialName(value = "storeID")
    val storeID: String
)