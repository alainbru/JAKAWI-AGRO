package com.alpha.jakawiagro.screens

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.material3.VerticalDragHandleDefaults.shapes
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
import com.alpha.jakawiagro.ui.theme.shapes

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreviewDark() {
    JakawiAgroTheme {
        RegisterScreen()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen() {
    var clicked by remember { mutableStateOf(false) }

    Scaffold { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.icono),
                contentDescription = "Logo Jakawi Agro",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Nombre")
                },
                leadingIcon = {  Icon(Icons.Default.Person,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary)  },
                modifier = Modifier.fillMaxWidth(),
                shape = shapes.medium

            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Correo Electrónico") },
                leadingIcon = { Icon(Icons.Default.Email,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary) },
                modifier = Modifier.fillMaxWidth(),
                shape = shapes.medium

            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Contraseña") },
                leadingIcon = { Icon(Icons.Default.Lock,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = shapes.medium

            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Confirmar Contraseña") },
                leadingIcon = { Icon(Icons.Default.Lock,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = shapes.medium

            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { clicked = !clicked },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(48.dp)
                    .animateContentSize(),
                    shape = shapes.large,

                        colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )            ) {
                if (clicked) {
                    Text("¡Registrado!", color = Color.Black)
                } else {
                    Text("REGISTRARSE", color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "¿YA TE REGISTRASTE?",
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    // Navegación a pantalla login
                }
            )
        }
    }
}

