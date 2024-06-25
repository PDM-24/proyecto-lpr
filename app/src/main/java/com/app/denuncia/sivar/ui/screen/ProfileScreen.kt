package com.app.denuncia.sivar.ui.screen

import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.app.denuncia.sivar.R
import com.app.denuncia.sivar.ui.components.BottonNavBar.ScreenRoute
import com.app.denuncia.sivar.viewmodel.ViewModelMain
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue50
import com.denuncia.sivar.ui.theme.blue80

@Composable
fun ProfileScreen(
    navController: NavHostController,
    innerPadding: PaddingValues,
    viewModel: ViewModelMain
) {
    //MAIN VIEW MODEL
    val profile = viewModel.profile.collectAsState().value
    //VARIABLES FROM VIEW MODEL
    val mail by remember { mutableStateOf(profile.email) }
    val username by remember { mutableStateOf(profile.username) }
    val firstName by remember { mutableStateOf(profile.name) }
    val lastName by remember { mutableStateOf(profile.surname) }
    //STATES
    var exitDialog by remember { mutableStateOf(false) }
    //TOAST
    var showToast by remember { mutableStateOf(false) }
    var toastMessage by remember { mutableStateOf("") }
    val context = LocalContext.current
    LaunchedEffect(showToast) {
        if (showToast) {
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
            showToast = false // Reset toast state
        }
    }
    //NAVIGATION
    val launchLogin = remember { mutableStateOf(false) }
    val launchEditProfile = remember { mutableStateOf(false) }
    //NAV to Login
    if (launchLogin.value) {
        navController.navigate(ScreenRoute.Login.route) {
            popUpTo(ScreenRoute.Profile.route) { inclusive = true }
        }
        launchLogin.value = false
    }
    //NAV to EditProfile
    if (launchEditProfile.value) {
        navController.navigate(ScreenRoute.EditProfile.route) {
            popUpTo(ScreenRoute.Profile.route) { inclusive = true }
        }
        launchEditProfile.value = false
    }
    //DIALOGS
    if (exitDialog) {
        ExitDialog(
            onDismiss = {
                exitDialog = false
            },
            onConfirm = {
                //TO-DO: CLEAR TOKEN FROM DATA STORE
                //NAVIGATION
                viewModel.logout(context)
                exitDialog = false
                toastMessage = "Sesión cerrada"
                showToast = true
                launchLogin.value = true
            }
        )
    }
    //VIEW
    Column {
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(blue100),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
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
                        if (profile.image.url.isNotEmpty()) {
                            AsyncImage(
                                model = "https://${profile.image.url.removePrefix("http://")}",
                                contentDescription = "profile picture",
                                modifier = Modifier
                                    .size(150.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            AsyncImage(
                                model = "https://cdn-icons-png.freepik.com/512/149/149071.png",
                                contentDescription = "profile picture",
                                modifier = Modifier
                                    .size(150.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "¡Bienvenido ${username}!",
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            color = blue20,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = mail,
                            textDecoration = TextDecoration.Underline,
                            fontSize = 20.sp,
                            color = blue20,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = firstName,
                            fontSize = 18.sp,
                            color = blue20,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = lastName,
                            fontSize = 18.sp,
                            color = blue20,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { launchEditProfile.value = true },
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
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Editar tu perfil", color = blue20)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                exitDialog = true
                            }, // Toggle the exit dialog state to show it
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
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Cerrar sesión", color = blue80)
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun ExitDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = onConfirm,
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
            Text(text = "Cerrar sesión", color = blue20)
        },
        text = {
            Text("¿Estás seguro de que deseas cerrar sesión?", color = blue20)
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        navController = rememberNavController(),
        innerPadding = PaddingValues(0.dp),
        ViewModelMain()
    )
}