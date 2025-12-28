package com.alpha.jakawiagro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alpha.jakawiagro.screens.home.HomeScreen
import com.alpha.jakawiagro.screens.parcelas.ParcelasMapScreen
import com.alpha.jakawiagro.screens.clima.PronosticoHeladasScreen
import com.alpha.jakawiagro.screens.perfil.ProfileScreen
import com.alpha.jakawiagro.screens.settings.SettingsScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen()
        }
        composable(Routes.PARCELAS) {
            ParcelasMapScreen()
        }
        composable(Routes.HELADAS) {
            PronosticoHeladasScreen()
        }
        composable(Routes.PERFIL) {
            ProfileScreen()
        }
        composable(Routes.SETTINGS) {
            SettingsScreen()
        }
    }
}
