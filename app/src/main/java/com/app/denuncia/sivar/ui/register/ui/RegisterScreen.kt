package com.app.denuncia.sivar.ui.register.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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


@Composable
fun RegisterScreen(navController: NavController,  viewModel: ViewModelMain) {

    val nameState = remember { mutableStateOf("") }
    val surnameState = remember { mutableStateOf("") }
    val usernameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = blue100)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f),
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.logowhite),
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
                if(isSmallScreenHeight()){
                    Spacer(modifier = Modifier.fillMaxSize(0.05f))
                }
                else{
                    Spacer(modifier = Modifier.fillMaxSize(0.05f))
                }
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    keyboardActions = KeyboardActions.Default,
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
                            navController.navigate(route = ScreenRoute.Login.route)
                        },
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(250.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = blue20),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            text = "Registrarse",
                            modifier = Modifier,
                            color = blue100,
                            fontFamily = IstokWebFamily,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRegisterScreen(){
    RegisterScreen(rememberNavController(), viewModel = ViewModelMain())
}