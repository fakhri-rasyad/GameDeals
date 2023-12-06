package com.d121211017.gamedeales.ui.component.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.d121211017.gamedeales.ui.GameViewModel
import com.d121211017.gamedeales.ui.theme.NeonBlue

@Composable
fun DrawerSheet(
    navController: NavHostController,
    openDrawer: ()->Unit,
    modifier: Modifier = Modifier
){
    ModalDrawerSheet {
        Column {
            DrawerHeader()
            Divider(thickness = 4.dp, color = NeonBlue)
            DrawerContent(
                modifier = modifier,
                navController = navController,
                openDrawer = openDrawer
            )
        }
    }
}
