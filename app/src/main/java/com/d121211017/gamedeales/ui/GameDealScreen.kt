package com.d121211017.gamedeales.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.d121211017.gamedeales.GameDealUiState
import com.d121211017.gamedeales.ui.component.appbar.GameDealAppBar
import com.d121211017.gamedeales.ui.component.drawer.DrawerSheet
import com.d121211017.gamedeales.ui.screen.AboutScreen
import com.d121211017.gamedeales.ui.screen.GameDetailScreen
import com.d121211017.gamedeales.ui.screen.GameSearchScreen
import com.d121211017.gamedeales.ui.theme.GameDealesTheme

enum class GameDealScreen(val title: String){
    Search(title = "Deal Me Some App"),
    Game(title = "Game Detail"),
    About(title = "About")
}
@Composable
fun GameDealApp(
    viewModel: GameViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = GameDealScreen.valueOf(
        backStackEntry?.destination?.route ?: GameDealScreen.Search.name)
    val uistate by viewModel.uistate.collectAsState()

    GameDealDrawer(
        viewModel = viewModel,
        currentScreen = currentScreen,
        navController = navController,
        uiState = uistate,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun GameDealDrawer(
    viewModel: GameViewModel,
    currentScreen: GameDealScreen,
    navController:NavHostController,
    uiState: GameDealUiState,
    modifier: Modifier = Modifier,
){
    ModalNavigationDrawer(
        drawerState = DrawerState(uiState.isDrawerOpen),
        drawerContent = {
            DrawerSheet(
                viewModel = viewModel,
                navController = navController,
                modifier = modifier
            )
        }
    ) {
        GameDealScaffold(
            viewModel = viewModel,
            currentScreen = currentScreen,
            navController = navController,
            uiState = uiState)
    }
}
@Composable
fun GameDealScaffold(
    viewModel: GameViewModel,
    currentScreen: GameDealScreen,
    navController:NavHostController,
    uiState: GameDealUiState
){
    Scaffold(
        topBar = {
            GameDealAppBar(
                viewModel = viewModel,
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                },
            )
        }
    ) {innerPadding ->
        GameDealContent(
            viewModel = viewModel,
            innerPadding = innerPadding,
            navController = navController,
            uiState = uiState
        )

    }
}

@Composable
fun GameDealContent(
    viewModel: GameViewModel,
    innerPadding : PaddingValues,
    navController:NavHostController,
    uiState: GameDealUiState
){
    NavHost(
        navController = navController,
        startDestination = GameDealScreen.Search.name,
        modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
    ){
        composable(
            GameDealScreen.Search.name
        ){
            GameSearchScreen(
                isListView = uiState.isList,
                changeGameView = {viewModel.changeGameView()},
                onCardClick = {navController.navigate(GameDealScreen.Game.name)}
            )
        }
        composable(
            GameDealScreen.Game.name
        ){
            GameDetailScreen()
        }
        composable(
            GameDealScreen.About.name
        ){
            AboutScreen()
        }
    }
}

@Preview
@Composable
fun GameDealScreenPreview(){
    GameDealesTheme {
        GameDealApp()
    }
}