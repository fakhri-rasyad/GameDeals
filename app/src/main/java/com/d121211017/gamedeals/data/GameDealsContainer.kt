package com.d121211017.gamedeals.data

import com.d121211017.gamedeals.data.network.GameDealsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface GameDealsContainer {
    val gameDealsRepository: GameDealsRepository
}

class DefaultAppContainer : GameDealsContainer{
    private val baseUrl = "https://www.cheapshark.com/api/1.0/"

    private val retrofit = Retrofit
        .Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: GameDealsApiService by lazy{
        retrofit.create(GameDealsApiService::class.java)
    }

    override val gameDealsRepository: GameDealsRepository by lazy{
        NetworkGameDealsRepository(retrofitService)
    }
}