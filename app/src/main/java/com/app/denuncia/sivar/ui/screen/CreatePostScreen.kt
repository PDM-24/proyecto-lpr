package com.app.denuncia.sivar.ui.screen

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.denuncia.sivar.R
import com.app.denuncia.sivar.model.Categoria
import com.app.denuncia.sivar.model.CategoriaList
import com.app.denuncia.sivar.model.DepartamentList
import com.app.denuncia.sivar.model.Departamentos
import com.app.denuncia.sivar.ui.components.TopBar.TopBar
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue50
import com.denuncia.sivar.ui.theme.blue80
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Publish
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.app.denuncia.sivar.ui.components.FilterComp.CustomDropdownDepartment
import com.app.denuncia.sivar.ui.components.FilterComp.CustomDropdownKind

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePostScreen(navController: NavHostController, innerPadding: PaddingValues)
{
    var textAreaContent by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var selectedDepartment by remember { mutableStateOf("Seleccione el departamento") }
    var selectedKind by remember { mutableStateOf("Seleccione una categoria") }

    val context = LocalContext.current

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            selectedImageUri = uri
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        TopBar("Crear Denuncia", R.drawable.ic_edit_image, navController, showBackIcon = true)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(blue100),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                OutlinedCard(
                    colors = CardDefaults.cardColors(
                        containerColor = blue100,
                    ),
                    border = BorderStroke(2.dp, blue50),
                    shape = RoundedCornerShape(20.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.photoexample),
                                contentDescription = "profilePhoto",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(RoundedCornerShape(50.dp))
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "John Doe",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = blue20,
                            )
                        }
                        CustomDropdownDepartment(
                            options = DepartamentList,
                            selectedOption = selectedDepartment,
                            onOptionSelected = { selectedDepartment = it.nombre }
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                        CustomDropdownKind(
                            options = CategoriaList,
                            selectedOption = selectedKind,
                            onOptionSelected = { selectedKind = it.nombre }
                        )
                        OutlinedTextField(
                            shape = RoundedCornerShape(12.dp),
                            value = textAreaContent,
                            onValueChange = { textAreaContent = it },
                            label = { Text(text = "Descripción de la denuncia", color = blue20) },
                            colors = OutlinedTextFieldDefaults.colors(
                                cursorColor = blue50,
                                focusedBorderColor = blue50,
                                unfocusedBorderColor = blue50,
                                disabledBorderColor = blue50,
                                errorBorderColor = Color.Red,
                                focusedLabelColor = blue80,
                                unfocusedLabelColor = blue80
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                            , // Ajusta la altura según sea necesario
                            maxLines = 3 // Permitir hasta 5 líneas de texto
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                        if (selectedImageUri != null) {
                            Image(
                                painter = rememberAsyncImagePainter(selectedImageUri),
                                contentDescription = "Selected Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(20.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Button(
                            onClick = { imagePickerLauncher.launch("image/*") },
                            colors = ButtonDefaults.buttonColors(containerColor = blue80)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Image, contentDescription = "senIcon", tint = Color.White)
                                Text("Agregar imagen", color = Color.White)
                            }
                        }
                        Spacer(modifier = Modifier.height(7.dp))
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                showToast(context, "Denuncia enviada")
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = blue80)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(Icons.Default.Publish, contentDescription = "Send Icon", tint = Color.White)
                                Text("Realizar Denuncia", color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}





@Preview
@Composable
fun PreviewCreatePostScreen(){
    CreatePostScreen(navController = rememberNavController(), PaddingValues(0.dp))
}