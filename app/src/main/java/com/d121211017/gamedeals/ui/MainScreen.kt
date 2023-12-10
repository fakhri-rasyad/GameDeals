package com.d121211017.gamedeals.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.d121211017.gamedeals.GameDealUiState
import com.d121211017.gamedeals.ui.component.appbar.GameDealAppBar
import com.d121211017.gamedeals.ui.component.drawer.DrawerSheet
import com.d121211017.gamedeals.ui.screen.AboutScreen
import com.d121211017.gamedeals.ui.screen.GameDetailScreen
import com.d121211017.gamedeals.ui.screen.GameSearchScreen
import kotlinx.coroutines.launch

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
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    fun openDrawer(){
        scope.launch {
            drawerState.apply {
                if(isClosed) open() else close()
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerSheet(
                navController = navController,
                openDrawer = {openDrawer()},
                modifier = modifier
            )
        }
    ) {
        GameDealScaffold(
            viewModel = viewModel,
            currentScreen = currentScreen,
            navController = navController,
            uiState = uiState,
            openDrawer = {openDrawer()}
        )
    }
}
@Composable
fun GameDealScaffold(
    viewModel: GameViewModel,
    currentScreen: GameDealScreen,
    navController:NavHostController,
    uiState: GameDealUiState,
    openDrawer: () -> Unit
){
    Scaffold(
        topBar = {
            GameDealAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                isLightTheme = uiState.isLightTheme,
                navigateUp = {
                    navController.navigateUp()
                },
                openDrawer = {openDrawer()},
                changeTheme = {viewModel.changeTheme()}

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
                screenState = uiState.searchScreenState,
                viewModel = viewModel,
                changeGameView = {viewModel.changeGameView()},
                onCardClick = {
                    navController.navigate(GameDealScreen.Game.name)
                }
            )
        }
        composable(
            GameDealScreen.Game.name
        ){
            GameDetailScreen(uiState)
        }
        composable(
            GameDealScreen.About.name
        ){
            AboutScreen()
        }
    }
}
