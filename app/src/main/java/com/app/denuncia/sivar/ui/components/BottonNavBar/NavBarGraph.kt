package com.app.denuncia.sivar.ui.components.BottonNavBar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.app.denuncia.sivar.ui.screen.ExitScreen
import com.app.denuncia.sivar.ui.screen.HistorialScreen
import com.app.denuncia.sivar.ui.screen.HomeScreen
import com.app.denuncia.sivar.ui.screen.ManageScreen
import com.app.denuncia.sivar.ui.screen.ProfileScreen

@Composable
fun NavBarGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
){
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Home.route
    ) {
        composable(ScreenRoute.Home.route){
            HomeScreen(innerPadding)
        }
        composable(ScreenRoute.Historial.route){
            HistorialScreen(innerPadding)
        }
        composable(ScreenRoute.Profile.route){
            ProfileScreen(innerPadding)
        }
        composable(ScreenRoute.Manage.route){
            ManageScreen(innerPadding)
        }
    }

}