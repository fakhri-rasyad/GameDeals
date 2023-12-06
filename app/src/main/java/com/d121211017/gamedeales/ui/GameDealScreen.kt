package com.d121211017.gamedeales.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.d121211017.gamedeales.GameDealUiState
import com.d121211017.gamedeales.ui.screen.GameDetailScreen
import com.d121211017.gamedeales.ui.screen.GameSearchScreen
import com.d121211017.gamedeales.ui.theme.GameDealesTheme
import kotlinx.coroutines.launch

enum class GameDealScreen(val title: String){
    Search(title = "Deal Me Some App"),
    Game(title = "Game Detail"),
}
@Composable
fun GameDealApp(
    viewModel: GameViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = GameDealScreen.valueOf(backStackEntry?.destination?.route ?: GameDealScreen.Search.name)
    val uistate by viewModel.uistate.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    fun openDrawer(){
        scope.launch{
            drawerState.apply {
                if(isClosed) open() else close()
            }
        }
    }

    GameDealDrawer(
        viewModel = viewModel,
        currentScreen = currentScreen,
        navController = navController,
        ui_State = uistate,
        openDrawer = {openDrawer()},
        drawerState = drawerState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDealAppBar(
    currentScreen: GameDealScreen,
    canNavigateBack: Boolean,
    navigateUp: ()-> Unit,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    ){
    TopAppBar(
        title = {Text(currentScreen.title, fontWeight = FontWeight.Bold)},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier,
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "Back Arrow"
                    )
                }
            } else {
                IconButton(onClick = openDrawer) {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = "Menu"
                    )
                }
            }
        }
        )
}

@Composable
fun GameDealDrawer(
    viewModel: GameViewModel,
    currentScreen: GameDealScreen,
    navController:NavHostController,
    ui_State: GameDealUiState,
    openDrawer: () -> Unit,
    drawerState:DrawerState
){
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(text = "TEST ME")
            }
        }
    ) {
        GameDealScaffold(
            viewModel = viewModel,
            currentScreen = currentScreen,
            navController = navController,
            openDrawer = openDrawer,
            ui_State = ui_State)
    }
}
@Composable
fun GameDealScaffold(
    viewModel: GameViewModel,
    currentScreen: GameDealScreen,
    navController:NavHostController,
    openDrawer: () -> Unit,
    ui_State: GameDealUiState
){
    Scaffold(
        topBar = {
            GameDealAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                openDrawer = openDrawer,
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
            ui_State = ui_State
        )

    }
}

@Composable
fun GameDealContent(
    viewModel: GameViewModel,
    innerPadding : PaddingValues,
    navController:NavHostController,
    ui_State: GameDealUiState
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
                isListView = ui_State.isList,
                changeGameView = {viewModel.changeGameView()},
                onCardClick = {navController.navigate(GameDealScreen.Game.name)}
            )
        }
        composable(
            GameDealScreen.Game.name
        ){
            val context = LocalContext.current
            GameDetailScreen()
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