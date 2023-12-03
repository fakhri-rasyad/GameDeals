package com.d121211017.gamedeales.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.d121211017.gamedeales.ui.screen.GameDetailScreen
import com.d121211017.gamedeales.ui.screen.GameSearchScreen
import com.d121211017.gamedeales.ui.theme.GameDealesTheme

@Composable
fun GameDealApp(
    viewModel: GameViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
){
    Scaffold(
        topBar = { GameDealAppBar(Modifier.padding(16.dp)) }
    ) {innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(16.dp)) {
            GameDetailScreen()
//            GameSearchScreen()
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDealAppBar(modifier: Modifier = Modifier){
    TopAppBar(
        title = { Text("Deal Me Some Games", fontWeight = FontWeight.Bold)},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        )
}

@Preview
@Composable
fun GameDealScreenPreview(){
    GameDealesTheme {
        GameDealApp()
    }
}