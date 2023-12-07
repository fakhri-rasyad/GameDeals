package com.d121211017.gamedeales.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen(modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        AboutHeader()
        Divider(modifier = Modifier.padding(8.dp))
        Text(
            "Aplikasi yang menjadi tugas akhir dari Mata Kuliah Pemrogramman Mobile. Aplikasi ini menggunakan API dari cheapshark.com yang memberikan penawaran game dari toko berbeda")
    }
}

@Composable
fun AboutHeader(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ){
        Icon(
            imageVector = Icons.Rounded.Face,
            contentDescription = "Logo Image",
            modifier = Modifier.size(64.dp)
        )
        Spacer(Modifier.width(32.dp))
        Column {
            Text("Game Deals App")
            Spacer(Modifier.height(8.dp))
            Text("Version 1.0.0")
        }
    }
}