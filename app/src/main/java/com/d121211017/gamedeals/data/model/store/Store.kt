package com.d121211017.gamedeals.data.model.store

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Store(
    @SerialName(value = "images")
    val images: Images,
    @SerialName(value = "isActive")
    val isActive: Int,
    @SerialName(value = "storeID")
    val storeID: String,
    @SerialName(value = "storeName")
    val storeName: String
)