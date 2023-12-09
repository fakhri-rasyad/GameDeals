package com.d121211017.gamedeals.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211017.gamedeals.GameDealUiState
import com.d121211017.gamedeals.GameDealsApplication
import com.d121211017.gamedeals.GameScreenState
import com.d121211017.gamedeals.data.GameDealsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class GameViewModel(private val gameDealsRepository: GameDealsRepository): ViewModel() {
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

    fun getGameDetail(gameId:String){
        viewModelScope.launch {
            try{
                val result2 = gameDealsRepository.getDeals(gameId)
//                val result = ""
//                if(result.isEmpty()){
//                    _uistate.update { currentState -> currentState.copy(screenState = GameScreenState.Empty) }
//                }else{
//                    _uistate.update { currentState ->  currentState.copy(screenState = GameScreenState.Success(result))}
//                }
            }catch(e:IOException){
                println(e)
            }
        }
    }

    fun getGame(gameName:String){
        viewModelScope.launch {
            try{
                val result = gameDealsRepository.getGames(gameName);
                if(result.isEmpty()){
                    _uistate.update { currentState -> currentState.copy(screenState = GameScreenState.Empty) }
                }else{
                    _uistate.update { currentState ->  currentState.copy(screenState = GameScreenState.Success(result))}
                }
            } catch (e: IOException){
                _uistate.update { currentState -> currentState.copy(screenState = GameScreenState.Failure) }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GameDealsApplication)
                val gameDealsRepository = application.container.gameDealsRepository
                GameViewModel(gameDealsRepository = gameDealsRepository)
            }
        }
    }

}