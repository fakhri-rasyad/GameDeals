package com.d121211017.gamedeales.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.d121211017.gamedeales.GameDealUiState
import com.d121211017.gamedeales.R
import com.d121211017.gamedeales.ui.screen.GameDetailScreen
import com.d121211017.gamedeales.ui.screen.GameSearchScreen
import com.d121211017.gamedeales.ui.screen.AboutScreen
import com.d121211017.gamedeales.ui.theme.GameDealesTheme
import com.d121211017.gamedeales.ui.theme.GhostWhite
import com.d121211017.gamedeales.ui.theme.NeonBlue
import com.d121211017.gamedeales.ui.theme.PistonBlue
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
        uiState = uistate,
        openDrawer = {openDrawer()},
        drawerState = drawerState,
        modifier = Modifier.padding(16.dp)
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
    uiState: GameDealUiState,
    openDrawer: () -> Unit,
    drawerState:DrawerState,
    modifier: Modifier = Modifier,
){
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column{
                    Column(
                        modifier = Modifier
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        PistonBlue,
                                        NeonBlue
                                    )
                                )
                            )
                            .fillMaxWidth()
                            .padding(16.dp)){
                        Image(
                            painterResource(id = R.drawable.profile_picture),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = "Profile",
                            modifier = Modifier
                                .size(64.dp)
                                .clip(shape = CircleShape)
                                .border(
                                    width = 2.dp,
                                    color = Color.Transparent,
                                    shape = CircleShape
                                )
                        )
                        Text("Fakhri Rasyad", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = GhostWhite)
                        Text("D121211017", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = GhostWhite)
                        Text("Projek Mobile: Game Deals App", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = GhostWhite)
                    }
                    Divider(thickness = 4.dp, color = NeonBlue)
                    Column(modifier) {
                        Button(onClick = {
                            navController.navigate(GameDealScreen.About.name)
                            openDrawer()
                        }) {
                            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                                Icon(
                                    imageVector = Icons.Rounded.Info,
                                    contentDescription = "Info"
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Information")
                            }
                        }
                    }
                }
            }
        }
    ) {
        GameDealScaffold(
            viewModel = viewModel,
            currentScreen = currentScreen,
            navController = navController,
            openDrawer = openDrawer,
            uiState = uiState)
    }
}
@Composable
fun GameDealScaffold(
    viewModel: GameViewModel,
    currentScreen: GameDealScreen,
    navController:NavHostController,
    openDrawer: () -> Unit,
    uiState: GameDealUiState
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
            val context = LocalContext.current
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