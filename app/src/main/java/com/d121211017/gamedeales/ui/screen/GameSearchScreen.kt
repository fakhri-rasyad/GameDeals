package com.d121211017.gamedeales.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.d121211017.gamedeales.R
import kotlin.random.Random

@Composable
fun GameSearchScreen(isListView: Boolean ,changeGameView: () -> Unit ,modifier: Modifier = Modifier){
    Column(modifier){
        GameSearchBar(isListView = isListView ,changeGameView = changeGameView)
        GameDisplayGrid(isListView = isListView)
//        GameDisplayCard(gameThumbnail = R.drawable.help_fill1_wght400_grad0_opsz24, gameName = "Game Name Placeholder")
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
fun GameSearchBar(isListView:Boolean ,changeGameView: ()->Unit){
    Column {
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
                shape = RoundedCornerShape(8.dp)
            )
            {
                Icon(imageVector = Icons.Default.Search, contentDescription = "")
            }
        }
        Row(Modifier.fillMaxWidth()){
            Button(
                onClick = {},
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp)
            ){
                Icon(
                    painter = painterResource(id = R.drawable.sort_fill1_wght400_grad0_opsz24), "gridView")
            }
            Button(
                onClick = {changeGameView()},
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp)
            ){
                Icon(painter = painterResource(id = if(isListView) R.drawable.view_list_fill1_wght400_grad0_opsz24 else R.drawable.grid_view_fill1_wght400_grad0_opsz24), "gridView")
            }

        }
    }
    Spacer(Modifier.height(16.dp))

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
fun GameDisplayCard(gameThumbnail: Int, gameName: String, isList: Boolean){
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
        border = BorderStroke(1.dp, SolidColor(MaterialTheme.colorScheme.tertiary))

    ){
        if(isList){
            GameListCard(gameName = gameName, gameThumbnail = gameThumbnail)
        }
        else {
            GameGridCard(gameName = gameName, gameThumbnail = gameThumbnail)
    }}
}

@Composable
fun GameListCard(gameName: String,gameThumbnail: Int){
    Row(
        Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(id = gameThumbnail),
            contentDescription = gameName,
            Modifier.size(height = 72.dp, width = 128.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            gameName,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
    }
}

@Composable
fun GameGridCard(gameName: String, gameThumbnail: Int){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = gameThumbnail),
            contentDescription = gameName,
            Modifier.size(height = 72.dp, width = 128.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            gameName,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Composable
fun GameDisplayGrid(isListView: Boolean){
    val storaImages = arrayOf(R.drawable.batman, R.drawable.batman_2, R.drawable.arkham)
    val gameName = arrayOf("TEST MEMEMEMEM", "MMEEMEMEMM", "Me ME ME ME ME ME ME ME ME ME ME ME ME")
    LazyVerticalGrid(
        columns = GridCells.Fixed(if(isListView) 1 else 2),
        Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {
        items(8){
            _ -> GameDisplayCard(
            gameThumbnail = storaImages[Random.nextInt(0, storaImages.size)],
            gameName = gameName[Random.nextInt(0, gameName.size)],
            isList = isListView
            )
        }
    })
}


//@Preview
//@Composable
//fun GameSearchScreenPreview(){
//    GameDealesTheme {
//        GameSearchScreen(
////            Modifier
////                .fillMaxSize()
////                .padding(32.dp))
////    }
//}