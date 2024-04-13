package com.app.denuncia.sivar.ui.components.BottonNavBar

data class NavBarItem(
    val title: String,
    val route: String,
    val selectedIconResId: Int,
    val hasNews: Boolean,
    val badgetCount: Int? = null
)
