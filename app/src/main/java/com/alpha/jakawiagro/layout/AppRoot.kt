package com.alpha.jakawiagro.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alpha.jakawiagro.drawer.AppDrawer
import com.alpha.jakawiagro.navigation.NavGraph
import com.alpha.jakawiagro.navigation.Routes
import com.alpha.jakawiagro.screens.MainTopAppBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRoot() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val title = when (currentRoute) {
        Routes.HOME -> "Inicio"
        Routes.PARCELAS -> "Parcelas"
        Routes.HELADAS -> "Pronóstico de Heladas"
        Routes.PERFIL -> "Perfil"
        Routes.SETTINGS -> "Configuración"
        else -> "Jakawi Agro"
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                currentRoute = currentRoute,
                onItemClick = { route ->
                    scope.launch {
                        drawerState.close()
                        navController.navigate(route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                        }
                    }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                MainTopAppBar(
                    title = title,
                    onMenuClick = { scope.launch { drawerState.open() } },
                    onProfileClick = {
                        navController.navigate(Routes.PERFIL) { launchSingleTop = true }
                    }
                )
            }
        ) { padding ->
            Surface(modifier = Modifier.padding(padding)) {
                NavGraph(navController = navController)
            }
        }
    }
}
