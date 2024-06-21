package com.app.denuncia.sivar.ui.components.BottonNavBar

import androidx.compose.ui.graphics.vector.ImageVector

data class NavBarItem(
    val title: String,
    val route: String,
    val filledIcon: ImageVector,
    val outlinedIcon: ImageVector,
)