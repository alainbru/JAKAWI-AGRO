package com.alpha.jakawiagro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
fun ModuloPlagas1Screen(
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onNextClick: () -> Unit = {}
) {
    val beige = Color(0xFFF1EAD9)
    val greenBar = Color(0xFF8BC34A)
    val greenCard = Color(0xFF78D9AE)

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "PLAGAS Y ENFERMEDADES",
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
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally // <— TODO CENTRADO
        ) {

            Spacer(Modifier.height(14.dp))

            Image(
                painter = painterResource(id = R.drawable.icono_bienvenida2),
                contentDescription = "Bienvenida",
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "¡HOLA! bienvenido al segmento de plagas/enfermedades con IA.",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1B1B1B)
                ),
                textAlign = TextAlign.Center,                    // <— centrado
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Ten en cuenta los siguientes pasos:",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF3B3B3B)
                ),
                textAlign = TextAlign.Center,                    // <— centrado
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            // ===== SECCIÓN: Tarjeta verde con pasos (centrada) =====
            Card(
                colors = CardDefaults.cardColors(containerColor = greenCard),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth(0.92f)  // ancho ligeramente menor y centrado
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally // <— contenido centrado
                ) {
                    // ---- Paso 1: Observa atentamente
                    PasoItemImagen(
                        // >>>>> AÑADE AQUÍ EL ÍCONO/IMAGEN DEL OJO <<<<<
                        // Cambia 'icono_ojo' por tu archivo (p. ej. icono_asistencia, etc.)
                        drawableRes = R.drawable.icono_ojo,
                        texto = "Observa atentamente"
                    )

                    // ---- Paso 2: Toma una foto clara
                    PasoItemImagen(
                        // >>>>> AÑADE AQUÍ EL ÍCONO/IMAGEN DE CÁMARA <<<<<
                        // Si tu archivo es icono.jpg, sería R.drawable.icono
                        drawableRes = R.drawable.icono_camara,
                        texto = "Toma una foto clara"
                    )

                    // ---- Paso 3: Verifica la plaga o enfermedad
                    PasoItemImagen(
                        // >>>>> AÑADE AQUÍ EL ÍCONO/IMAGEN DE BÚSQUEDA/CHECK <<<<<
                        drawableRes = R.drawable.icono_busqueda,
                        texto = "Verifica la plaga o enfermedad"
                    )

                    // ---- Paso 4: Sigue la ficha técnica
                    PasoItemImagen(
                        // >>>>> AÑADE AQUÍ EL ÍCONO/IMAGEN DE DOCUMENTO/FICHA <<<<<
                        drawableRes = R.drawable.icono_documento,
                        texto = "Sigue la ficha técnica"
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // ===== SECCIÓN: Botón SIGUIENTE (ya centrado) =====
            Button(
                onClick = onNextClick,
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF98BFA5),
                    contentColor = Color.Black
                )
            ) {
                Text("SIGUIENTE")
                Spacer(Modifier.width(8.dp))
                // >>>>> OPCIONAL: usa una imagen de flechas si la tienes en drawable <<<<<
                // Ej: Image(painterResource(R.drawable.icono_flechas), contentDescription = "Ir")
                Text("»»", textAlign = TextAlign.Center)
            }

            Spacer(Modifier.height(18.dp))
        }
    }
}

/**
 * Item de paso con imagen de res/drawable, centrado horizontalmente.
 * - Sustituye 'drawableRes' por tus propios recursos en cada uso.
 */
@Composable
fun PasoItemImagen(
    drawableRes: Int,
    texto: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,           // <— centrado
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // >>>>> AÑADE TU ÍCONO/IMAGEN AQUÍ <<<<<
        // Coloca tu PNG/JPG en res/drawable y pásalo como drawableRes
        Image(
            painter = painterResource(id = drawableRes),
            contentDescription = null,
            modifier = Modifier.size(28.dp)
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = texto,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
                color = Color(0xFF103B2B)
            ),
            textAlign = TextAlign.Center                      // <— texto centrado
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewModuloPlagas1Screen() {
    ModuloPlagas1Screen()
}



