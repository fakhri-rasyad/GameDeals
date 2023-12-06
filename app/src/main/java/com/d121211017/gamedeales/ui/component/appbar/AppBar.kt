package com.d121211017.gamedeales.ui.component.appbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.d121211017.gamedeales.ui.GameDealScreen
import com.d121211017.gamedeales.ui.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDealAppBar(
    viewModel: GameViewModel,
    currentScreen: GameDealScreen,
    canNavigateBack: Boolean,
    navigateUp: ()-> Unit,
    modifier: Modifier = Modifier,
){
    TopAppBar(
        title = {
            Text(
                currentScreen.title,
                fontWeight = FontWeight.Bold)
        },
        colors = TopAppBarDefaults
            .mediumTopAppBarColors(
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
                IconButton(onClick = {viewModel.openDrawer()}) {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = "Menu"
                    )
                }
            }
        }
    )
}