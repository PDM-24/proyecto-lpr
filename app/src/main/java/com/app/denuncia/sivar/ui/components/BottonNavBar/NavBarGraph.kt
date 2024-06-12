package com.app.denuncia.sivar.ui.components.BottonNavBar

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.app.denuncia.sivar.ui.components.TopBar.TopAppBarHome
import com.app.denuncia.sivar.ui.screen.CreatePostScreen
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
        startDestination = ScreenRoute.Home.route,
        enterTransition = { EnterTransition.None},
        exitTransition = { ExitTransition.None}
    ) {
        composable(ScreenRoute.Home.route){
            HomeScreen(navController, innerPadding)
        }
        composable(ScreenRoute.Historial.route){
            HistorialScreen(navController, innerPadding)
        }
        composable(ScreenRoute.Profile.route){
            ProfileScreen(navController, innerPadding)
        }
        composable(ScreenRoute.Manage.route){
            ManageScreen(navController, innerPadding)
        }
        composable(ScreenRoute.CreatePost.route){
            CreatePostScreen(navController, innerPadding)
        }
    }
}