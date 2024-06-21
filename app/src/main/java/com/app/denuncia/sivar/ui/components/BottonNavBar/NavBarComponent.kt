package com.app.denuncia.sivar.ui.components.BottonNavBar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PostAdd
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.denuncia.sivar.ui.theme.IstokWebFamily
import com.denuncia.sivar.ui.theme.blue20
import com.denuncia.sivar.ui.theme.blue50
import com.denuncia.sivar.ui.theme.blue80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBarComponent(
    items: List<NavBarItem>,
    currentRoute: String?,
    onClick: (NavBarItem) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .height(57.dp),
        containerColor = blue80,
    ) {
        items.forEach { navBarItem ->
            NavigationBarItem(
                modifier = Modifier,
                selected = currentRoute == navBarItem.route,
                onClick = { onClick(navBarItem) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = blue20,
                    selectedTextColor = blue20,
                    unselectedIconColor = blue20,
                    indicatorColor = blue50,
                    unselectedTextColor = blue20,
                ),
                icon = {
                    BadgedBox(
                        badge = {
                            if (navBarItem.badgeCount != null) {
                                Badge(
                                    contentColor = Color.White,
                                    containerColor = blue50
                                ) {
                                    Text(text = navBarItem.badgeCount.toString())
                                }
                            } else if (navBarItem.hasNews) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (currentRoute == navBarItem.route) navBarItem.filledIcon else navBarItem.outlinedIcon,
                            contentDescription = navBarItem.title,
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
            )
        }
    }
}

