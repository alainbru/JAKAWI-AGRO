package com.alpha.jakawiagro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alpha.jakawiagro.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilPrincipal(
    onEditarPerfilClick: () -> Unit = {},
    onConfiguracionClick: () -> Unit = {},
    onHistorialClick: () -> Unit = {},
    onSoporteClick: () -> Unit = {},
    onCerrarSesionClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val beige = Color(0xFFF1EAD9)
    val greenBar = Color(0xFFBFD84E)
    val greenButton = Color(0xFFDFF57A)

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "PERFIL",
                onMenuClick = onMenuClick,
                onProfileClick = onProfileClick
            )
        },
        containerColor = greenBar
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(beige)
                .padding(padding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(Modifier.height(24.dp))

            // Imagen de perfil
            Image(
                painter = painterResource(id = R.drawable.icono_camara),
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )

            Spacer(Modifier.height(12.dp))

            // Nombre
            Text(
                text = "ELSA PATO",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B1B1B)
                )
            )

            // Ocupación
            Text(
                text = "Agricultora familiar",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF3B3B3B)
                )
            )

            Spacer(Modifier.height(4.dp))

            // Ubicación
            Text(
                text = "📍 Puno",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF5C5C5C)
                )
            )

            Spacer(Modifier.height(24.dp))

            // Botones de acción
            PerfilButton("EDITAR PERFIL", onEditarPerfilClick)
            PerfilButton("CONFIGURACIONES", onConfiguracionClick)
            PerfilButton("HISTORIAL", onHistorialClick)
            PerfilButton("SOPORTE Y AYUDA", onSoporteClick)

            Spacer(Modifier.height(12.dp))

            // Botón de cerrar sesión
            TextButton(onClick = onCerrarSesionClick) {
                Text(
                    text = "CERRAR SESIÓN",
                    color = Color(0xFFD32F2F),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun PerfilButton(
    text: String,
    onClick: () -> Unit
) {
    val buttonColor = Color(0xFFDCE775)

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPerfilPrincipal() {
    PerfilPrincipal()
}


