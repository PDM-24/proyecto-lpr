package com.app.denuncia.sivar.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.denuncia.sivar.ui.components.BottonNavBar.ScreenRoute
import com.app.denuncia.sivar.ui.components.PostComponent.PostComp
import com.app.denuncia.sivar.viewmodel.ViewModelMain
import com.denuncia.sivar.ui.theme.blue20
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HistorialScreen(navController: NavHostController, innerPadding: PaddingValues, viewModel: ViewModelMain){
    val userDenuncias = viewModel.getUserDenuncias()
    val isRefreshing by viewModel.loading.collectAsState()


    val session by viewModel.session.collectAsState()
    val loadingSession by viewModel.loadingSession.collectAsState()
    val launchSession = remember { mutableStateOf(false) }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.verifyToken(context)
            delay(1000)
            launchSession.value = true
        }
    }

    if(launchSession.value){
        if(!loadingSession){
            if(!session){
                navController.navigate(ScreenRoute.Login.route) {
                    popUpTo(ScreenRoute.Historial.route) { inclusive = true }
                }
            }
        }
    }

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
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
            Divider(modifier = Modifier.height(0.7.dp))
        }
        SwipeRefresh(
            state = SwipeRefreshState(isRefreshing),
            onRefresh = {
                viewModel.getComplainst("", "", "")
            }
        ) {
            LazyColumn{
                items(userDenuncias) { postItem ->
                    PostComp(postItem, viewModel)
                    Divider(modifier = Modifier.height(0.7.dp))
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HistorialScreenPreview(){
    HistorialScreen(rememberNavController(), PaddingValues(0.dp), ViewModelMain())
}

