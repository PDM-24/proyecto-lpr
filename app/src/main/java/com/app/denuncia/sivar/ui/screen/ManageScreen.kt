package com.app.denuncia.sivar.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.denuncia.sivar.model.mongoose.Usuario
import com.app.denuncia.sivar.ui.components.BottonNavBar.ScreenRoute
import com.app.denuncia.sivar.ui.components.ListUsersComps.UserTable
import com.app.denuncia.sivar.viewmodel.ViewModelMain
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue50
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ManageScreen(navController: NavHostController, innerPadding: PaddingValues,  viewModel: ViewModelMain) {


    var search by remember { mutableStateOf("") }
    val users by viewModel.usuarios.collectAsState()
    val loading by viewModel.loading.collectAsState()

    val profile by viewModel.profile.collectAsState()

    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedUser by remember { mutableStateOf(Usuario()) }
    var showRoleChangeDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current

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
                    popUpTo(ScreenRoute.Manage.route) { inclusive = true }
                }
            }
        }
    }

    LaunchedEffect(search) {
       if(!loading){
           if(search.isNotEmpty()){
               viewModel.getUsers(search)
           }else{
               viewModel.getUsers("")
           }
       }
    }


    fun onRoleChange(user: Usuario) {
        selectedUser = user
        showRoleChangeDialog  = true
    }

    fun onDelete(user: Usuario) {
        selectedUser = user
        showDeleteDialog = true
    }

    fun confirmRoleChange() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.verifyToken(context)
            delay(500)
            if(profile.rol == "Administrador"){
                val updated = selectedUser.copy(rol = if (selectedUser.rol == "Moderador") "Usuario" else "Moderador")
                viewModel.changeRol(updated._id, updated.rol)
                delay(1000)
                if (!viewModel.loading.value){
                    if (viewModel.stateUpdateUser.value) {
                        viewModel.getUsers(search)
                        Toast.makeText(context, "Rol actualizado", Toast.LENGTH_SHORT).show()
                    }else{
                        if(viewModel.errorRequest.value){
                            Toast.makeText(context, viewModel.detailsErrorRequest.value, Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(context, "Error al cambiar el rol", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                selectedUser = Usuario()
            }else{
                Toast.makeText(context, "No tienes permiso de modificar usuarios", Toast.LENGTH_SHORT).show()
            }
        }

        showRoleChangeDialog = false
    }



    fun confirmDelete() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.verifyToken(context)
            delay(500)
            if(profile.rol == "Administrador"){
                viewModel.deleteUser(selectedUser._id)
                delay(1000)
                if(viewModel.stateDeleteUser.value){
                    viewModel.getUsers(search)
                    Toast.makeText(context, "Usuario eliminado", Toast.LENGTH_SHORT).show()
                }else{
                    if(viewModel.errorRequest.value){
                        Toast.makeText(context, viewModel.detailsErrorRequest.value, Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context, "Error al eliminar el usuario", Toast.LENGTH_SHORT).show()
                    }
                }
                    selectedUser = Usuario()
            }else{
                Toast.makeText(context, "No tienes permiso de eliminar usuarios", Toast.LENGTH_SHORT).show()
            }
        }
        showDeleteDialog = false
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Usuarios Registrados",
                    fontSize = 19.sp
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Box(modifier = Modifier.padding(start = 16.dp)) {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(blue50)
                        .height(30.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.White,
                            modifier = Modifier.size(25.dp)
                        )
                        BasicTextField(
                            value = search,
                            onValueChange = {
                                search = it
                            },
                            singleLine = true,
                            textStyle = TextStyle(
                                color = blue20,
                                fontSize = 16.sp
                            ),
                            cursorBrush = SolidColor(blue20),
                            modifier = Modifier.width(130.dp),
                        ) { innerTextField ->
                            if (search.isEmpty()) {
                                Text(
                                    text = "Buscar...",
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                            }
                            innerTextField()
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            SwipeRefresh(
                state = SwipeRefreshState(loading),
                onRefresh = {
                    viewModel.getUsers(search)
                }
            ) {
                UserTable(users = users.filter { it._id != viewModel.profile.value._id }, onRoleChange = ::onRoleChange, onDelete = ::onDelete)
            }

        }
    }


    if (showRoleChangeDialog) {
        AlertDialog(
            onDismissRequest = { showRoleChangeDialog = false },
            title = { Text("Cambiar Rol") },
            text = {
                if(selectedUser.rol === "Moderador"){
                Text("¿Estás seguro de que quieres cambiar el rol de ${selectedUser.username} a Usuario?")
                }else{
                Text("¿Estás seguro de que quieres cambiar el rol de ${selectedUser.username} a Moderador?")
                }
            },
            confirmButton = {
                Button(onClick = { confirmRoleChange() }) {
                    Text(text= "Aceptar", color = blue20)
                }
            },
            dismissButton = {
                Button(onClick = { showRoleChangeDialog = false }) {
                    Text(text= "Cancelar", color = blue20)
                }
            }
        )
    }


    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Eliminar Usuario") },
            text = { Text("¿Estás seguro de que quieres eliminar a este usuario?") },
            confirmButton = {
                Button(onClick = { confirmDelete() }) {
                    Text(text= "Sí", color = blue20)
                }
            },
            dismissButton = {
                Button(onClick = { showDeleteDialog = false }) {
                    Text(text= "No", color = blue20)
                }
            }
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ManageScreenPreview() {
    ManageScreen(navController = rememberNavController(), innerPadding = PaddingValues(0.dp), ViewModelMain())
}
