package com.denuncia.sivar.ui.login.ui

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.denuncia.sivar.R
import com.app.denuncia.sivar.ui.login.ui.PasswordLogin
import com.app.denuncia.sivar.viewmodel.ViewModelMain
import com.denuncia.sivar.ui.theme.DenunciaSivarTheme
import com.denuncia.sivar.ui.theme.IstokWebFamily
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue80

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreen() {

    val usernameState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val viewModel = ViewModelMain()

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
                    Spacer(modifier = Modifier.fillMaxSize(0.1f))
                }
                Text(
                    text = "Iniciar Sesión",
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
                MyTextField(
                    modifier = Modifier,
                    placeholder = "Nombre de usuario:",
                    value = usernameState.value,
                    onValueChange = {
                            updateString ->
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
                    onValueChange = {
                            updateString ->
                        passwordState.value = updateString
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    keyboardActions = KeyboardActions.Default,
                    iconResId = R.drawable.password
                )
                Spacer(modifier = Modifier.height(50.dp))
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
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
                                .clickable { }
                                .align(Alignment.CenterVertically)
                                .width(100.dp),
                            fontFamily = IstokWebFamily,
                            textDecoration = TextDecoration.Underline,
                            color = blue20,

                        )
                    }
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(120.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = blue20),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            text = "Login",
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

