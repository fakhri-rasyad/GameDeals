package com.d121211017.gamedeals.data.model.store

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Images(
    @SerialName(value = "banner")
    val banner: String,
    @SerialName(value = "icon")
    val icon: String,
    @SerialName(value = "logo")
    val logo: String
)