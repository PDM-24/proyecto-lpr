package com.app.denuncia.sivar.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.app.denuncia.sivar.ui.components.BottonNavBar.ScreenRoute
import com.app.denuncia.sivar.ui.components.PostComponent.PostComp
import com.app.denuncia.sivar.viewmodel.ViewModelMain
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController, innerPadding: PaddingValues, viewModel: ViewModelMain) {
    val denuncias by viewModel.denuncias.collectAsState()
    val isRefreshing by viewModel.loading.collectAsState()

    val context = LocalContext.current


    var search by viewModel.search

    val session by viewModel.session.collectAsState()
    val loadingSession by viewModel.loadingSession.collectAsState()
    val launchSession = remember {mutableStateOf(false)}

    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            search = ""
            viewModel.getComplainst(search, "", "")
            viewModel.verifyToken(context)
            delay(1000)
            launchSession.value = true
        }
    }

    if(launchSession.value){
        if(!loadingSession){
            if(!session){
                navController.navigate(ScreenRoute.Login.route) {
                    popUpTo(ScreenRoute.Home.route) { inclusive = true }
                }
            }
        }
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

