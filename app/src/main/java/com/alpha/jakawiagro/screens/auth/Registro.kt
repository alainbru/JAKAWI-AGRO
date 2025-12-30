package com.alpha.jakawiagro.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.R
import com.alpha.jakawiagro.viewmodel.auth.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registro(
    authViewModel: AuthViewModel,
    onGoLogin: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    val state by authViewModel.uiState.collectAsState()
    val colors = MaterialTheme.colorScheme
    val focusManager = LocalFocusManager.current

    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var pass2 by remember { mutableStateOf("") }

    var showPass by remember { mutableStateOf(false) }
    var showPass2 by remember { mutableStateOf(false) }

    val passOk = pass.length >= 6
    val matchOk = pass.isNotEmpty() && pass == pass2

    val canSubmit = nombre.isNotBlank() &&
            email.isNotBlank() &&
            passOk &&
            matchOk &&
            !state.loading

    // Navega al Home cuando se registre (tu VM ya pone isLogged = true)
    LaunchedEffect(state.isLogged) {
        if (state.isLogged) onRegisterSuccess()
    }

    val bg = Brush.verticalGradient(
        colors = listOf(
            colors.background,
            colors.background,
            colors.primary.copy(alpha = 0.12f)
        )
    )

    Scaffold(containerColor = colors.background) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(bg)
        ) {

            RegisterBottomWave(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .align(Alignment.BottomCenter),
                primary = colors.primary,
                secondary = colors.secondary
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // Logo pequeño
                Card(
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = colors.surface),
                    modifier = Modifier.size(92.dp)
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.icono),
                            contentDescription = "Logo Jakawi Agro",
                            modifier = Modifier.size(54.dp)
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "Crear cuenta",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = colors.onBackground
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = "Regístrate para guardar tus parcelas y gestionar tu información.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.onBackground.copy(alpha = 0.70f),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(18.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = colors.surface.copy(alpha = 0.96f))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        OutlinedTextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            label = { Text("Nombre completo") },
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
                        )

                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it.trim() },
                            label = { Text("Correo") },
                            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
                        )

                        OutlinedTextField(
                            value = pass,
                            onValueChange = { pass = it },
                            label = { Text("Contraseña (mín. 6)") },
                            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                            trailingIcon = {
                                IconButton(onClick = { showPass = !showPass }) {
                                    Icon(
                                        imageVector = if (showPass) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                        contentDescription = "Ver contraseña"
                                    )
                                }
                            },
                            singleLine = true,
                            visualTransformation = if (showPass) VisualTransformation.None else PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
                        )

                        // Helper de contraseña
                        if (pass.isNotBlank() && !passOk) {
                            Text(
                                text = "La contraseña debe tener al menos 6 caracteres.",
                                color = colors.error,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        OutlinedTextField(
                            value = pass2,
                            onValueChange = { pass2 = it },
                            label = { Text("Confirmar contraseña") },
                            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                            trailingIcon = {
                                IconButton(onClick = { showPass2 = !showPass2 }) {
                                    Icon(
                                        imageVector = if (showPass2) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                        contentDescription = "Ver confirmación"
                                    )
                                }
                            },
                            singleLine = true,
                            visualTransformation = if (showPass2) VisualTransformation.None else PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
                        )

                        if (pass2.isNotBlank() && !matchOk) {
                            Text(
                                text = "Las contraseñas no coinciden.",
                                color = colors.error,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        if (state.error != null) {
                            Text(
                                text = state.error ?: "",
                                color = colors.error,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        Spacer(Modifier.height(4.dp))

                        Button(
                            onClick = {
                                focusManager.clearFocus()
                                authViewModel.register(nombre.trim(), email.trim(), pass)
                            },
                            enabled = canSubmit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = colors.primary)
                        ) {
                            if (state.loading) {
                                CircularProgressIndicator(
                                    strokeWidth = 2.dp,
                                    modifier = Modifier.size(18.dp),
                                    color = colors.onPrimary
                                )
                            } else {
                                Text(
                                    text = "Registrarme",
                                    fontWeight = FontWeight.Bold,
                                    color = colors.onPrimary
                                )
                            }
                        }

                        TextButton(
                            onClick = onGoLogin,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text("¿Ya tienes cuenta? Inicia sesión")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RegisterBottomWave(
    modifier: Modifier,
    primary: Color,
    secondary: Color
) {
    androidx.compose.foundation.Canvas(modifier = modifier) {
        drawWave(primary.copy(alpha = 0.22f), secondary.copy(alpha = 0.25f))
        drawWave(primary.copy(alpha = 0.40f), secondary.copy(alpha = 0.55f), offsetY = 22f)
        drawWave(primary.copy(alpha = 0.90f), secondary.copy(alpha = 0.80f), offsetY = 44f)
    }
}

private fun DrawScope.drawWave(
    c1: Color,
    c2: Color,
    offsetY: Float = 0f
) {
    val w = size.width
    val h = size.height

    val path = Path().apply {
        moveTo(0f, h * 0.38f + offsetY)
        cubicTo(
            w * 0.25f, h * 0.20f + offsetY,
            w * 0.55f, h * 0.62f + offsetY,
            w, h * 0.38f + offsetY
        )
        lineTo(w, h)
        lineTo(0f, h)
        close()
    }

    drawPath(
        path = path,
        brush = Brush.horizontalGradient(listOf(c1, c2))
    )
}



