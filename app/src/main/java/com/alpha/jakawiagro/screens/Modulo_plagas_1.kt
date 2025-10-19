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
fun ModuloPlagas1(
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
            horizontalAlignment = Alignment.CenterHorizontally
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
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Ten en cuenta los siguientes pasos:",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF3B3B3B)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            // ===== SECCIÓN: Tarjeta con pasos =====
            Card(
                colors = CardDefaults.cardColors(containerColor = greenCard),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth(0.92f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    PasoItemImagen(
                        drawableRes = R.drawable.icono_ojo,
                        texto = "Observa atentamente"
                    )

                    PasoItemImagen(

                        drawableRes = R.drawable.icono_camara,
                        texto = "Toma una foto clara"
                    )

                    PasoItemImagen(
                        drawableRes = R.drawable.icono_busqueda,
                        texto = "Verifica la plaga o enfermedad"
                    )

                    PasoItemImagen(
                        drawableRes = R.drawable.icono_documento,
                        texto = "Sigue la ficha técnica"
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

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
                Text("»»", textAlign = TextAlign.Center)
            }

            Spacer(Modifier.height(18.dp))
        }
    }
}


@Composable
fun PasoItemImagen(
    drawableRes: Int,
    texto: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {

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
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlagas1() {
    ModuloPlagas1()
}



