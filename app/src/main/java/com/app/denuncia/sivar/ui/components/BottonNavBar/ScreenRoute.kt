package com.app.denuncia.sivar.ui.components.BottonNavBar

sealed class ScreenRoute (var route: String) {
    object Home : ScreenRoute("home")
    object Historial : ScreenRoute("historial")
    object Profile : ScreenRoute ("profile")
    object Manage : ScreenRoute ("manage")
    object CreatePost : ScreenRoute ("createPost")
    object Filter : ScreenRoute ("filter")
    object Login : ScreenRoute("login")

    object Register : ScreenRoute ("register")
}