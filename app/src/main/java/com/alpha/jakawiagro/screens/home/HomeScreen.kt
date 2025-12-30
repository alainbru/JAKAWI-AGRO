package com.alpha.jakawiagro.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.viewmodel.auth.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    authViewModel: AuthViewModel,
    onGoParcelas: () -> Unit,
    onGoPerfil: () -> Unit,
    onGoSettings: () -> Unit,
    onLogout: () -> Unit
) {
    val state by authViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jakawi Agro") },
                actions = {
                    IconButton(onClick = onLogout) {
                        Icon(Icons.Default.Logout, contentDescription = "Salir")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Usuario: ${state.userId ?: "-"}")

            Button(onClick = onGoParcelas, modifier = Modifier.fillMaxWidth().height(48.dp)) {
                Text("Parcelas")
            }

            Button(onClick = onGoPerfil, modifier = Modifier.fillMaxWidth().height(48.dp)) {
                Text("Perfil")
            }

            Button(onClick = onGoSettings, modifier = Modifier.fillMaxWidth().height(48.dp)) {
                Text("Settings (Tema)")
            }
        }
    }
}
