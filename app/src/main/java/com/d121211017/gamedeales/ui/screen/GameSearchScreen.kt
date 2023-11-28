package com.d121211017.gamedeales.ui.screen

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.d121211017.gamedeales.R
import com.d121211017.gamedeales.ui.theme.GameDealesTheme

@Composable
fun GameSearchScreen(modifier: Modifier = Modifier){
    Column(modifier){
        GameSearchBar()
        GameDisplayCard(gameThumbnail = R.drawable.help_fill1_wght400_grad0_opsz24, gameName = "Game Name Placeholder")
//        //    IconAndDetail(
////        image = R.drawable.search_fill1_wght400_grad0_opsz24,
////        description = "Ayo Cari Game Anda!",
////        modifier = Modifier
////            .fillMaxWidth()
////            .padding(top = 64.dp)
////        )
//        IconAndDetail(
//            image = R.drawable.help_fill1_wght400_grad0_opsz24,
//            description = "Sepertinya Game Yang Anda Cari Tidak Ada!",
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 32.dp)
//        )
////    IconAndDetail(
////        image = R.drawable.warning_fill1_wght400_grad0_opsz24,
////        description = "Sepertinya Jaringan Anda Sedang Bermasalah Silahkan Coba Lagi Nanti",
////        modifier = Modifier
////            .fillMaxSize()
////            .padding(top = 32.dp)
////    )
    }
}

@Composable
fun GameSearchBar(){
    Row(Modifier.fillMaxWidth()){
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .height(48.dp)
                .weight(2f))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.height(48.dp),
            shape = RoundedCornerShape(8.dp))
        {
            Icon(imageVector = Icons.Default.Search, contentDescription = "")
        }
    }
    Spacer(Modifier.height(64.dp))

}

@Composable
fun IconAndDetail(@DrawableRes image: Int, description: String, modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
        Image(painterResource(id = image), contentDescription = "", Modifier.size(128.dp))
        Spacer(Modifier.height(16.dp))
        Text(description, textAlign = TextAlign.Center, lineHeight = 28.sp , fontSize = 28.sp)
    }
}
@Composable
fun GameDisplayCard(gameThumbnail: Int, gameName: String){
    Card(){
        Column(
            Modifier
                .weight(.4f)
                .height(96.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painterResource(id = gameThumbnail), contentDescription = gameName)
            Text(gameName)
        }
    }
}

@Composable
fun GameDisplayGrid(){

}


@Preview
@Composable
fun GameSearchScreenPreview(){
    GameDealesTheme {
        GameSearchScreen(
            Modifier
                .fillMaxSize()
                .padding(32.dp))
    }
}