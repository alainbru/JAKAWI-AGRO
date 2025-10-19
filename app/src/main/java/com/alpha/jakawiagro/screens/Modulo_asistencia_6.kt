package com.alpha.jakawiagro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alpha.jakawiagro.R

@Composable
fun AsistenciaHistorial() {
    val beige = Color(0xFFF1EAD9)
    val greenBar = Color(0xFF8BC34A)
    val mint = Color(0xFF78D9AE)
    val pill = Color(0xFFD9E6E0)
    val textDark = Color(0xFF21342C)

    Scaffold(
        topBar = { MainTopAppBar(title = "ASISTENCIA") },
        containerColor = greenBar
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(beige)
                .padding(padding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(22.dp))
            Text(
                text = "HISTORIAL DE\nASISTENCIA",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 26.sp,           // ↑ tamaño
                    letterSpacing = 2.sp,       // ↑ espaciado
                    lineHeight = 32.sp,
                    fontWeight = FontWeight.Black,
                    color = textDark
                )
            )

            Spacer(Modifier.height(22.dp))

            Column(
                modifier = Modifier.fillMaxWidth().widthIn(max = 460.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(verticalAlignment = Alignment.Top) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)                               // ↑ tamaño del check
                            .clip(CircleShape)
                            .background(mint),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.icono_check),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)            // ↑ icono interno
                        )
                    }
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Riego completado – 12 marzo 2024",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = textDark,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                        Text(
                            text = "(Parcela 1)",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = textDark.copy(alpha = 0.85f)
                            )
                        )
                    }
                }
                Row(verticalAlignment = Alignment.Top) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(mint),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.icono_check),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Corte de alfalfa – 10 marzo 2024",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = textDark,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                        Text(
                            text = "(Parcela 2)",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = textDark.copy(alpha = 0.85f)
                            )
                        )
                    }
                }
            }

            Spacer(Modifier.height(22.dp))
            Row(
                modifier = Modifier.fillMaxWidth().widthIn(max = 460.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = pill),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Registro", color = textDark, fontWeight = FontWeight.Bold)
                    }
                }
                Card(
                    colors = CardDefaults.cardColors(containerColor = pill),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.icono_busqueda),
                            contentDescription = null,
                            modifier = Modifier.size(18.dp) // ↑ icono filtro
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Filtro", color = textDark, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(Modifier.height(14.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = mint),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 460.dp)
                    .height(50.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "+",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Black,
                        color = textDark
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        "Nueva tarea",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Black,
                            color = Color.Black
                        )
                    )
                }
            }

            Spacer(Modifier.height(14.dp))

            Text(
                text = "Volver",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = textDark,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                )
            )

            Spacer(Modifier.height(18.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAsistencia() {
    AsistenciaHistorial()
}

