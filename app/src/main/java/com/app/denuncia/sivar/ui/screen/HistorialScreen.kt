package com.app.denuncia.sivar.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.denuncia.sivar.R
import com.app.denuncia.sivar.ui.components.PostComponent.PostComp
import com.app.denuncia.sivar.ui.components.TopBar.TopBar
import com.app.denuncia.sivar.viewmodel.ViewModelMain
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20

@Composable
fun HistorialScreen(navController: NavHostController, innerPadding: PaddingValues, viewModel: ViewModelMain){
    val userDenuncias = viewModel.getUserDenuncias()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Tu historial de denuncias",
                color = blue20,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )
        }
        LazyColumn(){
            items(userDenuncias) { postItem ->
                PostComp(postItem, viewModel)
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HistorialScreenPreview(){
    HistorialScreen(rememberNavController(), PaddingValues(0.dp), ViewModelMain())
}

