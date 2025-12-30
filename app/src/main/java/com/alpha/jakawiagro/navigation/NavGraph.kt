package com.alpha.jakawiagro.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alpha.jakawiagro.screens.auth.Login
import com.alpha.jakawiagro.screens.auth.Registro
import com.alpha.jakawiagro.screens.auth.RecuperarClave
import com.alpha.jakawiagro.screens.home.HomeScreen
import com.alpha.jakawiagro.screens.parcelas.Detalles
import com.alpha.jakawiagro.screens.parcelas.Dibujar
import com.alpha.jakawiagro.screens.parcelas.Editar
import com.alpha.jakawiagro.screens.parcelas.Inicio
import com.alpha.jakawiagro.screens.parcelas.Lista
import com.alpha.jakawiagro.viewmodel.auth.AuthViewModel
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    parcelasViewModel: ParcelasViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        composable(Routes.SPLASH) {
            val auth = authViewModel.uiState.collectAsState().value

            LaunchedEffect(auth.isLogged) {
                if (auth.isLogged) {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                } else {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            }
        }

        // AUTH
        composable(Routes.LOGIN) {
            Login(
                authViewModel = authViewModel,
                onLoginSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onGoToRegistro = { navController.navigate(Routes.REGISTRO) },
                onGoToRecuperar = { navController.navigate(Routes.RECUPERAR_CLAVE) }
            )
        }

        composable(Routes.REGISTRO) {
            Registro(
                authViewModel = authViewModel,
                onRegisterSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.REGISTRO) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.RECUPERAR_CLAVE) {
            RecuperarClave(
                authViewModel = authViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        // HOME
        composable(Routes.HOME) {
            HomeScreen(
                onGoParcelas = { navController.navigate(Routes.PARCELAS_INICIO) },
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                }
            )
        }

        // PARCELAS
        composable(Routes.PARCELAS_INICIO) {
            Inicio(
                onGoLista = { navController.navigate(Routes.PARCELAS_LISTA) },
                onGoDibujar = { navController.navigate(Routes.PARCELAS_DIBUJAR) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.PARCELAS_LISTA) {
            Lista(
                authViewModel = authViewModel,
                parcelasViewModel = parcelasViewModel,
                onGoDetalles = { navController.navigate(Routes.PARCELAS_DETALLES) },
                onGoEditar = { navController.navigate(Routes.PARCELAS_EDITAR) },
                onAdd = { navController.navigate(Routes.PARCELAS_DIBUJAR) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.PARCELAS_DIBUJAR) {
            Dibujar(
                authViewModel = authViewModel,
                parcelasViewModel = parcelasViewModel,
                onSaved = { navController.popBackStack() },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.PARCELAS_DETALLES) {
            Detalles(onBack = { navController.popBackStack() })
        }

        composable(Routes.PARCELAS_EDITAR) {
            Editar(
                parcelasViewModel = parcelasViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

