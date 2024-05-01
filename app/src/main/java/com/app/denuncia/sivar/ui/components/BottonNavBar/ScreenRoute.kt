package com.app.denuncia.sivar.ui.components.BottonNavBar

sealed class ScreenRoute (var route: String) {
    object Home : ScreenRoute("home")
    object Historial : ScreenRoute("historial")
    object Profile : ScreenRoute ("profile")
    object Manage : ScreenRoute ("manage")
}