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
fun HistorialPersonal(
    onExportarClick: () -> Unit = {},
    onVolverClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val beige = Color(0xFFF1EAD9)
    val greenBar = Color(0xFFBFD84E)
    val buttonGreen = Color(0xFFDCE775)

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "HISTORIAL",
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
                text = "Resumen de tus actividades",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color(0xFF1B1B1B),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(30.dp))

            HistorialItem(
                icono = R.drawable.icono_parcelas,
                valor = "12",
                descripcion = "Parcelas registradas"
            )
            HistorialItem(
                icono = R.drawable.icono_plagas,
                valor = "6",
                descripcion = "Plagas detectadas"
            )
            HistorialItem(
                icono = R.drawable.icono_asistencia,
                valor = "21",
                descripcion = "Asistencias completadas"
            )
            HistorialItem(
                icono = R.drawable.icono_calendario,
                valor = "Hoy a las 16:30",
                descripcion = "Última actividad"
            )

            Spacer(Modifier.height(40.dp))

            // Botones inferiores
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = onExportarClick,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonGreen,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("EXPORTAR DATOS", textAlign = TextAlign.Center)
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
fun HistorialItem(
    icono: Int,
    valor: String,
    descripcion: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(id = icono),
            contentDescription = descripcion,
            modifier = Modifier.size(36.dp)
        )

        Spacer(Modifier.width(16.dp))

        Column {
            Text(
                text = valor,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFF1B1B1B)
                )
            )
            Text(
                text = descripcion,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF4F4F4F)
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHistorialPersonal() {
    HistorialPersonal()
}
