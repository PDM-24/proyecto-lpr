package com.app.denuncia.sivar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.denuncia.sivar.ui.components.BottonNavBar.NavBarComponent
import com.app.denuncia.sivar.ui.components.BottonNavBar.NavBarGraph
import com.app.denuncia.sivar.ui.components.BottonNavBar.NavBarItemList
import com.denuncia.sivar.ui.login.ui.LoginScreen
import com.denuncia.sivar.ui.theme.DenunciaSivarTheme
import com.denuncia.sivar.ui.theme.blue100

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DenunciaSivarTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute : String? = navBackStackEntry?.destination?.route
                val navItems = NavBarItemList()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold (
                        modifier = Modifier,
                        containerColor = blue100,
                        bottomBar = {
                            NavBarComponent(
                                items = navItems,
                                currentRoute = currentRoute) {
                                    currentNavigationItem ->
                                    navController.navigate(currentNavigationItem.route){
                                    navController.graph.startDestinationRoute?.let{
                                            startDestinationRoute ->
                                        popUpTo(startDestinationRoute){
                                            saveState = true
                                        }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    ){
                        innerPadding -> NavBarGraph(
                        navController = navController,
                        innerPadding = innerPadding)
                    }
                }
            }
        }
    }
}

