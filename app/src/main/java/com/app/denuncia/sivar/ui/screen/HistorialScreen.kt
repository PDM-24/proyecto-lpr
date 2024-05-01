package com.app.denuncia.sivar.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.denuncia.sivar.R
import com.app.denuncia.sivar.ui.components.PostComponent.PostComp
import com.app.denuncia.sivar.ui.components.TopBar.TopAppBarHome
import com.app.denuncia.sivar.ui.components.TopBar.TopBar
import com.app.denuncia.sivar.ui.model.PostList

@Composable
fun HistorialScreen(innerPadding: PaddingValues){
    Column(){
        TopBar("Historial", R.drawable.historial)
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ){

        }
    }
}