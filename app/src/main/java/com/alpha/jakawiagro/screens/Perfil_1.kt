package com.alpha.jakawiagro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
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
    val buttonGreen = Color(0xFFDCE775)
    val cardBg = Color(0xFFF9F6EC)

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
            Spacer(Modifier.height(28.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(
                        width = 4.dp,
                        brush = Brush.linearGradient(
                            listOf(Color(0xFFAED581), Color(0xFF8BC34A))
                        ),
                        shape = CircleShape
                    )
                    .shadow(6.dp, CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icono_camara),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(Modifier.height(14.dp))
            Text(
                text = "ELSA PATO",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B1B1B)
                )
            )

            Text(
                text = "Agricultora familiar",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF3B3B3B)
                )
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = "📍 Puno",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF5C5C5C)
                )
            )

            Spacer(Modifier.height(28.dp))

            PerfilCardButton("✏️  EDITAR PERFIL", onEditarPerfilClick)
            PerfilCardButton("⚙️  CONFIGURACIONES", onConfiguracionClick)
            PerfilCardButton("📋  HISTORIAL", onHistorialClick)
            PerfilCardButton("💬  SOPORTE TECNICO ", onSoporteClick)

            Spacer(Modifier.height(20.dp))
            Button(
                onClick = onCerrarSesionClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color(0xFFD32F2F)
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFFD32F2F), Color(0xFFEF5350))
                    )
                ),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            ) {
                Text(
                    text = "CERRAR SESIÓN",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun PerfilCardButton(
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F6EC))
    ) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFDCE775),
                contentColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 17.sp
                ),
                modifier = Modifier.padding(vertical = 6.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPerfilPrincipal() {
    PerfilPrincipal()
}

