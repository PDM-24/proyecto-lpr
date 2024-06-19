package com.app.denuncia.sivar.ui.components.BottonNavBar

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.app.denuncia.sivar.ui.register.ui.RegisterScreen
import com.app.denuncia.sivar.ui.screen.CreatePostScreen
import com.app.denuncia.sivar.ui.screen.EditProfileScreen
import com.app.denuncia.sivar.ui.screen.FilterScreen
import com.app.denuncia.sivar.ui.screen.HistorialScreen
import com.app.denuncia.sivar.ui.screen.HomeScreen
import com.app.denuncia.sivar.ui.screen.ManageScreen
import com.app.denuncia.sivar.ui.screen.ProfileScreen
import com.app.denuncia.sivar.viewmodel.ViewModelMain
import com.denuncia.sivar.ui.login.ui.LoginScreen

@Composable
fun NavBarGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    viewModel: ViewModelMain
){
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Login.route,
        enterTransition = { EnterTransition.None},
        exitTransition = { ExitTransition.None}
    ) {
        composable(ScreenRoute.Home.route){
            HomeScreen(navController, innerPadding, viewModel)
        }
        composable(ScreenRoute.Historial.route){
            HistorialScreen(navController, innerPadding, viewModel)
        }
        composable(ScreenRoute.Profile.route){
            ProfileScreen(navController, innerPadding, viewModel)
        }
        composable(ScreenRoute.EditProfile.route){
            EditProfileScreen(navController, innerPadding, viewModel)
        }
        composable(ScreenRoute.Manage.route){
            ManageScreen(navController, innerPadding, viewModel)
        }
        composable(ScreenRoute.CreatePost.route){
            CreatePostScreen(navController, innerPadding, viewModel)
        }
        composable(ScreenRoute.Filter.route){
            FilterScreen(navController, innerPadding, viewModel)
        }
        composable(ScreenRoute.Login.route){
            LoginScreen(navController, viewModel)
        }
        composable(ScreenRoute.Register.route){
            RegisterScreen(navController, viewModel)
        }
    }
}