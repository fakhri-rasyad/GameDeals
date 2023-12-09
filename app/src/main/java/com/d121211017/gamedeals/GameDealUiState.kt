package com.d121211017.gamedeals

import com.d121211017.gamedeals.data.game.Game

sealed interface GameScreenState {
    object Start:GameScreenState
    object Empty:GameScreenState
    data class Success(val game: List<Game>): GameScreenState

    object Failure:GameScreenState
}
data class GameDealUiState(
    val isList:Boolean = false,
    val isLightTheme:Boolean = true,
    val listStore: String = "",
    val screenState: GameScreenState = GameScreenState.Start
)
