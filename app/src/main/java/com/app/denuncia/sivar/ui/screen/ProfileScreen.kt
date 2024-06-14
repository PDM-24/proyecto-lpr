package com.app.denuncia.sivar.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.denuncia.sivar.R
import com.app.denuncia.sivar.ui.components.TopBar.TopBar
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue50
import com.denuncia.sivar.ui.theme.blue80


@Composable
fun ProfileScreen(navController: NavHostController, innerPadding: PaddingValues) {
    var mail by remember { mutableStateOf("john@hotmail.com") }
    var username by remember { mutableStateOf("John") }
    var firstName by remember { mutableStateOf("John Estefano") }
    var lastName by remember { mutableStateOf("Canizales Montenegro") }
    var password by remember { mutableStateOf("") }
    var exitDialog by remember { mutableStateOf(false) }
    var editDialog by remember { mutableStateOf(false) }


    if (exitDialog) {
        ExitDialog(
            onDismiss = {
                exitDialog = false
            },
            onConfirm = {
                // Add logic here to handle session closure
                exitDialog = false
                //Ir a Login Screen & borrar token
            }
        )
    }

    if (editDialog) {
        EditProfileDialog(
            mail = mail,
            username = username,
            firstName = firstName,
            lastName = lastName,
            password = password,
            onDismiss = { editDialog = false },
            onConfirm = { newMail, newUsername, newFirstName, newLastName, newPassword ->
                mail = newMail
                username = newUsername
                firstName = newFirstName
                lastName = newLastName
                password = newPassword
                editDialog = false
            }
        )
    }

    Column {
        TopBar("Perfil", R.drawable.editprofile, navController, showBackIcon = false)
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(blue100),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(16.dp)
                    .wrapContentHeight(),
                colors = CardDefaults.cardColors(containerColor = blue100),
                border = BorderStroke(2.dp, blue50),
                shape = RoundedCornerShape(20.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logowhite),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(width = 100.dp, height = 100.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.photoexample),
                        contentDescription = "profile picture",
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "¡Bienvenido ${username}!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = blue20,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = mail,
                        textDecoration = TextDecoration.Underline,
                        fontSize = 16.sp,
                        color = blue20,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = firstName,
                        fontSize = 16.sp,
                        color = blue20,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = lastName,
                        fontSize = 16.sp,
                        color = blue20,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { editDialog = true },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = blue80
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Icon",
                            tint = blue20
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Editar tu perfil", color = blue20)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { exitDialog = true }, // Toggle the exit dialog state to show it
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = blue20
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Close session Icon",
                            tint = blue80
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Cerrar sesión", color = blue80)
                    }
                }
            }
        }
    }
}

@Composable
fun ExitDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss, // Here we expect to pass a lambda that does nothing
        confirmButton = {
            Button(
                onClick =

                onConfirm,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = blue20
                )
            ) {
                Text(text = "Confirmar", color = blue80)
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = blue80
                )
            ) {
                Text(text = "Cancelar", color = blue20)
            }
        },
        title = {
            Text(text = "Cerrar sesión")
        },
        text = {
            Text("¿Estás seguro de que deseas cerrar sesión?")
        }
    )
}

@Composable
fun EditProfileDialog(
    mail: String,
    username: String,
    firstName: String,
    lastName: String,
    password: String,
    onDismiss: () -> Unit,
    onConfirm: (String, String, String, String, String) -> Unit
) {
    var tempMail by remember { mutableStateOf(mail) }
    var tempUsername by remember { mutableStateOf(username) }
    var tempFirstName by remember { mutableStateOf(firstName) }
    var tempLastName by remember { mutableStateOf(lastName) }
    var tempPassword by remember { mutableStateOf(password) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(tempMail, tempUsername, tempFirstName, tempLastName, tempPassword)
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = blue20)
            ) {
                Text(text = "Confirmar", color = blue80)
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = blue80)
            ) {
                Text(text = "Cancelar", color = blue20)
            }
        },
        title = {
            Text(text = "Editar Perfil")
        },
        text = {
            Column {
                OutlinedTextField(
                    value = tempMail,
                    onValueChange = { tempMail = it },
                    label = { Text("Email") }
                )
                OutlinedTextField(
                    value = tempUsername,
                    onValueChange = { tempUsername = it },
                    label = { Text("Username") }
                )
                OutlinedTextField(
                    value = tempFirstName,
                    onValueChange = { tempFirstName = it },
                    label = { Text("First Name") }
                )
                OutlinedTextField(
                    value = tempLastName,
                    onValueChange = { tempLastName = it },
                    label = { Text("Last Name") }
                )
                OutlinedTextField(
                    value = tempPassword,
                    onValueChange = { tempPassword = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                )
            }
        }
    )
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController(), innerPadding = PaddingValues(0.dp))
}