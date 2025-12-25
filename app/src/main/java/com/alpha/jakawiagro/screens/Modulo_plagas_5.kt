package com.alpha.jakawiagro.screens

import android.content.res.Configuration
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
import com.alpha.jakawiagro.ui.theme.JakawiAgroTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModuloPlagasFicha(
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onRegistrarClick: () -> Unit = {},
    onAtrasClick: () -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "FICHA TÉCNICA",
                onMenuClick = onMenuClick,
                onProfileClick = onProfileClick
            )
        },
        containerColor = colors.primary
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
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
                    colors = CardDefaults.cardColors(
                        containerColor = colors.surface
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            1.dp,
                            colors.outline,
                            RoundedCornerShape(16.dp)
                        ),
                    shape = RoundedCornerShape(16.dp)
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
                                lineHeight = 20.sp,
                                color = colors.onSurface
                            )
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Button(
                        onClick = onRegistrarClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.primaryContainer,
                            contentColor = colors.onPrimaryContainer
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    ) {
                        Icon(Icons.Filled.Edit, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "REGISTRAR\nPARCELA",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }

                    Button(
                        onClick = onAtrasClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.secondaryContainer,
                            contentColor = colors.onSecondaryContainer
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                        Spacer(Modifier.width(6.dp))
                        Text("ATRÁS", fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(Modifier.height(12.dp))
        }
    }
}


@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewModuloPlagasFichaDark() {
    JakawiAgroTheme {
        ModuloPlagasFicha()
    }
}
