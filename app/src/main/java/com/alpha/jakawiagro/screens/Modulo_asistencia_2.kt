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
import com.alpha.jakawiagro.ui.theme.JakawiAgroTheme
import com.alpha.jakawiagro.ui.theme.shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsistenciaRecordatorio(
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val primaryBar = MaterialTheme.colorScheme.primary
    val backgroundColor = MaterialTheme.colorScheme.background
    val mint = MaterialTheme.colorScheme.secondaryContainer
    val pillBg = MaterialTheme.colorScheme.tertiaryContainer
    val textColor = MaterialTheme.colorScheme.onBackground
    val pillTextColor = MaterialTheme.colorScheme.onTertiaryContainer
    val plusColor = MaterialTheme.colorScheme.primaryContainer

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "ASISTENCIA",
                onMenuClick = onMenuClick,
                onProfileClick = onProfileClick
            )
        },
        containerColor = primaryBar
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
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
                        color = textColor,
                        lineHeight = 28.sp
                    )
                )

                Spacer(Modifier.height(18.dp))
                Card(
                    colors = CardDefaults.cardColors(containerColor = mint),
                    elevation = CardDefaults.cardElevation(0.dp),
                    shape = shapes.medium,
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
                                text = "Parcela 1 – Riego recomendado",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = textColor
                                ),
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "hoy 5 p.m.",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = textColor
                                )
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            listOf("Ver detalle", "Marcar como hecho").forEach { pillText ->
                                Surface(color = pillBg, shape = shapes.small) {
                                    Text(
                                        pillText,
                                        color = pillTextColor,
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            fontWeight = FontWeight.SemiBold
                                        ),
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                // ------- CARD 2 (Corte) -------
                Card(
                    colors = CardDefaults.cardColors(containerColor = mint),
                    elevation = CardDefaults.cardElevation(0.dp),
                    shape = shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
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
                                    color = textColor
                                ),
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            listOf("Ver detalle", "Marcar como hecho").forEach { pillText ->
                                Surface(color = pillBg, shape = shapes.small) {
                                    Text(
                                        pillText,
                                        color = pillTextColor,
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            fontWeight = FontWeight.SemiBold
                                        ),
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                    )
                                }
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
                        .background(plusColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text("+", fontSize = 28.sp, color = textColor, fontWeight = FontWeight.Bold)
                }
            }

            // Botón ATRAS abajo-derecha
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = mint, contentColor = pillTextColor),
                shape = shapes.medium,
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


@Preview(uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAsistenciaRecordatorio() {
    JakawiAgroTheme{
        AsistenciaRecordatorio()
    }
}
