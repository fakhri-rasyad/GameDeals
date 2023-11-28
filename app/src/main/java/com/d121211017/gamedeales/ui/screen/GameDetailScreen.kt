package com.d121211017.gamedeales.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.d121211017.gamedeales.R
import com.d121211017.gamedeales.ui.theme.GameDealesTheme

@Composable
fun GameDetailScreen(){
    Column(modifier = Modifier.fillMaxSize()){
        GameDetailHeader(image = R.drawable.warning_fill1_wght400_grad0_opsz24, gameName = "Game Name")
    }

}

@Composable
fun GameDetailHeader(image: Int, gameName: String){
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
        Image(painter = painterResource(id = image), contentDescription = gameName, modifier = Modifier.size(width = 96.dp, height = 128.dp))
        Text(gameName)
    }
}

@Composable
fun GameDealCard(){

}

@Composable
fun GameDealList(){

}

@Preview
@Composable
fun GameDetailScreenPreview(){
    GameDealesTheme {
        GameDetailScreen()
    }
}