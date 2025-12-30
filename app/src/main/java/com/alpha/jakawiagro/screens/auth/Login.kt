package com.alpha.jakawiagro.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.viewmodel.auth.AuthViewModel

@Composable
fun Login(
    authViewModel: AuthViewModel,
    onLoginSuccess: () -> Unit,
    onGoToRegistro: () -> Unit,
    onGoToRecuperar: () -> Unit
) {
    val state = authViewModel.uiState.collectAsState().value

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(state.isLogged) {
        if (state.isLogged) onLoginSuccess()
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Iniciar sesión", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            if (state.error != null) {
                Spacer(Modifier.height(12.dp))
                Text(state.error, color = MaterialTheme.colorScheme.error)
            }

            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { authViewModel.login(email, password) },
                enabled = !state.loading,
                modifier = Modifier.fillMaxWidth()
            ) { Text(if (state.loading) "Cargando..." else "Ingresar") }

            Spacer(Modifier.height(8.dp))
            TextButton(onClick = onGoToRecuperar, modifier = Modifier.fillMaxWidth()) {
                Text("¿Olvidaste tu clave?")
            }

            Spacer(Modifier.height(4.dp))
            TextButton(onClick = onGoToRegistro, modifier = Modifier.fillMaxWidth()) {
                Text("Crear cuenta")
            }
        }
    }
}


