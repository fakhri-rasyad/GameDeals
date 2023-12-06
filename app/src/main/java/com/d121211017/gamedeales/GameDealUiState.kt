package com.d121211017.gamedeales

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue

data class GameDealUiState(
    val isList:Boolean = false,
    val appBarText:String = "Deal Me Some Games",
    val isDrawerOpen: DrawerValue = DrawerValue.Closed,
)
