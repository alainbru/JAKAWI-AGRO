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
import com.alpha.jakawiagro.screens.temporal.MainTopAppBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRoot() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    // ✅ En estas pantallas NO permitimos gesto lateral (para que el mapa sea fluido)
    val drawerGesturesEnabled = currentRoute !in listOf(
        Routes.PARCELAS_DRAW,
        Routes.PARCELA_EDIT
    )

    val title = when (currentRoute) {
        Routes.HOME -> "Inicio"
        Routes.PARCELAS_HOME -> "Parcelas"
        Routes.PARCELAS_DRAW -> "Dibujar parcela"
        Routes.PARCELAS_LIST -> "Mis parcelas"
        Routes.CLIMA -> "Pronóstico de Heladas"
        Routes.PERFIL -> "Perfil"
        Routes.SETTINGS -> "Configuración"
        else -> "Jakawi Agro"
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerGesturesEnabled, // ✅ clave
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
                    onMenuClick = { scope.launch { drawerState.open() } }, // ✅ solo botón abre drawer
                    onProfileClick = { navController.navigate(Routes.PERFIL) { launchSingleTop = true } }
                )
            }
        ) { innerPadding ->
            Surface(modifier = Modifier.padding(innerPadding)) {
                // ✅ Importante: el padding se aplica aquí, así el mapa NO se va debajo del topbar
                NavGraph(navController = navController)
            }
        }
    }
}
