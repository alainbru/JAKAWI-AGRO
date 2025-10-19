package com.alpha.jakawiagro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun AsistenciaCrearTarea() {
    val beige = Color(0xFFF1EAD9)
    val greenBar = Color(0xFF8BC34A)
    val mint = Color(0xFF78D9AE)
    val textDark = Color(0xFF1E2F28)
    val line = Color(0x33000000)

    Scaffold(
        topBar = { MainTopAppBar(title = "ASISTENCIA") },
        containerColor = greenBar
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(beige)
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Título
            Text(
                text = "CREAR  NUEVA\nTAREA",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = textDark,
                    fontSize = 22.sp,
                    lineHeight = 28.sp,
                    letterSpacing = 2.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                textAlign = TextAlign.Start
            )

            Spacer(Modifier.height(18.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.icono_parcelas),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = "Seleccionar parcela",
                    style = MaterialTheme.typography.bodyLarge.copy(color = textDark)
                )
            }
            Spacer(Modifier.height(8.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(line)
            )

            Spacer(Modifier.height(16.dp))
            Text(
                text = "Tipo de labor (Riego, Fertilización,\nCorte, Otro)",
                style = MaterialTheme.typography.bodyLarge.copy(color = textDark)
            )
            Spacer(Modifier.height(8.dp))
            Box(Modifier.fillMaxWidth().height(1.dp).background(line))

            Spacer(Modifier.height(16.dp))
            Text(
                text = "Fecha y hora",
                style = MaterialTheme.typography.bodyLarge.copy(color = textDark)
            )
            Spacer(Modifier.height(8.dp))
            Box(Modifier.fillMaxWidth().height(1.dp).background(line))

            Spacer(Modifier.height(16.dp))
            Text(
                text = "Nota opcional",
                style = MaterialTheme.typography.bodyLarge.copy(color = textDark.copy(alpha = 0.85f))
            )
            Spacer(Modifier.height(8.dp))
            Box(Modifier.fillMaxWidth().height(1.dp).background(line))

            Spacer(Modifier.height(20.dp))
            Surface(
                color = mint,
                contentColor = Color.Black,
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.icono_documento),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = "Guardar tarea",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }

            Spacer(Modifier.height(14.dp))
            Text(
                text = "Cancelar",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = textDark,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAsistenciaCrear() {
    AsistenciaCrearTarea()
}
