package com.alpha.jakawiagro.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.R
import com.alpha.jakawiagro.viewmodel.auth.AuthViewModel

@Composable
fun Registro(
    authViewModel: AuthViewModel,
    onGoLogin: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    val state by authViewModel.uiState.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var pass2 by remember { mutableStateOf("") }

    val canSubmit = nombre.isNotBlank() && email.isNotBlank() && pass.length >= 6 && pass == pass2 && !state.loading

    LaunchedEffect(state.isLogged) {
        if (state.isLogged) onRegisterSuccess()
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.icono),
                contentDescription = "Logo",
                modifier = Modifier.size(110.dp)
            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                leadingIcon = { Icon(Icons.Default.Person, null) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it.trim() },
                label = { Text("Correo") },
                leadingIcon = { Icon(Icons.Default.Email, null) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = pass,
                onValueChange = { pass = it },
                label = { Text("Contraseña (mín. 6)") },
                leadingIcon = { Icon(Icons.Default.Lock, null) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = pass2,
                onValueChange = { pass2 = it },
                label = { Text("Confirmar contraseña") },
                leadingIcon = { Icon(Icons.Default.Lock, null) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            if (state.error != null) {
                Spacer(Modifier.height(10.dp))
                Text(state.error ?: "", color = MaterialTheme.colorScheme.error)
            }

            Spacer(Modifier.height(18.dp))

            Button(
                onClick = { authViewModel.register(nombre, email, pass) },
                enabled = canSubmit,
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                if (state.loading) CircularProgressIndicator(strokeWidth = 2.dp, modifier = Modifier.size(18.dp))
                else Text("REGISTRARSE")
            }

            Spacer(Modifier.height(18.dp))

            Text(
                text = "¿YA TE REGISTRASTE?",
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onGoLogin() }
            )
        }
    }
}


