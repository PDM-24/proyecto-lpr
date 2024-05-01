package com.app.denuncia.sivar.ui.components.BottonNavBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.app.denuncia.sivar.R

@Composable
fun NavBarItemList(): List<NavBarItem>{
    return listOf(
        NavBarItem(
            "Inicio",
            ScreenRoute.Home.route,
            R.drawable.ic_home,
            false,
            12
        ),
        NavBarItem(
            "Historial",
            ScreenRoute.Historial.route,
            R.drawable.historial,
            false
        ),
        NavBarItem(
            "Perfil",
            ScreenRoute.Profile.route,
            R.drawable.editprofile,
            false,
        ),
        NavBarItem(
            "Admin",
            ScreenRoute.Manage.route,
            R.drawable.manageuser,
            false,
        ),
    )
}