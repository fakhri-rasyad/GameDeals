package com.d121211017.gamedeals.data

import com.d121211017.gamedeals.data.model.deals.GameDetail
import com.d121211017.gamedeals.data.model.game.Game
import com.d121211017.gamedeals.data.model.store.Store
import com.d121211017.gamedeals.data.network.GameDealsApiService

interface GameDealsRepository {
    suspend fun getGames(gameName: String): List<Game>
    suspend fun  getDeals(gameId: String):GameDetail
    suspend fun getStores():List<Store>
}

class NetworkGameDealsRepository(
    private val gameDealsApiService : GameDealsApiService
):GameDealsRepository{
    override suspend fun getGames(gameName:String): List<Game> = gameDealsApiService.getGames(gameName)

    override suspend fun getDeals(gameId:String): GameDetail = gameDealsApiService.getGameDetail(gameId)

    override suspend fun getStores(): List<Store> = gameDealsApiService.getStores()
}