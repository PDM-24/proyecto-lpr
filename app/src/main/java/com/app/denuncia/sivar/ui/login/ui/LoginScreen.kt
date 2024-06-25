package com.denuncia.sivar.ui.login.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.denuncia.sivar.R
import com.app.denuncia.sivar.ui.components.BottonNavBar.ScreenRoute
import com.app.denuncia.sivar.ui.login.ui.PasswordLogin
import com.app.denuncia.sivar.viewmodel.ViewModelMain
import com.denuncia.sivar.ui.theme.IstokWebFamily
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue80
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navController: NavController, viewModel: ViewModelMain) {

    val context = LocalContext.current

    val usernameState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    val isLoading by viewModel.loading.collectAsState()
    val loginState by viewModel.loginState.collectAsState()

    val error by viewModel.errorRequest.collectAsState()
    val detailsError by viewModel.detailsErrorRequest.collectAsState()

    val showDialog = remember { mutableStateOf(false) }

    val launchLogin = remember {mutableStateOf(false)}

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
            if(session){
                navController.navigate(ScreenRoute.Home.route) {
                    popUpTo(ScreenRoute.Login.route) { inclusive = true }
                }
            }
        }
    }

    if(launchLogin.value){
        if(!isLoading){
            if (loginState) {
                navController.navigate(ScreenRoute.Home.route) {
                    popUpTo(ScreenRoute.Login.route) { inclusive = true }
                }
                launchLogin.value = false
            }else{
                if (error) {
                    showDialog.value = true
                    launchLogin.value = false
                }else{
                    Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    launchLogin.value = false
                }
            }
        }
    }

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
                    .fillMaxHeight(0.35f),
                contentAlignment = Alignment.Center
            ) {
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
                if (isSmallScreenHeight()) {
                    Spacer(modifier = Modifier.fillMaxSize(0.05f))
                } else {
                    Spacer(modifier = Modifier.fillMaxSize(0.1f))
                }
                Text(
                    text = "Iniciar Sesión",
                    fontFamily = IstokWebFamily,
                    fontWeight = FontWeight.Bold,
                    color = blue20,
                    fontSize = 25.sp
                )
                if (isSmallScreenHeight()) {
                    Spacer(modifier = Modifier.fillMaxSize(0.05f))
                } else {
                    Spacer(modifier = Modifier.fillMaxSize(0.1f))
                }
                MyTextField(
                    modifier = Modifier,
                    placeholder = "Nombre de usuario:",
                    value = usernameState.value,
                    onValueChange = { updateString ->
                        usernameState.value = updateString
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    keyboardActions = KeyboardActions.Default,
                    iconResId = R.drawable.user
                )
                Spacer(modifier = Modifier.height(50.dp))

                PasswordLogin(
                    modifier = Modifier,
                    placeholder = "Contraseña:",
                    value = passwordState.value,
                    onValueChange = { updateString ->
                        passwordState.value = updateString
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    keyboardActions = KeyboardActions.Default,
                    iconResId = R.drawable.password
                )
                Spacer(modifier = Modifier.height(50.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                    ) {
                        Image(
                            painter = painterResource(R.drawable.adduser),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .align(Alignment.CenterVertically),
                            contentScale = ContentScale.Fit
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(
                            text = "Registrarse en Denuncia Sivar",
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(route = ScreenRoute.Register.route)
                                }
                                .align(Alignment.CenterVertically)
                                .width(100.dp),
                            fontFamily = IstokWebFamily,
                            textDecoration = TextDecoration.Underline,
                            color = blue20,
                        )
                    }
                    Button(
                        onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                viewModel.loginUser(usernameState.value, passwordState.value, context)
                                delay(1000)
                                launchLogin.value = true
                            }
                        },
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(120.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = blue20),
                        shape = RoundedCornerShape(20.dp),
                        enabled = !isLoading
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                color = blue100,
                                modifier = Modifier.size(20.dp)
                            )
                        } else {
                            Text(
                                text = "Login",
                                color = blue100,
                                fontFamily = IstokWebFamily,
                            )
                        }
                    }
                }
            }
        }

        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text(text = "Error") },
                text = { Text(text = detailsError, fontSize = 17.sp) },
                confirmButton = {
                    Button(
                        onClick = { showDialog.value = false }
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
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController(), viewModel = ViewModelMain())
}

