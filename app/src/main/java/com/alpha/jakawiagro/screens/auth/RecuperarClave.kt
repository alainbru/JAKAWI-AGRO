package com.alpha.jakawiagro.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.viewmodel.auth.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecuperarClave(
    authViewModel: AuthViewModel,
    onBack: () -> Unit
) {
    val state by authViewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("") }
    var enviado by remember { mutableStateOf(false) }
    var submitted by remember { mutableStateOf(false) }

    // cuando termina loading después de enviar:
    LaunchedEffect(state.loading, state.error) {
        if (submitted && !state.loading) {
            if (state.error == null && email.isNotBlank()) enviado = true
            submitted = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recuperar clave") },
                navigationIcon = {
                    TextButton(onClick = onBack) { Text("Atrás") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it.trim()
                    enviado = false
                },
                label = { Text("Correo") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = {
                    if (email.isNotBlank()) {
                        enviado = false
                        submitted = true
                        authViewModel.resetPassword(email)
                    }
                },
                enabled = email.isNotBlank() && !state.loading,
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                if (state.loading) CircularProgressIndicator(strokeWidth = 2.dp, modifier = Modifier.size(18.dp))
                else Text("ENVIAR")
            }

            if (state.error != null) {
                Spacer(Modifier.height(10.dp))
                Text(state.error ?: "", color = MaterialTheme.colorScheme.error)
            }

            if (enviado) {
                Spacer(Modifier.height(10.dp))
                Text("Listo ✅ Revisa tu correo.", color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}


