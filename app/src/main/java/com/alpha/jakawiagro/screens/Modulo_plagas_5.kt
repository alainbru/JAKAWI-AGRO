package com.alpha.jakawiagro.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModuloPlagasFicha(
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onRegistrarClick: () -> Unit = {},
    onAtrasClick: () -> Unit = {}
) {
    val beige = Color(0xFFF1EAD9)
    val greenBar = Color(0xFF8BC34A)
    val panelBg = Color(0xFFF5F7F2)
    val panelBorder = Color(0xFFE1E5DA)
    val mint = Color(0xFF78D9AE)
    val darkBtn = Color(0xFF2E3C30)

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "FICHA TECNICA",
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
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(12.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 360.dp)
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = panelBg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .border(1.dp, panelBorder, RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(14.dp)
                    ) {
                        Text(
                            text = """
Estado de conservación general: En general, la mayoría de las especies del género Gamocheta no están consideradas en riesgo a nivel global. A menudo son malezas o plantas silvestres comunes que crecen en áreas perturbadas, como bordes de caminos o terrenos de cultivo. Esto sugiere que son bastante resistentes y adaptables.

Estado de conservación de especies específicas: El estado de conservación puede variar mucho según la especie y la región. Por ejemplo, una especie endémica, como la Gamocheta fernandeziana de Chile, ha sido clasificada como "En Peligro" debido a su distribución limitada y a amenazas de especies invasoras como la zarzamora. Esto demuestra que si bien el género en general es resistente, algunas de sus especies requieren atención especial.

Factores que afectan su salud:
• Competencia: En ambientes con competencia por luz, agua u otros nutrientes y agua, es común que su vitalidad disminuya.  
• Herbivoría: Pueden verse afectadas por insectos fitófagos, especialmente orugas y áfidos.  
• Patógenos: Son susceptibles a enfermedades de “roya” del género Puccinia y oídios, especialmente en climas húmedos.  
• Prácticas de manejo: El control de malezas, el pisoteo por ganado y el uso de herbicidas pueden afectar su regeneración natural.
                            """.trimIndent(),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                color = Color(0xFF1E1E1E)
                            )
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = onRegistrarClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = darkBtn,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Registrar"
                        )
                        Spacer(Modifier.width(9.dp))
                        Text(
                            "REGISTRAR EN\nPARCELA",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                letterSpacing = 0.4.sp
                            )
                        )
                    }

                    Button(
                        onClick = onAtrasClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = mint,
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atrás"
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "ATRAS",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewModuloPlagasFicha() {
    ModuloPlagasFicha()
}
