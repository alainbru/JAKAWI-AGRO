package com.alpha.jakawiagro.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.R
import com.alpha.jakawiagro.viewmodel.auth.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecuperarClave(
    authViewModel: AuthViewModel,
    onBack: () -> Unit
) {
    val state by authViewModel.uiState.collectAsState()
    val colors = MaterialTheme.colorScheme
    val focusManager = LocalFocusManager.current

    var email by remember { mutableStateOf("") }
    var enviado by remember { mutableStateOf(false) }
    var submitted by remember { mutableStateOf(false) }

    // Marca "enviado" solo si terminó loading y no hubo error
    LaunchedEffect(state.loading, state.error) {
        if (submitted && !state.loading) {
            enviado = state.error == null && email.isNotBlank()
            submitted = false
        }
    }

    val bg = Brush.verticalGradient(
        colors = listOf(
            colors.background,
            colors.background,
            colors.primary.copy(alpha = 0.12f)
        )
    )

    Scaffold(
        containerColor = colors.background,
        topBar = {
            TopAppBar(
                title = { Text("Recuperar clave") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(bg)
        ) {

            // Wave inferior estilo login
            RecoverBottomWave(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
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
                    text = "¿Olvidaste tu contraseña?",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = colors.onBackground,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = "Ingresa tu correo y te enviaremos un enlace para restablecerla.",
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
                            value = email,
                            onValueChange = {
                                email = it.trim()
                                enviado = false
                            },
                            label = { Text("Correo") },
                            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
                        )

                        // Mensaje de error
                        if (state.error != null) {
                            Text(
                                text = state.error ?: "",
                                color = colors.error,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        // Mensaje de éxito
                        if (enviado) {
                            Text(
                                text = "✅ Listo. Revisa tu correo.",
                                color = colors.primary,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Spacer(Modifier.height(4.dp))

                        Button(
                            onClick = {
                                focusManager.clearFocus()
                                if (email.isNotBlank()) {
                                    enviado = false
                                    submitted = true
                                    authViewModel.resetPassword(email)
                                }
                            },
                            enabled = email.isNotBlank() && !state.loading,
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
                                    text = "Enviar enlace",
                                    fontWeight = FontWeight.Bold,
                                    color = colors.onPrimary
                                )
                            }
                        }

                        TextButton(
                            onClick = onBack,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text("Volver al login")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RecoverBottomWave(
    modifier: Modifier,
    primary: Color,
    secondary: Color
) {
    androidx.compose.foundation.Canvas(modifier = modifier) {
        drawWave(primary.copy(alpha = 0.22f), secondary.copy(alpha = 0.25f))
        drawWave(primary.copy(alpha = 0.40f), secondary.copy(alpha = 0.55f), offsetY = 20f)
        drawWave(primary.copy(alpha = 0.90f), secondary.copy(alpha = 0.80f), offsetY = 40f)
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
        moveTo(0f, h * 0.40f + offsetY)
        cubicTo(
            w * 0.25f, h * 0.20f + offsetY,
            w * 0.55f, h * 0.60f + offsetY,
            w, h * 0.40f + offsetY
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


