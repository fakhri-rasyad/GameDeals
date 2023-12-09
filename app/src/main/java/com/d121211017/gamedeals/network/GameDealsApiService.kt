package com.d121211017.gamedeals.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL = "https://www.cheapshark.com/api/1.0/"

private val retrofit = Retrofit
    .Builder()
    .addConverterFactory(
        ScalarsConverterFactory
            .create()
    )
    .baseUrl(
        BASE_URL
    )
    .build()

interface GameDealsApiService{

    @GET("stores")
    suspend fun getStores():String
}

object GameDealsApi{
    val retrofitService: GameDealsApiService by lazy{
        retrofit.create(GameDealsApiService::class.java)
    }
}