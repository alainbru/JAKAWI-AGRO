package com.alpha.jakawiagro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
fun AsistenciaScreen(
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val beige = Color(0xFFF1EAD9)
    val greenBar = Color(0xFF8BC34A)
    val mint = Color(0xFF78D9AE)
    val pillBg = Color(0xFF90CDB5)
    val textDark = Color(0xFF1B2E27)

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "ASISTENCIA",
                onMenuClick = onMenuClick,
                onProfileClick = onProfileClick
            )
        },
        containerColor = greenBar
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(beige)
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(18.dp))

                Text(
                    text = "VISTA DE\nRECORDATORIO\nPOR PARCELAS",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 22.sp,
                        letterSpacing = 2.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF2B3E36),
                        lineHeight = 28.sp
                    )
                )

                Spacer(Modifier.height(18.dp))
                Card(
                    colors = CardDefaults.cardColors(containerColor = mint),
                    elevation = CardDefaults.cardElevation(0.dp),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(R.drawable.icono_gota),
                                contentDescription = null,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "Parcela 1 – regar el cultivo\nrecomendable hoy",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = textDark
                                ),
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            // Pill 1
                            Surface(color = pillBg, shape = RoundedCornerShape(10.dp)) {
                                Text(
                                    "Ver detalle",
                                    color = Color(0xFF0F2F27),
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                )
                            }
                            // Pill 2
                            Surface(color = pillBg, shape = RoundedCornerShape(10.dp)) {
                                Text(
                                    "Marcar como hecho",
                                    color = Color(0xFF0F2F27),
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                // ------- CARD 2 (Corte) -------
                Card(
                    colors = CardDefaults.cardColors(containerColor = mint),
                    elevation = CardDefaults.cardElevation(0.dp),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            // Usa tu ícono de tijeras si lo agregas; de momento placeholder
                            Image(
                                painter = painterResource(R.drawable.icono_asistencia),
                                contentDescription = null,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "Parcela 2 – Primer corte\nel 10 de abril",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = textDark
                                ),
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            Surface(color = pillBg, shape = RoundedCornerShape(10.dp)) {
                                Text(
                                    "Ver detalle",
                                    color = Color(0xFF0F2F27),
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                )
                            }
                            Surface(color = pillBg, shape = RoundedCornerShape(10.dp)) {
                                Text(
                                    "Marcar como hecho",
                                    color = Color(0xFF0F2F27),
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(18.dp))

                // Botón central "+"
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(greenBar),
                    contentAlignment = Alignment.Center
                ) {
                    Text("+", fontSize = 28.sp, color = Color(0xFF2B3E36), fontWeight = FontWeight.Bold)
                }
            }

            // Botón ATRAS abajo-derecha
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = mint, contentColor = Color.Black),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .height(44.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("ATRAS")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAsis() {
    AsistenciaScreen()
}

