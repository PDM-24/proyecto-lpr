package com.app.denuncia.sivar.ui.components.TopBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
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
import com.app.denuncia.sivar.R
import com.denuncia.sivar.ui.theme.IstokWebFamily
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue50
import com.denuncia.sivar.ui.theme.blue80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarHome() {
    TopAppBar(
        modifier = Modifier.height(50.dp),
        title = {  },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = blue80,
            titleContentColor = blue20,
        ),
        actions = {
            IconButton(onClick = { /* Acción al hacer clic */ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_edit_image), contentDescription = "timer",
                    modifier = Modifier.size(24.dp)
                )
            }
            Button(
                onClick = { /*TODO*/ },
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
            IconButton(onClick = { /* Acción al hacer clic */ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_search), contentDescription = "timer",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TopBarPreview() {
    TopAppBarHome()
}