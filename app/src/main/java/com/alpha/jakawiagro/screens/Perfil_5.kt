package com.alpha.jakawiagro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
fun SoporteAyuda(
    onVerTutorialClick: () -> Unit = {},
    onVolverClick: () -> Unit = {},
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
                title = "SOPORTE TECNICO",
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
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(20.dp))

            Text(
                text = "Centro de ayuda y soporte técnico",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color(0xFF1B1B1B),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(30.dp))
            SoporteCard(
                icono = R.drawable.icono_preguntas,
                texto = "Preguntas frecuentes (FAQ)"
            )
            SoporteCard(
                icono = R.drawable.icono_contacto,
                texto = "Contacto soporte técnico"
            )
            SoporteCard(
                icono = R.drawable.icono_tutorial,
                texto = "Tutorial básico"
            )

            Spacer(Modifier.height(40.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = onVerTutorialClick,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonGreen,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("VER TUTORIAL", textAlign = TextAlign.Center)
                }

                Spacer(Modifier.width(12.dp))

                OutlinedButton(
                    onClick = onVolverClick,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF333333)
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("VOLVER", textAlign = TextAlign.Center)
                }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun SoporteCard(
    icono: Int,
    texto: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F6EC))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = icono),
                contentDescription = texto,
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 16.dp)
            )
            Text(
                text = texto,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1B1B1B)
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSoporteAyuda() {
    SoporteAyuda()
}
