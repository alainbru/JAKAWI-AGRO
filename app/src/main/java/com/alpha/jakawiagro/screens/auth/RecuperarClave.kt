package com.alpha.jakawiagro.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.viewmodel.auth.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecuperarClave(
    authViewModel: AuthViewModel,
    onBack: () -> Unit
) {
    val state = authViewModel.uiState.collectAsState().value
    var email by remember { mutableStateOf("") }
    var enviado by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recuperar clave") },
                navigationIcon = { IconButton(onClick = onBack) { Text("←") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Te enviaremos un enlace a tu correo.")
            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo") },
                modifier = Modifier.fillMaxWidth()
            )

            if (state.error != null) {
                Spacer(Modifier.height(12.dp))
                Text(state.error, color = MaterialTheme.colorScheme.error)
            }
            if (enviado) {
                Spacer(Modifier.height(12.dp))
                Text("Listo. Revisa tu correo.", color = MaterialTheme.colorScheme.primary)
            }

            Spacer(Modifier.height(16.dp))
            Button(
                enabled = !state.loading,
                onClick = {
                    authViewModel.resetPassword(email)
                    enviado = true
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text(if (state.loading) "Enviando..." else "Enviar enlace") }
        }
    }
}

