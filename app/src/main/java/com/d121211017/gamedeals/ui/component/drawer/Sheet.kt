package com.d121211017.gamedeals.ui.component.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun DrawerSheet(
    navController: NavHostController,
    openDrawer: ()->Unit,
    modifier: Modifier = Modifier
){
    ModalDrawerSheet {
        Column {
            DrawerHeader()
            Divider(thickness = 4.dp)
            DrawerContent(
                modifier = modifier,
                navController = navController,
                openDrawer = openDrawer
            )
        }
    }
}
