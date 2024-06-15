package com.app.denuncia.sivar.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.denuncia.sivar.R
import com.app.denuncia.sivar.ui.components.ListUsersComps.User
import com.app.denuncia.sivar.ui.components.ListUsersComps.UserTable
import com.app.denuncia.sivar.ui.components.ListUsersComps.userList
import com.app.denuncia.sivar.ui.components.TopBar.TopBar
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue50

@Composable
fun ManageScreen(navController: NavHostController, innerPadding: PaddingValues) {
    val textState = remember { mutableStateOf(TextFieldValue()) }

    var users by remember { mutableStateOf(userList.toMutableStateList()) }
    var filteredUsers by remember { mutableStateOf(users) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var userToDelete by remember { mutableStateOf<User?>(null) }
    var showRoleChangeDialog by remember { mutableStateOf(false) }
    var userToChangeRole by remember { mutableStateOf<User?>(null) }

    fun filterUsers() {
        val query = textState.value.text.lowercase()
        filteredUsers = if (query.isEmpty()) {
            users
        } else {
            users.filter {
                it.name.lowercase().contains(query) || it.email.lowercase().contains(query)
            }.toMutableStateList()
        }
    }

    fun onRoleChange(user: User) {
        userToChangeRole = user
        showRoleChangeDialog = true
    }

    fun confirmRoleChange() {
        userToChangeRole?.let { user ->
            val updatedUser = user.copy(role = if (user.role == "Admin") "User" else "Admin")
            val index = users.indexOf(user)
            if (index >= 0) {
                users[index] = updatedUser
                filterUsers()
            }
        }
        showRoleChangeDialog = false
    }

    fun onDelete(user: User) {
        userToDelete = user
        showDeleteDialog = true
    }

    fun confirmDelete() {
        userToDelete?.let { user ->
            users.remove(user)
            filterUsers()
        }
        showDeleteDialog = false
    }

    fun onLoadMore() {
        val moreUsers = listOf(
            User(users.size + 1, "New User ${users.size + 1}", "newuser${users.size + 1}@example.com", "User"),
            User(users.size + 2, "New User ${users.size + 2}", "newuser${users.size + 2}@example.com", "Admin"),
        )
        users = (users + moreUsers).toMutableStateList()
        filterUsers()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(blue100)
    ) {
        TopBar("Administrar usuarios", R.drawable.manageuser, navController, showBackIcon = false)
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
                    color = blue20,
                    fontSize = 19.sp
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
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
                        tint = blue20,
                        modifier = Modifier.size(25.dp)
                    )
                    BasicTextField(
                        value = textState.value,
                        onValueChange = {
                            textState.value = it
                            filterUsers()
                        },
                        singleLine = true,
                        textStyle = TextStyle(
                            color = blue20,
                            fontSize = 16.sp
                        ),
                        cursorBrush = SolidColor(blue20),
                        modifier = Modifier.width(130.dp),
                    ) { innerTextField ->
                        if (textState.value.text.isEmpty()) {
                            Text(
                                text = "Buscar...",
                                color = blue20,
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            UserTable(users = filteredUsers, onRoleChange = ::onRoleChange, onDelete = ::onDelete, onLoadMore = ::onLoadMore)
        }
    }


    if (showRoleChangeDialog) {
        AlertDialog(
            onDismissRequest = { showRoleChangeDialog = false },
            title = { Text("Cambiar Rol") },
            text = { Text("¿Estás seguro de que quieres cambiar el rol de este usuario?") },
            confirmButton = {
                Button(onClick = { confirmRoleChange() }) {
                    Text(text= "Sí", color = blue20)
                }
            },
            dismissButton = {
                Button(onClick = { showRoleChangeDialog = false }) {
                    Text(text= "No", color = blue20)
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
    ManageScreen(navController = rememberNavController(), innerPadding = PaddingValues(0.dp))
}
