package com.app.denuncia.sivar.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.denuncia.sivar.R
import com.app.denuncia.sivar.ui.components.TopBar.TopBar

@Composable
fun ProfileScreen(innerPadding: PaddingValues){
    Column(){
        TopBar("Tu perfil", R.drawable.editprofile)

    }
}