package com.app.denuncia.sivar.ui.components.TopBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.denuncia.sivar.R
import com.denuncia.sivar.ui.theme.IstokWebFamily
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(Title: String, Icon: Int) {
    TopAppBar(
        modifier = Modifier.height(50.dp),
        title = {  },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = blue80,
            titleContentColor = blue20,
        ),
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "$Title",
                    color = blue20,
                    fontStyle = FontStyle.Normal,
                    fontFamily = IstokWebFamily,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.size(5.dp))
                Image(painter = painterResource(id = Icon), contentDescription = "ic_historial", modifier = Modifier.size(24.dp))
            }
        }
    )
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun TopBar2Preview() {
    TopBar("Tu historial", R.drawable.historial)
}








