package com.d121211017.gamedeals.data.source.remote

import com.d121211017.gamedeals.data.model.game.Game
import com.d121211017.gamedeals.data.model.deals.GameDetail
import com.d121211017.gamedeals.data.model.store.Store
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType



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
    suspend fun getStores():List<Store>
}