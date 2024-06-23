package com.app.denuncia.sivar.ui.components.TopBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.denuncia.sivar.ui.components.BottonNavBar.ScreenRoute
import com.denuncia.sivar.ui.theme.IstokWebFamily
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    icon: Int,
    navController: NavHostController,
    showBackIcon: Boolean = false,
) {
    TopAppBar(
        modifier = Modifier.height(50.dp),
        title = { },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = blue80,
            titleContentColor = blue20,
        ),
        navigationIcon = {
            if (showBackIcon) {
                IconButton(onClick = {
                    navController.navigate(ScreenRoute.Home.route) // NAVIGATION TO HOME
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = blue20,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    color = blue20,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontFamily = IstokWebFamily,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.size(5.dp))
                Image(painter = painterResource(id = icon), contentDescription = "history icon", modifier = Modifier.size(24.dp))
            }
        }
    )
}

