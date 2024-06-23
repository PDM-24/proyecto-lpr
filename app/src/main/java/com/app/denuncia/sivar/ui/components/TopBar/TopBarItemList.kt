package com.app.denuncia.sivar.ui.components.TopBar

import androidx.compose.runtime.Composable
import com.app.denuncia.sivar.ui.components.BottonNavBar.NavBarItem
import com.app.denuncia.sivar.ui.components.BottonNavBar.ScreenRoute

@Composable
fun TopBarItemList(): List<TopBarItem>{
    return listOf(
        TopBarItem(
            ScreenRoute.CreatePost.route,
        ),
    )
}
