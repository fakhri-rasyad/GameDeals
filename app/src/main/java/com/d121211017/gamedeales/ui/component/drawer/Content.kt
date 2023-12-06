package com.d121211017.gamedeales.ui.component.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.d121211017.gamedeales.ui.GameDealScreen
import com.d121211017.gamedeales.ui.GameViewModel

@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel,
    navController: NavHostController
){
    Column(modifier) {
        Button(onClick = {
            navController.navigate(GameDealScreen.About.name)
            viewModel.openDrawer()
        }) {
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