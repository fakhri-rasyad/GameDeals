package com.d121211017.gamedeales.ui.component.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.d121211017.gamedeales.R
import com.d121211017.gamedeales.ui.theme.GhostWhite
import com.d121211017.gamedeales.ui.theme.NeonBlue
import com.d121211017.gamedeales.ui.theme.PistonBlue

@Composable
fun DrawerHeader(){
    Column(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        PistonBlue,
                        NeonBlue
                    )
                )
            )
            .fillMaxWidth()
            .padding(16.dp)){
        Image(
            painterResource(id = R.drawable.profile_picture),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Profile",
            modifier = Modifier
                .size(64.dp)
                .clip(shape = CircleShape)
                .border(
                    width = 2.dp,
                    color = Color.Transparent,
                    shape = CircleShape
                )
        )
        Text("Fakhri Rasyad", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = GhostWhite)
        Text("D121211017", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = GhostWhite)
        Text("Projek Mobile: Game Deals App", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = GhostWhite)
    }
}