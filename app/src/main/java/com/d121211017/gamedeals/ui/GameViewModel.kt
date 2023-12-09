package com.d121211017.gamedeals.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211017.gamedeals.DetailScreenState
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
            searchScreenState = GameScreenState.Start,
            detailScreenState = DetailScreenState.Loading,
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
                val data = gameDealsRepository.getDeals(gameId)
                _uistate.update { currentState -> currentState.copy(detailScreenState = DetailScreenState.Success(data)) }
            }catch(e:IOException){
                _uistate.update { currentState -> currentState.copy(detailScreenState = DetailScreenState.Failure) }
            }
        }
    }

    fun getGame(gameName:String){
        _uistate.update { currentState -> currentState.copy(searchScreenState = GameScreenState.Loading) }
        viewModelScope.launch {
            try{
                val data = gameDealsRepository.getGames(gameName);
                if(data.isEmpty()){
                    _uistate.update { currentState -> currentState.copy(searchScreenState = GameScreenState.Empty) }
                }else{
                    _uistate.update { currentState ->  currentState.copy(searchScreenState = GameScreenState.Success(data))}
                }
            } catch (e: IOException){
                _uistate.update { currentState -> currentState.copy(searchScreenState = GameScreenState.Failure) }
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