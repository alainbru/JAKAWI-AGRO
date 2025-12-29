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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alpha.jakawiagro.drawer.AppDrawer
import com.alpha.jakawiagro.navigation.NavGraph
import com.alpha.jakawiagro.navigation.Routes
import com.alpha.jakawiagro.screens.temporal.MainTopAppBar
import com.alpha.jakawiagro.di.AppModule
import com.alpha.jakawiagro.viewmodel.auth.AuthViewModel
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRoot() {
    val context = LocalContext.current
    val navController = rememberNavController()

    // ✅ ViewModels se crean una sola vez aquí (no en pantallas)
    val authViewModel = remember {
        AuthViewModel(AppModule.authRepository(context))
    }

    val parcelasViewModel = remember {
        ParcelasViewModel(AppModule.parcelaRepository(context))
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    // ✅ Rutas donde NO queremos Drawer (mapa y auth)
    val isAuthRoute = currentRoute in listOf(
        Routes.WELCOME,
        Routes.LOGIN,
        Routes.REGISTER
        // agrega aquí FORGOT/RESET si las tienes
        // Routes.FORGOT, Routes.RESET
    )

    val isMapRoute = currentRoute in listOf(
        Routes.PARCELAS_DRAW,
        Routes.PARCELA_EDIT
    )

    val drawerGesturesEnabled = !isAuthRoute && !isMapRoute

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

    // ✅ Si es Auth route, no mostramos Drawer ni TopBar
    if (isAuthRoute) {
        Surface {
            NavGraph(
                navController = navController,
                authViewModel = authViewModel,
                parcelasViewModel = parcelasViewModel
            )
        }
        return
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerGesturesEnabled,
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
        ) { innerPadding ->
            Surface(modifier = Modifier.padding(innerPadding)) {
                NavGraph(
                    navController = navController,
                    authViewModel = authViewModel,
                    parcelasViewModel = parcelasViewModel
                )
            }
        }
    }
}

