package com.alpha.jakawiagro.screens

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.R

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}

@Composable
fun LoginScreen() {
    // Estado para controlar qué vista mostrar: true = recuperación de contraseña, false = login
    var showRecovery by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEDE7D5))
            .padding(horizontal = 32.dp),
    ) {
        Crossfade(targetState = showRecovery) { isRecovery ->
            if (isRecovery) {
                // Pantalla de recuperación de contraseña
                PasswordRecovery(onBack = { showRecovery = false })
            } else {
                // Pantalla principal de login
                LoginForm(onForgotPassword = { showRecovery = true })
            }
        }
    }
}

@Composable
fun LoginForm(onForgotPassword: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.icono),
            contentDescription = "Logo Jakawi Agro",
            modifier = Modifier
                .size(240.dp)
                .padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Usuario") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Contraseña") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* Acción de login */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("INICIAR", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "REGISTRARSE",
                color = Color.Black,
                modifier = Modifier.clickable { /* Acción de registro */ }
            )
            Text(
                text = "OLVIDASTE LA CONTRASEÑA",
                color = Color.Black,
                modifier = Modifier.clickable { onForgotPassword() }
            )
        }
    }
}

@Composable
fun PasswordRecovery(onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Recuperar Contraseña",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* Acción para enviar correo recuperación */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("ENVIAR", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "VOLVER",
            color = Color.Black,
            modifier = Modifier.clickable { onBack() }
        )
    }
}

