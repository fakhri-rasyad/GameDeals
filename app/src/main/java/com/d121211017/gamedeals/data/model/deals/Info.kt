package com.d121211017.gamedeals.data.model.deals

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Info(
    @SerialName(value = "steamAppID")
    val steamAppID: String?,
    @SerialName(value = "thumb")
    val thumb: String,
    @SerialName(value = "title")
    val title: String
)