package com.app.denuncia.sivar.ui.screen

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.ChangeCircle
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.app.denuncia.sivar.R
import com.app.denuncia.sivar.ui.components.BottonNavBar.ScreenRoute
import com.app.denuncia.sivar.ui.components.TopBar.TopBar
import com.app.denuncia.sivar.viewmodel.ViewModelMain
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue50
import com.denuncia.sivar.ui.theme.blue80

@Composable
fun EditProfileScreen(
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
    //AUX VARIABLES TO PATCH
    var tempMail by remember { mutableStateOf(mail) }
    var tempUsername by remember { mutableStateOf(username) }
    var tempFirstName by remember { mutableStateOf(firstName) }
    var tempLastName by remember { mutableStateOf(lastName) }
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    //STATES
    var showToast by remember { mutableStateOf(false) }
    var toastMessage by remember { mutableStateOf("") }
    var editDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    //TOAST
    LaunchedEffect(showToast) {
        if (showToast) {
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
            showToast = false // Reset toast state
        }
    }
    //NAVIGATION
    val launchProfile = remember { mutableStateOf(false) }
    if (launchProfile.value) {
        navController.navigate(ScreenRoute.Profile.route) {
            popUpTo(ScreenRoute.EditProfile.route) { inclusive = true }
        }
        launchProfile.value = false
    }
    //IMAGE PICKER
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            selectedImageUri = uri
        }
    )
    //DIALOGS
    if (editDialog) {
        EditProfileDialog(
            onDismiss = {
                //ROLLBACK without save changes
                editDialog = false
            },
            onConfirm = {
                //SAVE changes and return
                //Validations
                if(tempMail.isEmpty()||tempUsername.isEmpty()||tempFirstName.isEmpty()||tempLastName.isEmpty()){
                    toastMessage = "Error no se permiten campos vacios"
                    showToast = true
                    editDialog = false
                }else{
                    //Main View Model
                    //Navigation
                    editDialog = false
                    toastMessage = "Perfil actualizado"
                    showToast = true
                    launchProfile.value = true
                }
            }
        )
    }
    //VIEW
    Column{
        TopBar("Perfil", R.drawable.editprofile, navController, showBackIcon = false)
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
                        Text(
                            text = "Edita tu perfil",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = blue20,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        if (selectedImageUri != null) {
                            Image(
                                painter = rememberAsyncImagePainter(selectedImageUri),
                                contentDescription = "Selected Image",
                                modifier = Modifier
                                    .size(150.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        } else {
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
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { imagePickerLauncher.launch("image/*") },
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = blue20
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.ChangeCircle,
                                contentDescription = "Change profile image",
                                tint = blue80
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Cambiar foto de perfil", color = blue80)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = tempMail,
                            onValueChange = { tempMail = it },
                            label = { Text("Correo electronico", color = blue20) },
                            maxLines = 1,
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                errorBorderColor = Color.Red,
                                cursorColor = blue50,
                                focusedBorderColor = blue50,
                                unfocusedBorderColor = blue50,
                                disabledBorderColor = blue50,
                                focusedLabelColor = blue80,
                                unfocusedLabelColor = blue80
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = tempUsername,
                            onValueChange = { tempUsername = it },
                            label = { Text("Nombre de usuario", color = blue20) },
                            maxLines = 1,
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                errorBorderColor = Color.Red,
                                cursorColor = blue50,
                                focusedBorderColor = blue50,
                                unfocusedBorderColor = blue50,
                                disabledBorderColor = blue50,
                                focusedLabelColor = blue80,
                                unfocusedLabelColor = blue80
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = tempFirstName,
                            onValueChange = { tempFirstName = it },
                            label = { Text("Nombre", color = blue20) },
                            maxLines = 1,
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                errorBorderColor = Color.Red,
                                cursorColor = blue50,
                                focusedBorderColor = blue50,
                                unfocusedBorderColor = blue50,
                                disabledBorderColor = blue50,
                                focusedLabelColor = blue80,
                                unfocusedLabelColor = blue80
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = tempLastName,
                            onValueChange = { tempLastName = it },
                            label = { Text("Apellido", color = blue20) },
                            maxLines = 1,
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                errorBorderColor = Color.Red,
                                cursorColor = blue50,
                                focusedBorderColor = blue50,
                                unfocusedBorderColor = blue50,
                                disabledBorderColor = blue50,
                                focusedLabelColor = blue80,
                                unfocusedLabelColor = blue80
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = oldPassword,
                            onValueChange = { oldPassword = it },
                            label = { Text("Contraseña actual", color = blue20) },
                            visualTransformation = PasswordVisualTransformation(),
                            maxLines = 1,
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                errorBorderColor = Color.Red,
                                cursorColor = blue50,
                                focusedBorderColor = blue50,
                                unfocusedBorderColor = blue50,
                                disabledBorderColor = blue50,
                                focusedLabelColor = blue80,
                                unfocusedLabelColor = blue80
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = newPassword,
                            onValueChange = { newPassword = it },
                            label = { Text("Nueva contraseña", color = blue20) },
                            visualTransformation = PasswordVisualTransformation(),
                            maxLines = 1,
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                errorBorderColor = Color.Red,
                                cursorColor = blue50,
                                focusedBorderColor = blue50,
                                unfocusedBorderColor = blue50,
                                disabledBorderColor = blue50,
                                focusedLabelColor = blue80,
                                unfocusedLabelColor = blue80
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row {
                            Button(
                                onClick = { launchProfile.value = true },
                                shape = RoundedCornerShape(50),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = blue20
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Cancel,
                                    contentDescription = "Cancel action",
                                    tint = blue80
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = "Cancelar", color = blue80)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = { editDialog = true },
                                shape = RoundedCornerShape(50),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = blue80
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Save,
                                    contentDescription = "Save Icon",
                                    tint = blue20
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = "Guardar cambios", color = blue20)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EditProfileDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
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
            Text(text = "Actualizar perfil", color = blue20)
        },
        text = {
            Text("¿Estás seguro de actualizar tu perfil?", color = blue20)
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen(
        navController = rememberNavController(),
        innerPadding = PaddingValues(0.dp),
        ViewModelMain()
    )
}