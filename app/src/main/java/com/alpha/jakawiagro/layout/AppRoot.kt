package com.alpha.jakawiagro.layout

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.alpha.jakawiagro.navigation.NavGraph
import com.alpha.jakawiagro.ui.theme.JakawiAgroTheme
import com.alpha.jakawiagro.viewmodel.auth.AuthViewModel
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel

@Composable
fun AppRoot(modifier: Modifier = Modifier) {
    // Tema manual (por defecto usa sistema)
    var useSystemTheme by rememberSaveable { mutableStateOf(true) }
    var darkMode by rememberSaveable { mutableStateOf(false) }

    val navController = rememberNavController()

    // ViewModels (SIN cambiar tus clases)
    val authViewModel: AuthViewModel = viewModel()
    val parcelasViewModel: ParcelasViewModel = viewModel()

    JakawiAgroTheme(
        darkTheme = if (useSystemTheme) androidx.compose.foundation.isSystemInDarkTheme() else darkMode,
        dynamicColor = false
    ) {
        NavGraph(
            navController = navController,
            authViewModel = authViewModel,
            parcelasViewModel = parcelasViewModel,
            // settings theme control
            useSystemTheme = useSystemTheme,
            darkMode = darkMode,
            onToggleUseSystemTheme = { useSystemTheme = it },
            onToggleDarkMode = { darkMode = it }
        )
    }
}




