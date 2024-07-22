package com.app.denuncia.sivar.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.denuncia.sivar.remote.model.DepartamentList
import com.app.denuncia.sivar.ui.components.BottonNavBar.ScreenRoute
import com.app.denuncia.sivar.ui.components.FilterComp.CustomDropdownDepartment
import com.app.denuncia.sivar.ui.components.FilterComp.CustomDropdownKind
import com.app.denuncia.sivar.ui.components.PostComponent.PostComp
import com.app.denuncia.sivar.viewmodel.ViewModelMain
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue50
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FilterScreen(navController: NavHostController, innerPadding: PaddingValues, viewModel: ViewModelMain) {

    var departamento by remember { mutableStateOf("") }
    var categorie by remember { mutableStateOf("") }
    val search by viewModel.search

    val denuncias = viewModel.denuncias.collectAsState().value

    val categorias = viewModel.categorias.collectAsState().value

    LaunchedEffect(search, departamento, categorie) {
        viewModel.getComplainst(search, departamento, categorie)
    }

    val session by viewModel.session.collectAsState()
    val loadingSession by viewModel.loadingSession.collectAsState()
    val launchSession = remember {mutableStateOf(false)}

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
                    popUpTo(ScreenRoute.Home.route) { inclusive = true }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = "Filter Icon",
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.size(5.dp))
                //Text(text = "Filtrar", color = blue20, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.size(5.dp))
            CustomDropdownDepartment(
                options = DepartamentList,
                selectedOption = departamento.ifEmpty { "Departamento" },
                onOptionSelected = {
                    departamento = it.nombre
                },
                height = 25,
                background = blue50
            )
            Spacer(modifier = Modifier.size(5.dp))
            CustomDropdownKind(
                options = categorias,
                selectedOption = categorie.ifEmpty { "Categoría" },
                onOptionSelected = {
                    categorie = it.name
                },
                height = 25,
                background = blue50
            )
        }
        Divider(modifier = Modifier.height(0.7.dp))
        LazyColumn(
            modifier = Modifier
        ){
            items(denuncias){
                    postItem -> PostComp(postItem, viewModel)
                Divider(modifier = Modifier.height(0.7.dp))
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FilterScreenPreview() {
    FilterScreen(navController = rememberNavController(), PaddingValues(0.dp), ViewModelMain())
}