package com.app.denuncia.sivar.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.app.denuncia.sivar.R
import com.app.denuncia.sivar.ui.components.TopBar.TopBar
import com.denuncia.sivar.ui.theme.blue100

@Composable
fun ProfileScreen(navController: NavHostController, innerPadding: PaddingValues){
    Scaffold(
        modifier = Modifier.background(blue100),
        topBar = {
            TopBar("Tu perfil", R.drawable.editprofile, navController, showBackIcon = false)
        }
    )
    {
    }
}