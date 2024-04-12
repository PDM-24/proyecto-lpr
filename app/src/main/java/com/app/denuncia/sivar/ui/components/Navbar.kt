package com.app.denuncia.sivar.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.denuncia.sivar.R
import com.denuncia.sivar.ui.theme.IstokWebFamily
import com.denuncia.sivar.ui.theme.blue100
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue50
import com.denuncia.sivar.ui.theme.blue80
import com.denuncia.sivar.ui.theme.gray

data class BottomNavigationItem(
    val title: String,
    val selectedIconResId: Int,
    val hasNews: Boolean,
    val badgetCount: Int? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Navbar(){
    val items = listOf(
        BottomNavigationItem(
            title = "Inicio",
            selectedIconResId = R.drawable.ic_home,
            hasNews = false,
            badgetCount = 12
        ),
        BottomNavigationItem(
            title = "Historial",
            selectedIconResId = R.drawable.historial,
            hasNews = false,
            badgetCount = null
        ),
        BottomNavigationItem(
            title = "Perfil",
            selectedIconResId = R.drawable.editprofile,
            hasNews = false,
            badgetCount = null
        ),
        BottomNavigationItem(
            title = "Admin",
            selectedIconResId = R.drawable.manageuser,
            hasNews = false,
            badgetCount = null
        ),
        BottomNavigationItem(
            title = "Salir",
            selectedIconResId = R.drawable.ic_exit,
            hasNews = false,
            badgetCount = null
        ),

    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    Scaffold (
        modifier = Modifier,
        containerColor = blue100,
        bottomBar = {
            NavigationBar(
                modifier = Modifier,
                containerColor = blue80,
            ) {
                items.forEachIndexed{ index, item ->
                    NavigationBarItem(
                        modifier = Modifier,
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            // navController.navigate(item.title)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = blue20,
                            selectedTextColor = blue20,
                            unselectedIconColor = blue20,
                            indicatorColor = blue50,
                            unselectedTextColor = blue20,
                        ),
                        label = {
                                Text(
                                    text = item.title,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = IstokWebFamily
                                )
                        },
                        alwaysShowLabel = false,
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (item.badgetCount != null){
                                        Badge(
                                            contentColor = Color.White,
                                            containerColor = blue50
                                        ){
                                            Text(text = item.badgetCount.toString())
                                        }
                                    } else if(item.hasNews){
                                        Badge()
                                    }
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id =item.selectedIconResId),
                                    contentDescription = item.title,
                                    modifier = Modifier.
                                    size(24.dp),
                                )
                            }
                        }
                    )
                }
            }
        }
    ){

    }
}
