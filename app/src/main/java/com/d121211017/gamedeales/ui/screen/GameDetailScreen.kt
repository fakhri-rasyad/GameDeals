package com.d121211017.gamedeales.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.d121211017.gamedeales.R
import com.d121211017.gamedeales.ui.theme.GameDealesTheme
import kotlin.random.Random

@Composable
fun GameDetailScreen(){
    Column(modifier = Modifier
        .fillMaxSize()
        ){
        GameDetailHeader(image = R.drawable.batman_2, gameName = "Game Name")
        GameDealList()
    }

}

@Composable
fun GameDetailHeader(image: Int, gameName: String){
    Row(
        Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = image),
            contentDescription = gameName,
            modifier = Modifier
                .size(width = 192.dp, height = 144.dp)
                .fillMaxWidth()
                .weight(1f)
        )
        Text(gameName, textAlign = TextAlign.Center, fontSize = 24.sp,modifier = Modifier
            .fillMaxWidth()
            .weight(1f))
    }
}

@Composable
fun GameDealCard(
    image: Int,
    initialPrice: String,
    currentPrice: String,
    gameName: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp, brush = SolidColor(MaterialTheme.colorScheme.tertiary),
                shape = RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
        ){
        Image(
            painter = painterResource(id = image),
            contentDescription = gameName,
            modifier = Modifier
                .size(width = 96.dp, height = 32.dp)
                .fillMaxWidth()
                .weight(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            Text(
                text = gameName,
                fontSize = 24.sp,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End){
                Text(
                    "\$$initialPrice",

                    textAlign = TextAlign.End,
                    textDecoration = TextDecoration.LineThrough)
                Spacer(
                    modifier = Modifier.width(8.dp))
                Text(
                    "\$$currentPrice",
                    textAlign = TextAlign.End)
            }
        }
    }
}

@Composable
fun GameDealList(){
    val storaImages = arrayOf(R.drawable.greenman, R.drawable.gamergate, R.drawable.steam)
    LazyColumn{
        items(8){
            _ ->
            GameDealCard(
                image = storaImages[Random.nextInt(0, storaImages.size)],
            initialPrice = "19.99",
            currentPrice = "4.99",
            gameName = "Store Name")
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun GameDetailScreenPreview(){
    GameDealesTheme {
        GameDetailScreen()
    }
}