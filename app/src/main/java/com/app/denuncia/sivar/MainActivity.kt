package com.app.denuncia.sivar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.denuncia.sivar.ui.components.BottonNavBar.NavBarComponent
import com.app.denuncia.sivar.ui.components.BottonNavBar.NavBarGraph
import com.app.denuncia.sivar.ui.components.BottonNavBar.NavBarItemList
import com.app.denuncia.sivar.ui.components.TopBar.TopAppBarHome
import com.denuncia.sivar.ui.theme.DenunciaSivarTheme
import com.denuncia.sivar.ui.theme.blue100

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DenunciaSivarTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavBar()
                }
            }
        }
    }

    @Preview
    @Composable
    fun NavBar() {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute: String? = navBackStackEntry?.destination?.route
        val navItems = NavBarItemList()
        Scaffold(
            modifier = Modifier,
            containerColor = blue100,
            bottomBar = {
                NavBarComponent(items = navItems, currentRoute = currentRoute) {
                    nav -> navController.navigate(nav.route) {
                        navController.graph.startDestinationRoute?.let {
                            route -> popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        ) { innerPadding ->
            NavBarGraph(
                navController = navController,
                innerPadding = innerPadding
            )
        }
    }
}


