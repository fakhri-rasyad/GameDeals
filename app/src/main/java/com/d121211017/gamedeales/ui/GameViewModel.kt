package com.d121211017.gamedeales.ui

import androidx.lifecycle.ViewModel
import com.d121211017.gamedeales.GameDealUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel: ViewModel() {
    private val _uistate = MutableStateFlow(GameDealUiState(isList = true))
    val uistate: StateFlow<GameDealUiState> = _uistate.asStateFlow()

    fun changeGameView(){
        _uistate.update { currentState -> currentState.copy(isList = !_uistate.value.isList) }
    }
}