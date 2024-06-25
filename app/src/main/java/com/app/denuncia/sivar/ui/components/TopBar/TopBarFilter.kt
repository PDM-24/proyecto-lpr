package com.app.denuncia.sivar.ui.components.TopBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.denuncia.sivar.ui.components.BottonNavBar.ScreenRoute
import com.app.denuncia.sivar.viewmodel.ViewModelMain
import com.denuncia.sivar.ui.theme.IstokWebFamily
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarFilter(
    navController: NavHostController, viewModel: ViewModelMain
){
    var search by viewModel.search
    TopAppBar(
        modifier = Modifier.height(50.dp),
        title = { },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = blue80,
            titleContentColor = blue20,
        ),
        navigationIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 15.dp)
            ) {
                IconButton(
                    onClick = {
                        navController.navigate(ScreenRoute.Home.route)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = blue20,
                        contentDescription = "Back"
                    )
                }
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(blue100)
                        .padding(start = 0.dp, end = 15.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = blue20,
                            modifier = Modifier.size(25.dp)
                        )
                        BasicTextField(
                            value = search,
                            onValueChange = {
                                search = it
                            },
                            singleLine = true,
                            textStyle = TextStyle(
                                color = blue20,
                                fontSize = 16.sp
                            ),
                            cursorBrush = SolidColor(blue20),
                            modifier = Modifier.fillMaxWidth(),
                        ) { innerTextField ->
                            if (search.isEmpty()) {
                                Text(
                                    text = "Buscar denuncia...",
                                    color = blue20,
                                    fontSize = 16.sp,
                                    fontFamily = IstokWebFamily
                                )
                            }
                            innerTextField()
                        }
                    }
                }
            }
        },
        actions = { }
    )
}