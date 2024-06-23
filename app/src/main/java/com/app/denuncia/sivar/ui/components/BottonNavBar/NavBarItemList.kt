package com.app.denuncia.sivar.ui.components.BottonNavBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PostAdd
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.app.denuncia.sivar.R
import com.app.denuncia.sivar.viewmodel.ViewModelMain

@Composable
fun NavBarItemList(viewModel: ViewModelMain): List<NavBarItem> {

    val profile by viewModel.profile.collectAsState()

    if (profile.rol == "Administrador"){
        return listOf(
            NavBarItem(
                "Inicio",
                ScreenRoute.Home.route,
                Icons.Filled.Home,
                Icons.Outlined.Home,
            ),
            NavBarItem(
                "Historial",
                ScreenRoute.Historial.route,
                Icons.Filled.History,
                Icons.Outlined.History,
            ),
            NavBarItem(
                "Crear",
                ScreenRoute.CreatePost.route,
                Icons.Filled.PostAdd,
                Icons.Outlined.PostAdd,
            ),
            NavBarItem(
                "Perfil",
                ScreenRoute.Profile.route,
                Icons.Filled.Person,
                Icons.Outlined.Person,
            ),
            NavBarItem(
                "Admin",
                ScreenRoute.Manage.route,
                Icons.Filled.Settings,
                Icons.Outlined.Settings,
            ),
        )
    }
    else{
        return listOf(
            NavBarItem(
                "Inicio",
                ScreenRoute.Home.route,
                Icons.Filled.Home,
                Icons.Outlined.Home,
            ),
            NavBarItem(
                "Historial",
                ScreenRoute.Historial.route,
                Icons.Filled.History,
                Icons.Outlined.History,
            ),
            NavBarItem(
                "Crear",
                ScreenRoute.CreatePost.route,
                Icons.Filled.PostAdd,
                Icons.Outlined.PostAdd,
            ),
            NavBarItem(
                "Perfil",
                ScreenRoute.Profile.route,
                Icons.Filled.Person,
                Icons.Outlined.Person,
            ),
        )
    }
}