package com.app.denuncia.sivar.ui.register.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.denuncia.sivar.R
import com.app.denuncia.sivar.ui.components.BottonNavBar.ScreenRoute
import com.app.denuncia.sivar.viewmodel.ViewModelMain
import com.denuncia.sivar.ui.login.ui.isSmallScreenHeight
import com.denuncia.sivar.ui.theme.IstokWebFamily
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue80
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun RegisterScreen(navController: NavController,  viewModel: ViewModelMain) {

    val nameState = remember { mutableStateOf("") }
    val surnameState = remember { mutableStateOf("") }
    val usernameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val birthdateState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    val isLoading by viewModel.loading.collectAsState()
    val signUpState by viewModel.singUpState.collectAsState()

    val error by viewModel.errorRequest.collectAsState()
    val detailsErrorRequest by viewModel.detailsErrorRequest.collectAsState()

    var showDialog by remember { mutableStateOf(false) }

    val rolState = remember { mutableStateOf("Usuario") }

    val context = LocalContext.current

    var launchSignUp by remember {mutableStateOf(false)}

    if(launchSignUp){
        if(!isLoading){
            if(signUpState){
                Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                navController.navigate(ScreenRoute.Login.route){
                    popUpTo(ScreenRoute.Register.route){inclusive = true}
                }
                launchSignUp = false
            }else{
                if(error){
                    showDialog = true
                    launchSignUp = false
                }else{
                    Toast.makeText(context, "Error al registrarse", Toast.LENGTH_SHORT).show()
                    launchSignUp = false
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.1f),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painter = if(isSystemInDarkTheme()) painterResource(id = R.drawable.logowhite) else painterResource(id = R.drawable.logo),
                        contentDescription = "Logo Denuncia Sivar",
                        modifier = Modifier
                            .size(width = 200.dp, height = 200.dp),
                        contentScale = ContentScale.Fit
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                        .background(color = blue80)
                        .padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Registrarte",
                        fontFamily = IstokWebFamily,
                        fontWeight = FontWeight.Bold,
                        color = blue20,
                        fontSize = 25.sp
                    )
                    if(isSmallScreenHeight()){
                        Spacer(modifier = Modifier.fillMaxSize(0.05f))
                    }
                    else{
                        Spacer(modifier = Modifier.fillMaxSize(0.1f))
                    }
                    RegisterTextField(
                        modifier = Modifier,
                        label = "Nombre: ",
                        placeholder = "Ingrese su nombre",
                        value = nameState.value,
                        onValueChange = {
                                updateString ->
                            nameState.value = updateString
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        keyboardActions = KeyboardActions.Default
                    )
                    Spacer(modifier = Modifier.height(2.dp))

                    RegisterTextField(
                        modifier = Modifier,
                        label = "Apellido: ",
                        placeholder = "Ingrese su apellido",
                        value = surnameState.value,
                        onValueChange = {
                                updateString ->
                            surnameState.value = updateString
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        keyboardActions = KeyboardActions.Default
                    )
                    Spacer(modifier = Modifier.height(2.dp))

                    RegisterTextField(
                        modifier = Modifier,
                        label = "Usuario:",
                        placeholder = "Ingrese su nombre de usuario",
                        value = usernameState.value,
                        onValueChange = {
                                updateString ->
                            usernameState.value = updateString
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        keyboardActions = KeyboardActions.Default
                    )
                    Spacer(modifier = Modifier.height(2.dp))

                    RegisterTextField(
                        modifier = Modifier,
                        label = "Email: ",
                        placeholder = "Ingrese su dirección de correo",
                        value = emailState.value,
                        onValueChange = {
                                updateString ->
                            emailState.value = updateString
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        keyboardActions = KeyboardActions.Default
                    )
                    Spacer(modifier = Modifier.height(2.dp))

                    SelectedDate(
                        modifier = Modifier,
                        label = "Fecha de nacimiento: ",
                        onDateSelected = { birthdateState.value = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        keyboardActions = KeyboardActions.Default
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    PasswordTextField(
                        modifier = Modifier,
                        label = "Contraseña: ",
                        placeholder = "Ingrese su contraseña",
                        value = passwordState.value,
                        onValueChange = { passwordState.value = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Button(
                            onClick = {
                                if (nameState.value.isEmpty() || surnameState.value.isEmpty() || usernameState.value.isEmpty() || emailState.value.isEmpty() || birthdateState.value.isEmpty() || passwordState.value.isEmpty()) {
                                    Toast.makeText(context, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
                                } else if (!emailState.value.endsWith("@gmail.com") && !emailState.value.endsWith("@uca.edu.sv") && !emailState.value.endsWith("@hotmail.com") && !emailState.value.endsWith("@outlook.com")) {
                                    Toast.makeText(context, "Por favor ingrese un correo valido", Toast.LENGTH_SHORT).show()
                                } else {
                                    CoroutineScope(Dispatchers.IO).launch {
                                        viewModel.singUp(usernameState.value,nameState.value,surnameState.value,emailState.value,birthdateState.value,passwordState.value,rolState.value)
                                        delay(1000)
                                        launchSignUp = true
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(250.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = blue20),
                            shape = RoundedCornerShape(20.dp),
                            enabled = !isLoading
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(color = blue100)
                            } else {
                                Text(
                                    text = "Registrarse",
                                    modifier = Modifier,
                                    color = blue100,
                                    fontFamily = IstokWebFamily
                                )
                            }
                        }
                    }
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Error") },
                text = { Text(text = detailsErrorRequest, fontSize = 17.sp) },
                confirmButton = {
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text(text = "OK", color = blue20)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRegisterScreen(){
    RegisterScreen(rememberNavController(), viewModel = ViewModelMain())
}