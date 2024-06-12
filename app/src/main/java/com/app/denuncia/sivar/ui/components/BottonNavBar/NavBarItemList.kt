package com.app.denuncia.sivar.ui.components.BottonNavBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.app.denuncia.sivar.R

@Composable
fun NavBarItemList(): List<NavBarItem> {
    return listOf(
        NavBarItem(
            "Inicio",
            ScreenRoute.Home.route,
            Icons.Filled.Home,
            Icons.Outlined.Home,
            false,
            12
        ),
        NavBarItem(
            "Historial",
            ScreenRoute.Historial.route,
            Icons.Filled.History,
            Icons.Outlined.History,
            false
        ),
        NavBarItem(
            "Perfil",
            ScreenRoute.Profile.route,
            Icons.Filled.Person,
            Icons.Outlined.Person,
            false
        ),
        NavBarItem(
            "Admin",
            ScreenRoute.Manage.route,
            Icons.Filled.Settings,
            Icons.Outlined.Settings,
            false
        ),
    )
}