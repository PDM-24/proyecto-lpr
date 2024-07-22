package com.app.denuncia.sivar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.denuncia.sivar.ui.components.BottonNavBar.NavBarComponent
import com.app.denuncia.sivar.ui.components.BottonNavBar.NavBarGraph
import com.app.denuncia.sivar.ui.components.BottonNavBar.NavBarItemList
import com.app.denuncia.sivar.ui.components.BottonNavBar.ScreenRoute
import com.app.denuncia.sivar.ui.components.TopBar.TopAppBarHome
import com.app.denuncia.sivar.ui.components.TopBar.TopBar
import com.app.denuncia.sivar.ui.components.TopBar.TopBarFilter
import com.app.denuncia.sivar.viewmodel.ViewModelMain
import com.denuncia.sivar.ui.theme.DenunciaSivarTheme
import com.denuncia.sivar.ui.theme.blue100

class MainActivity : ComponentActivity() {

    private val viewModel = ViewModelMain()

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
        val navItems = NavBarItemList(viewModel)

        val shouldShowBottomBar = currentRoute != ScreenRoute.Login.route && currentRoute != ScreenRoute.Register.route

        Scaffold(
            modifier = Modifier,
            topBar = { TopBarForRoute(currentRoute, navController, viewModel) },
            bottomBar = {
                if (shouldShowBottomBar) {
                    NavBarComponent(items = navItems, currentRoute = currentRoute) { nav ->
                        navController.navigate(nav.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            }
        ) { innerPadding ->
            NavBarGraph(navController, innerPadding, viewModel)
        }
    }
}

@Composable
fun TopBarForRoute(currentRoute: String?, navController: NavHostController, viewModel: ViewModelMain) {
    when (currentRoute) {
        ScreenRoute.Home.route -> TopAppBarHome(navController)
        ScreenRoute.Historial.route -> TopBar("Historial", R.drawable.historial, navController)
        ScreenRoute.CreatePost.route -> TopBar("Crear Denuncia", R.drawable.ic_edit_image, navController, showBackIcon = true)
        ScreenRoute.Profile.route -> TopBar("Perfil", R.drawable.editprofile, navController)
        ScreenRoute.EditProfile.route -> TopBar("Perfil", R.drawable.editprofile, navController, showBackIcon = true)
        ScreenRoute.Manage.route -> TopBar("Administrar usuarios", R.drawable.manageuser, navController, showBackIcon = false)
        ScreenRoute.Filter.route -> TopBarFilter(navController, viewModel)
        else -> {}
    }
}

