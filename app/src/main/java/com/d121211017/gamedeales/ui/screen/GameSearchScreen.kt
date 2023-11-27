package com.d121211017.gamedeales.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.d121211017.gamedeales.ui.theme.GameDealesTheme

@Composable
fun GameSearchScreen(modifier: Modifier = Modifier){
    Column(modifier){
        GameSearchBar()
    }
}

@Composable
fun GameSearchBar(){
    Row(Modifier.fillMaxWidth()){
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.height(48.dp).weight(2f))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.height(48.dp),
            shape = RoundedCornerShape(8.dp))
        {
            Icon(imageVector = Icons.Default.Search, contentDescription = "")
        }
    }
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