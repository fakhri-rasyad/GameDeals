package com.d121211017.gamedeales.ui.component.drawer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.d121211017.gamedeales.ui.GameDealScreen
import com.d121211017.gamedeales.ui.GameViewModel
import com.d121211017.gamedeales.ui.theme.GhostWhite
import com.d121211017.gamedeales.ui.theme.NeonBlue
import com.d121211017.gamedeales.ui.theme.PistonBlue

@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    openDrawer: () -> Unit
){
    Column(modifier) {
        Button(
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            onClick = {
                navController.navigate(GameDealScreen.About.name)
                openDrawer()
            },
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            PistonBlue,
                            NeonBlue
                        )
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    width = 2.dp,
                    color = NeonBlue,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Rounded.Info,
                    contentDescription = "Info"
                )
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                Text("Information")
            }
        }
    }
}