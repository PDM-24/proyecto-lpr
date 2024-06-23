package com.app.denuncia.sivar.ui.screen

import android.content.ContentResolver
import android.net.Uri
import android.util.Base64
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.app.denuncia.sivar.model.body.photo
import com.app.denuncia.sivar.model.body.userBody
import com.app.denuncia.sivar.ui.components.BottonNavBar.ScreenRoute
import com.app.denuncia.sivar.ui.components.TopBar.TopBar
import com.app.denuncia.sivar.viewmodel.ViewModelMain
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue50
import com.denuncia.sivar.ui.theme.blue80
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.InputStream


@Composable
fun EditProfileScreen(navController: NavHostController,innerPadding: PaddingValues,viewModel: ViewModelMain) {

    fun uriToBase64(contentResolver: ContentResolver, uri: Uri): String {
        val inputStream: InputStream = contentResolver.openInputStream(uri) ?: return ""
        val buffer = ByteArrayOutputStream()
        var bytesRead: Int
        val data = ByteArray(5000)
        while (inputStream.read(data).also { bytesRead = it } != -1) {
            buffer.write(data, 0, bytesRead)
        }
        val imageBytes = buffer.toByteArray()
        return "data:image/jpeg;base64,${Base64.encodeToString(imageBytes, Base64.DEFAULT)}"
    }

    val context = LocalContext.current

    //IMAGE PICKER
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { selectedImageUri = it }
    )

    //MAIN VIEW MODEL
    val profile by viewModel.profile.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.errorRequest.collectAsState()
    val detailsError by viewModel.detailsErrorRequest.collectAsState()
    val stateUpdateProfile by viewModel.stateUpdateProfile.collectAsState()
    val img = selectedImageUri?.let { uriToBase64(context.contentResolver, it) }
    var launchPhoto by remember { mutableStateOf(false) }
    var launchProfile by remember { mutableStateOf(false) }



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

    //NAVIGATION

    if (launchProfile) {
        AlertDialog(
            onDismissRequest = {
                launchPhoto = false
                selectedImageUri = null
            },
            title = {
                if (loading) {
                    Text(text = "Subiendo....")
                }else{
                    if (stateUpdateProfile) {
                        Text(text = "Enhorabuena")
                    }else{
                        Text(text = "Error")
                    }
                }
            },
            text = {
                if(loading){
                    CircularProgressIndicator(
                        color = blue50,
                        modifier = Modifier.size(200.dp).fillMaxWidth()
                    )
                }else{
                    if (stateUpdateProfile) {
                        Text(text = "Perfil actualizado con exito")
                    }else{
                        if(error){
                            Text(text = detailsError)
                        }else{
                            Text(text = "Error al actualizar perfil")
                        }
                    }
                }
            },
            confirmButton = {
                if(!loading){
                    Button(
                        onClick = {
                            navController.navigate(ScreenRoute.Profile.route) {
                                popUpTo(ScreenRoute.EditProfile.route) { inclusive = true }
                            }
                            launchProfile = false
                        }
                    ){
                        Text(text = "OK")
                    }
                }
            }
        )
    }

    if (launchPhoto) {
        AlertDialog(
            onDismissRequest = {},
            title = {
                if (loading) {
                    Text(text = "Subiendo....")
                }else{
                    if (stateUpdateProfile) {
                        Text(text = "En hora buena")
                    }else{
                        Text(text = "Error")
                    }
                }
            },
            text = {
                if(loading){
                    CircularProgressIndicator(
                        color = blue50,
                        modifier = Modifier.size(200.dp).fillMaxWidth()
                    )
                }else{
                    if (stateUpdateProfile) {
                        Text(text = "Foto de perfil acualizado con exito")
                    }else{
                        if(error){
                            Text(text = detailsError)
                        }else{
                            Text(text = "Error al subir la foto")
                        }
                    }
                }
            },
            confirmButton = {
                if(!loading){
                    Button(
                        onClick = {
                            launchPhoto = false
                            selectedImageUri = null
                        }
                    ){
                        Text(text = "OK")
                    }
                }
            }
        )
    }


    //VIEW
    Column{
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

                        }

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
                        if(selectedImageUri == null){
                            Button(
                                onClick = {
                                    imagePickerLauncher.launch("image/*")
                                },
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
                        }else{
                            Button(
                                onClick = {
                                        if (img != null) {
                                            CoroutineScope(Dispatchers.IO).launch {
                                                viewModel.updatePhoto(profile._id, photo(img), context)
                                                delay(1000)
                                                launchPhoto = true
                                            }
                                        }
                                },
                                shape = RoundedCornerShape(50),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = blue20
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Upload,
                                    contentDescription = "Change profile image",
                                    tint = blue80
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = "Subir foto de perfil", color = blue80)
                            }
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
                        Spacer(modifier = Modifier.height(16.dp))
                        Row {
                            Button(
                                onClick = { launchProfile = true },
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
                                onClick = {
                                    if(tempMail.isNotEmpty() && tempUsername.isNotEmpty() && tempFirstName.isNotEmpty() && tempLastName.isNotEmpty()){
                                        CoroutineScope(Dispatchers.IO).launch {
                                            viewModel.updateProfile(profile._id, userBody(tempUsername, tempFirstName, tempLastName, tempMail, "","",""), context)
                                            delay(1000)
                                            launchProfile = true
                                        }
                                    }else{
                                        Toast.makeText(context, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
                                    }
                                },
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