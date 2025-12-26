package com.alpha.jakawiagro.screens

import android.content.res.Configuration
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.R
import com.alpha.jakawiagro.ui.theme.JakawiAgroTheme
import androidx.compose.material3.TextFieldDefaults


@Composable
fun LoginForm(onForgotPassword: () -> Unit) {
    val colors = MaterialTheme.colorScheme
    val shapes = MaterialTheme.shapes

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(colors.background)
                .padding(horizontal = 32.dp),
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
                label = { Text("Usuario", color = colors.onSurface) },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = colors.primary) },
                shape = shapes.medium,

                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Contraseña", color = colors.onSurface) },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = colors.primary) },
                visualTransformation = PasswordVisualTransformation(),
                shape = shapes.medium,

                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* Acción de login */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colors.primary),
                shape = shapes.medium
            ) {
                Text("INICIAR", color = colors.onPrimary)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "REGISTRARSE",
                    color = colors.secondary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { /* Acción de registro */ }
                )
                Text(
                    text = "OLVIDASTE LA CONTRASEÑA",
                    color = colors.secondary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onForgotPassword() }
                )
            }
        }
    }
}



@Preview(uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewLoginFormDark() {
    JakawiAgroTheme {
        LoginForm(onForgotPassword = {})
    }
}
