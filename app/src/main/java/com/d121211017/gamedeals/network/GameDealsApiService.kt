package com.d121211017.gamedeals.network

import com.d121211017.gamedeals.data.game.Game
import com.d121211017.gamedeals.data.gamedeals.GameDetail
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

private const val BASE_URL = "https://www.cheapshark.com/api/1.0/"

private val retrofit = Retrofit
    .Builder()
    .addConverterFactory(
        Json.asConverterFactory("application/json".toMediaType())
    )
    .baseUrl(
        BASE_URL
    )
    .build()

interface GameDealsApiService{

    @GET("games")
    suspend fun getGames(
        @Query("title") title:String,
    ): List<Game>

    @GET("games")
    suspend fun getGameDetail(
        @Query("id") id :String? = null,
    ): GameDetail
    @GET("stores")
    suspend fun getStores():String
}

object GameDealsApi{
    val retrofitService: GameDealsApiService by lazy{
        retrofit.create(GameDealsApiService::class.java)
    }
}