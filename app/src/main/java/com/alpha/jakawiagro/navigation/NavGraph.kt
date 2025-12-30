package com.alpha.jakawiagro.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.alpha.jakawiagro.layout.MainShell
import com.alpha.jakawiagro.screens.auth.Login
import com.alpha.jakawiagro.screens.auth.RecuperarClave
import com.alpha.jakawiagro.screens.auth.Registro
import com.alpha.jakawiagro.screens.home.HomeScreen
import com.alpha.jakawiagro.screens.parcelas.*
import com.alpha.jakawiagro.screens.settings.SettingsScreen
import com.alpha.jakawiagro.screens.welcome.PantallaBienvenidaHakwai
import com.alpha.jakawiagro.viewmodel.auth.AuthViewModel
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel
import com.alpha.jakawiagro.screens.perfil.ProfileScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    parcelasViewModel: ParcelasViewModel,
    useSystemTheme: Boolean,
    darkMode: Boolean,
    onToggleUseSystemTheme: (Boolean) -> Unit,
    onToggleDarkMode: (Boolean) -> Unit,
) {
    val authState by authViewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        // ---------------- SPLASH ----------------
        composable(Routes.SPLASH) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

            LaunchedEffect(Unit) {
                authViewModel.checkSession()
            }

            LaunchedEffect(authState.isLogged) {
                if (authState.isLogged) {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                } else {
                    navController.navigate(Routes.WELCOME) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            }
        }

        // ---------------- WELCOME/AUTH ----------------
        composable(Routes.WELCOME) {
            PantallaBienvenidaHakwai(
                onContinue = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.WELCOME) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.LOGIN) {
            Login(
                authViewModel = authViewModel,
                onGoRegister = { navController.navigate(Routes.REGISTRO) },
                onForgotPassword = { navController.navigate(Routes.RECUPERAR_CLAVE) },
                onLoginSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.REGISTRO) {
            Registro(
                authViewModel = authViewModel,
                onGoLogin = { navController.popBackStack() },
                onRegisterSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.REGISTRO) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.RECUPERAR_CLAVE) {
            RecuperarClave(
                authViewModel = authViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        // ---------------- MAIN (con Drawer + TopBar) ----------------

        composable(Routes.HOME) {
            MainShell(
                navController = navController,
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                }
            ) { modifier ->
                Box(modifier = modifier.fillMaxSize()) {
                    HomeScreen(
                        onGoCultivos = { navController.navigate(Routes.CULTIVOS) },
                        onGoParcelas = { navController.navigate(Routes.PARCELAS_INICIO) },
                        onGoCalendario = { navController.navigate(Routes.CALENDARIO) },
                        onGoClima = { navController.navigate(Routes.CLIMA) }
                    )
                }
            }
        }

        // CULTIVOS (placeholder por ahora)
        composable(Routes.CULTIVOS) {
            MainShell(
                navController = navController,
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                }
            ) { modifier ->
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Cultivos (próximamente)")
                }
            }
        }

        // CALENDARIO (placeholder por ahora)
        composable(Routes.CALENDARIO) {
            MainShell(
                navController = navController,
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                }
            ) { modifier ->
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Calendario (próximamente)")
                }
            }
        }

        // CLIMA (placeholder por ahora)
        composable(Routes.CLIMA) {
            MainShell(
                navController = navController,
                onLogout = { /* tu logout igual */ }
            ) { modifier ->
                Box(modifier = modifier.fillMaxSize()) {
                    com.alpha.jakawiagro.screens.clima.ClimaScreen()
                }
            }
        }

        // PARCELAS INICIO (si tu screen NO tiene topbar propia, queda perfecto)
        composable(Routes.PARCELAS_INICIO) {
            val userId = authState.userId ?: ""
            MainShell(
                navController = navController,
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                }
            ) { modifier ->
                Box(modifier = modifier.fillMaxSize()) {
                    ParcelasInicioScreen(
                        onGoLista = { navController.navigate(Routes.PARCELAS_LISTA) },
                        onGoDibujar = { navController.navigate(Routes.PARCELAS_DIBUJAR) },
                        onBack = { navController.popBackStack() },
                        userId = userId,
                        parcelasViewModel = parcelasViewModel
                    )
                }
            }
        }

        // --------- PARCELAS (pantallas secundarias, por ahora SIN MainShell para evitar doble topbar) ---------

        composable(Routes.PARCELAS_LISTA) {
            val userId = authState.userId ?: ""
            ParcelasListaScreen(
                userId = userId,
                parcelasViewModel = parcelasViewModel,
                onGoDetalles = { id -> navController.navigate(Routes.detalles(id)) },
                onGoEditar = { id -> navController.navigate(Routes.editar(id)) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.PARCELAS_DIBUJAR) {
            val userId = authState.userId ?: ""
            ParcelasDibujarScreen(
                userId = userId,
                parcelasViewModel = parcelasViewModel,
                onBack = { navController.popBackStack() },
                onSaved = {
                    navController.navigate(Routes.PARCELAS_LISTA) {
                        popUpTo(Routes.PARCELAS_INICIO) { inclusive = false }
                    }
                }
            )
        }

        composable(
            route = Routes.PARCELAS_DETALLES,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            ParcelasDetallesScreen(
                parcelaId = id,
                parcelasViewModel = parcelasViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.PARCELAS_EDITAR,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            ParcelasEditarScreen(
                parcelaId = id,
                parcelasViewModel = parcelasViewModel,
                onBack = { navController.popBackStack() },
                onSaved = { navController.popBackStack() }
            )
        }

        // SETTINGS (tema claro/oscuro)
        composable(Routes.SETTINGS) {
            SettingsScreen(
                useSystemTheme = useSystemTheme,
                darkMode = darkMode,
                onToggleUseSystemTheme = onToggleUseSystemTheme,
                onToggleDarkMode = onToggleDarkMode,
                onBack = { navController.popBackStack() }
            )
        }


        // PERFIL (real) - con Drawer + TopBar (MainShell)
        composable(Routes.PERFIL) {
            MainShell(
                navController = navController,
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                }
            ) { modifier ->
                Box(modifier = modifier.fillMaxSize()) {
                    ProfileScreen(
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }

    }
}


