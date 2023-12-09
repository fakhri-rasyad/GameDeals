package com.d121211017.gamedeals

import com.d121211017.gamedeals.data.model.deals.GameDetail
import com.d121211017.gamedeals.data.model.game.Game

sealed interface GameScreenState {
    object Start:GameScreenState
    object Loading:GameScreenState
    object Empty:GameScreenState
    data class Success(val game: List<Game>): GameScreenState
    object Failure:GameScreenState
}

sealed interface DetailScreenState{
    object Loading:DetailScreenState
    data class Success(val gameDetail: GameDetail):DetailScreenState
    object Failure:DetailScreenState
}
data class GameDealUiState(
    val isList:Boolean = false,
    val isLightTheme:Boolean = true,
    val searchScreenState: GameScreenState = GameScreenState.Start,
    val detailScreenState: DetailScreenState = DetailScreenState.Loading
)
