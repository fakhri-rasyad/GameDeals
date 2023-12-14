package com.d121211017.gamedeals.ui.screen.search

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211017.gamedeals.ui.GameScreenState
import com.d121211017.gamedeals.R
import com.d121211017.gamedeals.data.model.game.Game
import com.d121211017.gamedeals.ui.viewmodel.GameViewModel
import com.d121211017.gamedeals.ui.component.placeholder.IconAndDetail

@Composable
fun GameSearchScreen(
    isListView: Boolean,
    screenState: GameScreenState,
    viewModel: GameViewModel,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(modifier){
        GameSearchBar(
            isListView = isListView,
            viewModel = viewModel,
            changeGameView = {viewModel.changeGameView()}
        )
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
    isListView:Boolean, changeGameView: ()->Unit){
    var userInput by remember { mutableStateOf("") }
    Column {
        Row(Modifier.fillMaxWidth()){
            val context = LocalContext.current
            TextField(
                value = userInput,
                onValueChange = {userInput = it},
                modifier = Modifier
                    .height(48.dp)
                    .weight(2f))
            Button(
                onClick = {
                    viewModel.getGame(userInput, context = context, message = "Klik kartu untuk mendapat berbagai penawaran untuk game ")
                          },
                modifier = Modifier.height(48.dp),
                shape = RoundedCornerShape(8.dp)
            )
            {
                Icon(imageVector = Icons.Default.Search, contentDescription = "")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
            Button(
                onClick = {changeGameView()},
                shape = RoundedCornerShape(8.dp)
            ){
                Icon(
                    painter = painterResource(
                        id = if(isListView)
                            R.drawable.view_list_fill1_wght400_grad0_opsz24
                        else
                            R.drawable.grid_view_fill1_wght400_grad0_opsz24),
                    "gridView")
                Spacer(modifier = Modifier.width(8.dp))
                Text(if(isListView) "List" else "Grid")
            }

        }
    }
    Spacer(Modifier.height(8.dp))

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDisplayCard(viewModel: GameViewModel, onCardClick:()->Unit, game:Game, isList: Boolean){
    val context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        onClick = {
            onCardClick()
            viewModel.getGameDetail(
                game.gameID,
                context = context,
                message = "Klik penawaran untuk dibawa ke halaman penawaran dari toko")
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
    onCardClick: () -> Unit
) {
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

