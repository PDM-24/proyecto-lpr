package com.app.denuncia.sivar.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.denuncia.sivar.ui.components.PostComponent.PostComp
import com.app.denuncia.sivar.ui.components.TopBar.TopAppBarHome
import com.app.denuncia.sivar.model.PostList
import com.app.denuncia.sivar.ui.components.BottonNavBar.NavBarItemList
import com.app.denuncia.sivar.ui.components.TopBar.TopBarItemList
import com.app.denuncia.sivar.viewmodel.ViewModelMain


@Composable
fun HomeScreen(navController: NavHostController, innerPadding: PaddingValues, viewModelMain: ViewModelMain){

    Column(){
        TopAppBarHome(navController)
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ){
            items(PostList){
                    postItem -> PostComp(postItem)
            }
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview(){
    val viewModel =  ViewModelMain()
    HomeScreen(rememberNavController(), PaddingValues(0.dp), viewModel)
}
