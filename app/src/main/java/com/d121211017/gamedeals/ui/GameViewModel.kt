package com.d121211017.gamedeals.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d121211017.gamedeals.GameDealUiState
import com.d121211017.gamedeals.GameScreenState
import com.d121211017.gamedeals.network.GameDealsApi
import com.d121211017.gamedeals.network.GameDealsApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class GameViewModel: ViewModel() {
    private val _uistate = MutableStateFlow(
        GameDealUiState(
            isList = true,
            isLightTheme = true,
            screenState = GameScreenState.Start
            )
    )
    val uistate: StateFlow<GameDealUiState> = _uistate.asStateFlow()

    fun changeGameView(){
        _uistate.update { currentState -> currentState.copy(isList = !_uistate.value.isList) }

    }
    fun changeTheme(){
        _uistate.update {currentState -> currentState.copy(isLightTheme = !_uistate.value.isLightTheme)}
    }

    fun getStore(){
        viewModelScope.launch {
            try{
//                val result = GameDealsApi.retrofitService.getStores()
                val result = ""
                if(result.isEmpty()){
                    _uistate.update { currentState -> currentState.copy(screenState = GameScreenState.Empty) }
                }else{
                    _uistate.update { currentState ->  currentState.copy(screenState = GameScreenState.Success(result))}
                }
            }catch (e: IOException){
                _uistate.update { currentState -> currentState.copy(screenState = GameScreenState.Failure) }
            }
        }
    }

}