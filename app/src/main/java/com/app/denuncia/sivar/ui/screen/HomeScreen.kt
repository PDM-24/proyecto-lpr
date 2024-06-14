package com.app.denuncia.sivar.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.denuncia.sivar.ui.components.PostComponent.PostComp
import com.app.denuncia.sivar.ui.components.TopBar.TopAppBarHome
import com.app.denuncia.sivar.remote.model.PostList


@Composable
fun HomeScreen(navController: NavHostController, innerPadding: PaddingValues){

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
    HomeScreen(rememberNavController(), PaddingValues(0.dp))
}
