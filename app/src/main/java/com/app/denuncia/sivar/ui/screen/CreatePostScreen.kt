package com.app.denuncia.sivar.ui.screen

import android.content.ContentResolver
import android.net.Uri
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.denuncia.sivar.remote.model.DepartamentList
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue50
import com.denuncia.sivar.ui.theme.blue80
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Publish
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.app.denuncia.sivar.ui.components.BottonNavBar.ScreenRoute
import com.app.denuncia.sivar.ui.components.FilterComp.CustomDropdownDepartment
import com.app.denuncia.sivar.ui.components.FilterComp.CustomDropdownKind
import com.app.denuncia.sivar.viewmodel.ViewModelMain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.time.LocalDateTime

@Composable
fun CreatePostScreen(navController: NavHostController, innerPadding: PaddingValues,  viewModel: ViewModelMain)
{

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
    val contentResolver = context.contentResolver

    val profile by viewModel.profile.collectAsState()
    val categories by viewModel.categorias.collectAsState()
    val isLoading by viewModel.loading.collectAsState()
    val stateUpload by viewModel.stateUploadComplaint.collectAsState()
    val error by viewModel.errorRequest.collectAsState()
    val detailsError by viewModel.detailsErrorRequest.collectAsState()


    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val user = profile._id
    var departamento by remember { mutableStateOf("Seleccione el departamento") }
    var categoria by remember { mutableStateOf("Seleccione una categoria") }
    var idcateoria by remember { mutableStateOf("") }
    var details by remember { mutableStateOf("") }
    val date = LocalDateTime.now().toString()
    val img = imageUri?.let { uriToBase64(contentResolver, it) }

    var launchUpload by remember { mutableStateOf(false) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { imageUri = it }
    )
    //NAVIGATION TO HOME
    val launch = remember { mutableStateOf(false) }
    if (launch.value) {
        navController.navigate(ScreenRoute.Home.route) {
            popUpTo(ScreenRoute.CreatePost.route) { inclusive = true }
        }
        launch.value = false
    }


    val session by viewModel.session.collectAsState()
    val loadingSession by viewModel.loadingSession.collectAsState()
    val launchSession = remember {mutableStateOf(false)}

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

    if(launchUpload){
        AlertDialog(
            onDismissRequest = { launchUpload = false},
            title = {
                if(isLoading){
                    Text(text = "Subiendo....")
                }else{
                    if(stateUpload){
                        Text(text = "Denuncia enviada")
                    }else{
                        Text(text = "Error al enviar la denuncia")
                    }
                }
            },
            text = {
                if(isLoading){
                    CircularProgressIndicator(
                        color = blue50,
                        modifier = Modifier.size(200.dp),
                    )
                }else{
                    if(stateUpload){
                        Text(text = "Denuncia enviada")
                    }else{
                        if(error){
                            Text(text = detailsError)
                        }else{
                            Text(text = "Error al enviar la denuncia")
                        }
                    }
                }
            },
            confirmButton = {
                if(!isLoading){
                    Button(
                        onClick = {
                            launchUpload = false
                            launch.value = true //NAVIGATION TO HOME
                        }
                    ) {
                        Text(text = "OK", color = blue20)
                    }
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent,
                    ),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(0.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if(profile.image.url.isNotEmpty()){
                                AsyncImage(
                                    model = "https://${profile.image.url.removePrefix("http://")}",
                                    contentDescription = "profilePhoto",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(RoundedCornerShape(50.dp))
                                )
                            }else{
                                AsyncImage(
                                    model = "https://cdn-icons-png.freepik.com/512/149/149071.png",
                                    contentDescription = "profilePhoto",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(RoundedCornerShape(50.dp))
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = profile.username,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        CustomDropdownDepartment(
                            options = DepartamentList,
                            selectedOption = departamento,
                            onOptionSelected = { departamento = it.nombre },
                            height = 30,
                            background = blue50,
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                        CustomDropdownKind(
                            options = categories,
                            selectedOption = categoria,
                            onOptionSelected = {
                                categoria = it.name
                                idcateoria = it._id
                            },
                            height = 30,
                            background = blue50
                        )
                        OutlinedTextField(
                            shape = RoundedCornerShape(10.dp),
                            value = details,
                            onValueChange = { details = it },
                            label = {
                                        Text(
                                            text = "Descripción de la denuncia",
                                            color = MaterialTheme.colorScheme.onBackground,
                                        )
                                    },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = blue50,
                                disabledBorderColor = blue50,
                                errorBorderColor = Color.Red,
                                focusedLabelColor = blue80,
                                unfocusedLabelColor = blue80
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                            ,
                            maxLines = 3
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                        if (imageUri != null) {
                            Image(
                                painter = rememberAsyncImagePainter(imageUri),
                                contentDescription = "Selected Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.height(7.dp))
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(blue50)
                                .height(30.dp)
                                .width(180.dp)
                                .clickable { imagePickerLauncher.launch("image/*") },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Image, contentDescription = "senIcon", tint = Color.White)
                                Text("Agregar imagen", color = Color.White)
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(blue50)
                                .height(30.dp)
                                .fillMaxWidth()
                                .clickable {
                                    CoroutineScope(Dispatchers.IO).launch {
                                        viewModel.verifyToken(context)
                                        delay(1000)
                                        launchSession.value = true
                                        if(!loadingSession){
                                            if (session){
                                                if (img != null) {
                                                    viewModel.uploadComplaint(user, idcateoria, departamento, details, date, img)
                                                    delay(500)
                                                    launchUpload = true
                                                }else{
                                                    viewModel.uploadComplaint(user, idcateoria ,departamento, details, date, "")
                                                    delay(500)
                                                    launchUpload = true
                                                }
                                            }
                                        }
                                    }
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,

                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(Icons.Default.Publish, contentDescription = "Send Icon", tint = Color.White)
                                Text("Realizar denuncia", color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}






@Preview
@Composable
fun PreviewCreatePostScreen(){
    CreatePostScreen(navController = rememberNavController(), PaddingValues(0.dp), ViewModelMain())
}