package com.d121211017.gamedeales.ui

import androidx.compose.material3.DrawerValue
import androidx.lifecycle.ViewModel
import com.d121211017.gamedeales.GameDealUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel: ViewModel() {
    private val _uistate = MutableStateFlow(
        GameDealUiState(
            isList = true,
            appBarText = "Deal Me Some Games",
            isDrawerOpen = DrawerValue.Closed,
            )
    )
    val uistate: StateFlow<GameDealUiState> = _uistate.asStateFlow()

    fun changeGameView(){
        _uistate.update { currentState -> currentState.copy(isList = !_uistate.value.isList) }
    }

    fun openDrawer(){
        _uistate.update { currentState -> currentState.copy(isDrawerOpen = if(_uistate.value.isDrawerOpen == DrawerValue.Closed) DrawerValue.Open else DrawerValue.Closed)}
    }
}