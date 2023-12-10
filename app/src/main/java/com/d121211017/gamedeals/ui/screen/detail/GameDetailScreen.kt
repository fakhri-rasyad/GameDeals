package com.d121211017.gamedeals.ui.screen.detail

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211017.gamedeals.DealsState
import com.d121211017.gamedeals.DetailScreenState
import com.d121211017.gamedeals.GameDealUiState
import com.d121211017.gamedeals.R
import com.d121211017.gamedeals.data.model.deals.Deal
import com.d121211017.gamedeals.data.model.deals.GameDetail
import com.d121211017.gamedeals.data.model.store.Store
import com.d121211017.gamedeals.ui.component.placeholder.IconAndDetail

@Composable
fun GameDetailScreen(uistate: GameDealUiState){
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        when(uistate.detailScreenState){
            is DetailScreenState.Success -> Column {
                GameDetailHeader(uistate.detailScreenState.gameDetail)
                Spacer(modifier = Modifier.height(8.dp))
                GameDealList(uistate, uistate.detailScreenState.gameDetail.deals)
            }
            is DetailScreenState.Loading -> CircularProgressIndicator()
            is DetailScreenState.Failure ->
                IconAndDetail(
                    image = R.drawable.warning_fill1_wght400_grad0_opsz24,
                    description = "Gagal mengambil informasi game, silahkan coba lagi nanti"
                )
        }
    }

}

@Composable
fun GameDetailHeader(gameDetail: GameDetail){
    Row(
        Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(gameDetail.info.thumb)
                .crossfade(enable = true)
                .build(),
            placeholder = painterResource(id = R.drawable.image_fill0_wght400_grad0_opsz24),
            error = painterResource(id = R.drawable.broken_image_fill0_wght400_grad0_opsz24),
            contentDescription = gameDetail.info.title,
            modifier = Modifier
                .size(width = 192.dp, height = 144.dp)
                .fillMaxWidth()
                .weight(1f)
        )
        Text(gameDetail.info.title, textAlign = TextAlign.Center, fontSize = 24.sp,modifier = Modifier
            .fillMaxWidth()
            .weight(1f))
    }
}

@Composable
fun GameDealCard(
    deal:Deal,
    store: Store
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
        ){
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data("https://www.cheapshark.com" + store.images.banner)
                .crossfade(enable = true)
                .build(),
            placeholder = painterResource(id = R.drawable.image_fill0_wght400_grad0_opsz24),
            error = painterResource(id = R.drawable.broken_image_fill0_wght400_grad0_opsz24),
            contentDescription = deal.dealID,
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
                text = store.storeName,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End){
                Text(
                    "\$${deal.retailPrice}",
                    textAlign = TextAlign.End,
                    textDecoration = TextDecoration.LineThrough,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(
                    modifier = Modifier.width(8.dp))
                Text(
                    "\$${deal.price}",
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun GameDealList(uistate: GameDealUiState, dealList: List<Deal>){
    when(uistate.dealState){
        is DealsState.Success -> LazyColumn{
            items(dealList.size){ index ->
                val store: Store = uistate.dealState.store.first{it.storeID == dealList[index].storeID}
                GameDealCard(deal = dealList[index], store = store)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        is DealsState.Loading -> CircularProgressIndicator()
        is DealsState.Failure -> IconAndDetail(
            image = R.drawable.warning_fill1_wght400_grad0_opsz24,
            description = "Gagal mendapatkan penawaran silahkan coba lagi nanti"
        )
    }

}