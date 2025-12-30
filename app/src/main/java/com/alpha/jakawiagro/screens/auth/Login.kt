package com.alpha.jakawiagro.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
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
fun Login(
    authViewModel: AuthViewModel,
    onGoRegister: () -> Unit,
    onForgotPassword: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    val state by authViewModel.uiState.collectAsState()
    val colors = MaterialTheme.colorScheme
    val focusManager = LocalFocusManager.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPass by remember { mutableStateOf(false) }

    val canSubmit = email.isNotBlank() && password.isNotBlank() && !state.loading

    // Navega al home si ya inició sesión
    LaunchedEffect(state.isLogged) {
        if (state.isLogged) onLoginSuccess()
    }

    // Fondo suave (arriba claro, abajo con primary)
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

            // Curva inferior estilo imagen (adaptada al theme)
            LoginBottomWave(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .align(Alignment.BottomCenter),
                primary = colors.primary,
                secondary = colors.secondary
            )

            // Contenido
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // Logo pequeño arriba
                Card(
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = colors.surface),
                    modifier = Modifier.size(96.dp)
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.icono),
                            contentDescription = "Logo Jakawi Agro",
                            modifier = Modifier.size(58.dp)
                        )
                    }
                }

                Spacer(Modifier.height(18.dp))

                Text(
                    text = "Iniciar sesión",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = colors.onBackground
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = "Ingresa para gestionar tus parcelas y decisiones agrícolas.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.onBackground.copy(alpha = 0.70f),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(22.dp))

                // Card del formulario (minimal)
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
                            value = email,
                            onValueChange = { email = it.trim() },
                            label = { Text("Correo") },
                            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
                        )

                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Contraseña") },
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

                        // Error
                        if (state.error != null) {
                            Text(
                                text = state.error ?: "",
                                color = colors.error,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        Spacer(Modifier.height(4.dp))

                        // Botón principal
                        Button(
                            onClick = {
                                focusManager.clearFocus()
                                authViewModel.login(email, password)
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
                                    text = "Ingresar",
                                    fontWeight = FontWeight.Bold,
                                    color = colors.onPrimary
                                )
                            }
                        }

                        // Links
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TextButton(onClick = onForgotPassword) {
                                Text("¿Olvidaste tu clave?")
                            }
                            TextButton(onClick = onGoRegister) {
                                Text("Registrarme")
                            }
                        }
                    }
                }

                Spacer(Modifier.height(18.dp))

                // Texto inferior sutil (como la referencia)
                Text(
                    text = "Jakawi Agro • Cultiva decisiones inteligentes 🌱",
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.onBackground.copy(alpha = 0.55f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun LoginBottomWave(
    modifier: Modifier,
    primary: Color,
    secondary: Color
) {
    androidx.compose.foundation.Canvas(modifier = modifier) {
        drawWave(primary.copy(alpha = 0.22f), secondary.copy(alpha = 0.25f))
        drawWave(primary.copy(alpha = 0.40f), secondary.copy(alpha = 0.55f), offsetY = 24f)
        drawWave(primary.copy(alpha = 0.90f), secondary.copy(alpha = 0.80f), offsetY = 48f)
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
        moveTo(0f, h * 0.35f + offsetY)
        cubicTo(
            w * 0.25f, h * 0.20f + offsetY,
            w * 0.55f, h * 0.55f + offsetY,
            w, h * 0.35f + offsetY
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



