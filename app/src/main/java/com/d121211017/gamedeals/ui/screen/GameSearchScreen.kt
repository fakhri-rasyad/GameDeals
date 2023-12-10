package com.d121211017.gamedeals.ui.screen

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211017.gamedeals.GameScreenState
import com.d121211017.gamedeals.R
import com.d121211017.gamedeals.data.model.game.Game
import com.d121211017.gamedeals.ui.GameViewModel
import kotlin.random.Random

@Composable
fun GameSearchScreen(
    isListView: Boolean,
    screenState: GameScreenState,
    viewModel: GameViewModel,
    changeGameView: () -> Unit,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier){
    Column(modifier){
        GameSearchBar(isListView = isListView, viewModel = viewModel ,changeGameView = changeGameView)
        when(screenState){
            is GameScreenState.Success ->
                GameDisplayGrid(
                    viewModel = viewModel,
                    isListView = isListView,
                    gameList = screenState.game,
                    onCardClick = onCardClick
                )
            is GameScreenState.Loading ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            is GameScreenState.Empty ->
                IconAndDetail(
                image = R.drawable.help_fill1_wght400_grad0_opsz24,
                description = "Sepertinya Game Yang Anda Cari Tidak Ada!",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            )
            is GameScreenState.Failure ->
                IconAndDetail(
                image = R.drawable.warning_fill1_wght400_grad0_opsz24,
                description = "Sepertinya Jaringan Anda Sedang Bermasalah Silahkan Coba Lagi Nanti",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            )
            is GameScreenState.Start ->
                IconAndDetail(
                    image = R.drawable.search_fill1_wght400_grad0_opsz24,
                    description = "Ayo Cari Game Anda!",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                )
        }
    }
}

@Composable
fun GameSearchBar(
    viewModel: GameViewModel,
    isListView:Boolean ,changeGameView: ()->Unit){
    var userInput by remember { mutableStateOf("") }
    Column {
        Row(Modifier.fillMaxWidth()){
            TextField(
                value = userInput,
                onValueChange = {userInput = it},
                modifier = Modifier
                    .height(48.dp)
                    .weight(2f))
            Button(
                onClick = {viewModel.getGame(userInput)},
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
                Icon(
                    painter = painterResource(
                        id = if(isListView)
                            R.drawable.view_list_fill1_wght400_grad0_opsz24
                        else
                            R.drawable.grid_view_fill1_wght400_grad0_opsz24),
                    "gridView")
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
        Image(
            painterResource(id = image),
            contentDescription = "",
            Modifier.size(128.dp),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
        )
        Spacer(
            Modifier.height(16.dp)
        )
        Text(
            description,
            textAlign = TextAlign.Center,
            lineHeight = 28.sp,
            fontSize = 28.sp)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDisplayCard(viewModel: GameViewModel, onCardClick:()->Unit, game:Game, isList: Boolean){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        onClick = {
            onCardClick()
            viewModel.getGameDetail(game.gameID)
            viewModel.getStores()
        }
    ){
        if(isList){
            GameListCard(game = game)
        }
        else {
            GameGridCard(game = game)
    }}
}

@Composable
fun GameListCard(game: Game){
    Row(
        Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(game.thumb)
                .crossfade(enable = true)
                .build(),
            placeholder = painterResource(id = R.drawable.image_fill0_wght400_grad0_opsz24),
            error = painterResource(id = R.drawable.broken_image_fill0_wght400_grad0_opsz24),
            contentDescription = game.internalName,
            modifier = Modifier.size(height = 72.dp, width = 128.dp),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            game.external,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
    }
}

@Composable
fun GameGridCard(game : Game){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(game.thumb)
                .crossfade(enable = true)
                .build(),
            placeholder = painterResource(id = R.drawable.image_fill0_wght400_grad0_opsz24),
            error = painterResource(id = R.drawable.broken_image_fill0_wght400_grad0_opsz24),
            contentDescription = game.internalName,
            modifier = Modifier.size(height = 72.dp, width = 128.dp),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            game.external,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
    }
}

@Composable
fun GameDisplayGrid(
    viewModel: GameViewModel,
    gameList:List<Game>,
    isListView: Boolean = false,
    onCardClick: () -> Unit)
{
    LazyVerticalGrid(
        columns = GridCells.Fixed(if(isListView) 1 else 2),
        Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content =
        {
            items(gameList.size){
                index ->
                GameDisplayCard(
                    viewModel = viewModel,
                    isList = isListView,
                    game = gameList[index],
                    onCardClick = onCardClick
                )
            }
        }
    )
}

