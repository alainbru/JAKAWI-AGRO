package com.alpha.jakawiagro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import com.alpha.jakawiagro.screens.welcome.PantallaBienvenidaHakwai
import com.alpha.jakawiagro.screens.auth.LoginForm
import com.alpha.jakawiagro.screens.auth.RegisterScreen
import com.alpha.jakawiagro.screens.auth.ForgotPasswordScreen
import com.alpha.jakawiagro.screens.home.HomeScreen
import com.alpha.jakawiagro.screens.clima.PronosticoHeladasScreen
import com.alpha.jakawiagro.screens.perfil.ProfileScreen
import com.alpha.jakawiagro.screens.settings.SettingsScreen

import com.alpha.jakawiagro.screens.parcelas.ParcelasHomeScreen
import com.alpha.jakawiagro.screens.parcelas.ParcelasDrawScreen
import com.alpha.jakawiagro.screens.parcelas.ParcelasListScreen
import com.alpha.jakawiagro.screens.parcelas.ParcelaDetailScreen
import com.alpha.jakawiagro.screens.parcelas.ParcelaEditScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Routes.WELCOME
) {
    NavHost(navController = navController, startDestination = startDestination) {

        composable(Routes.WELCOME) {
            PantallaBienvenidaHakwai{
                navController.navigate(Routes.LOGIN)
            }
        }

        composable(Routes.LOGIN) {
            LoginForm(
                onLoginSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onGoToRegister = { navController.navigate(Routes.REGISTER) },
                onForgotPassword = { navController.navigate(Routes.FORGOT) } // <-- CLAVE
            )
        }


        composable(Routes.REGISTER) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.WELCOME) { inclusive = true }
                    }
                },
                onBackToLogin = { navController.popBackStack() }
            )
        }
        composable(Routes.FORGOT) {
            ForgotPasswordScreen(
                onBack = { navController.popBackStack() },
                onSendReset = { email ->
                    // TODO: aquí llamas a tu API
                    // Por ahora vuelve al login:
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.HOME) { HomeScreen() }

        /* ---------- PARCELAS ---------- */

        composable(Routes.PARCELAS_HOME) {
            ParcelasHomeScreen(
                onDraw = { navController.navigate(Routes.PARCELAS_DRAW) },
                onList = { navController.navigate(Routes.PARCELAS_LIST) }
            )
        }

        composable(Routes.PARCELAS_DRAW) {
            ParcelasDrawScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.PARCELAS_LIST) {
            ParcelasListScreen(
                onBack = { navController.popBackStack() },
                onOpenDetail = {
                    navController.navigate(Routes.parcelaDetailRoute(it))
                }
            )
        }

        composable(
            route = Routes.PARCELA_DETAIL,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            ParcelaDetailScreen(
                parcelaId = it.arguments?.getString("id") ?: "",
                onEdit = { id ->
                    navController.navigate(Routes.parcelaEditRoute(id))
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.PARCELA_EDIT,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            ParcelaEditScreen(
                parcelaId = it.arguments?.getString("id") ?: "",
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.CLIMA) { PronosticoHeladasScreen() }
        composable(Routes.PERFIL) { ProfileScreen() }
        composable(Routes.SETTINGS) { SettingsScreen() }
    }
}
