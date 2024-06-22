package com.app.denuncia.sivar.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.denuncia.sivar.ui.components.PostComponent.PostComp
import com.app.denuncia.sivar.viewmodel.ViewModelMain
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState

@Composable
fun HomeScreen(navController: NavHostController, innerPadding: PaddingValues, viewModel: ViewModelMain) {
    val denuncias by viewModel.denuncias.collectAsState()
    val isRefreshing by viewModel.loading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getComplainst("", "", "")
    }

    Column(
        modifier = Modifier.padding(innerPadding)
    ){
        SwipeRefresh(
            state = SwipeRefreshState(isRefreshing),
            onRefresh = {
                viewModel.getComplainst("", "", "")
            }
        ) {
            LazyColumn(
            ) {
                items(denuncias) { postItem ->
                    PostComp(postItem, viewModel)
                }
            }
        }
    }
}

