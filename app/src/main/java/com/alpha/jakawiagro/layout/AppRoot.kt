package com.alpha.jakawiagro.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.alpha.jakawiagro.navigation.NavGraph
import com.alpha.jakawiagro.viewmodel.auth.AuthViewModel
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel

@Composable
fun AppRoot() {
    val navController = rememberNavController()

    val authViewModel = remember { AuthViewModel() }
    val parcelasViewModel = remember{ ParcelasViewModel() }

    NavGraph(
        navController = navController,
        authViewModel = authViewModel,
        parcelasViewModel = parcelasViewModel
    )
}



