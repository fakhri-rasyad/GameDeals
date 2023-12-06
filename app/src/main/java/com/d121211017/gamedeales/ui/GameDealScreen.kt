package com.d121211017.gamedeales.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.d121211017.gamedeales.ui.screen.GameDetailScreen
import com.d121211017.gamedeales.ui.screen.GameSearchScreen
import com.d121211017.gamedeales.ui.theme.GameDealesTheme

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
    Scaffold(
        topBar = {
            GameDealAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                },
            )
        }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = GameDealScreen.Search.name,
            modifier = Modifier.padding(innerPadding).padding(16.dp)
            ){
            composable(
                GameDealScreen.Search.name
            ){
                GameSearchScreen(
                    isListView = uistate.isList,
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDealAppBar(
    currentScreen: GameDealScreen,
    canNavigateBack: Boolean,
    navigateUp: ()-> Unit,
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
                        contentDescription = "Back Arrow")
                }
            }
        }
        )
}

@Composable
fun GameDealDrawer(){

}

@Preview
@Composable
fun GameDealScreenPreview(){
    GameDealesTheme {
        GameDealApp()
    }
}