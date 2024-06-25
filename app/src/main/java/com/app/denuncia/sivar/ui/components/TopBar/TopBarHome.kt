package com.app.denuncia.sivar.ui.components.TopBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.denuncia.sivar.R
import com.app.denuncia.sivar.ui.components.BottonNavBar.ScreenRoute
import com.denuncia.sivar.ui.theme.IstokWebFamily
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarHome(navController: NavHostController) {

        TopAppBar(
            modifier = Modifier.height(50.dp),
            title = {  },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = blue80,
                titleContentColor = blue20,
            ),
            actions = {
                Image(
                    painter = painterResource(id = R.drawable.logowhite),
                    contentDescription = "logo",
                )
                Button(
                    onClick = { navController.navigate(ScreenRoute.CreatePost.route) },
                    modifier = Modifier
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = blue100
                    )
                ) {
                    Text(
                        text = "¿Desea realizar una denuncia?",
                        color = blue20,
                        fontStyle = FontStyle.Normal,
                        fontFamily = IstokWebFamily)
                }
                IconButton(
                    onClick = {
                        navController.navigate(ScreenRoute.Filter.route)
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(27.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = "searchIcon",
                        tint = blue20
                    )
                }
            }
        )

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TopBarPreview() {
    TopAppBarHome(navController = rememberNavController())
}