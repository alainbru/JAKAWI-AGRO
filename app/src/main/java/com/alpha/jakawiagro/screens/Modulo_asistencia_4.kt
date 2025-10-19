package com.alpha.jakawiagro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun AsistenciaCalendario() {
    val beige = Color(0xFFF1EAD9)
    val greenBar = Color(0xFF8BC34A)
    val mint = Color(0xFF78D9AE)
    val text = Color(0xFF21342C)

    Scaffold(
        topBar = { MainTopAppBar("ASISTENCIA") },
        containerColor = greenBar
    ) { p ->
        Column(
            Modifier
                .fillMaxSize()
                .background(beige)
                .padding(p)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "CALENDARIO\nAGRICOLA",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 22.sp, letterSpacing = 2.sp, lineHeight = 28.sp,
                    fontWeight = FontWeight.SemiBold, color = text
                )
            )
            Spacer(Modifier.height(16.dp))
            Column(Modifier.widthIn(max = 360.dp)) {
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                    listOf("LU","MA","MI","JU","VI","SA","DO").forEach {
                        Text(
                            it,
                            modifier = Modifier.width(32.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold, color = text
                            )
                        )
                    }
                }
                Spacer(Modifier.height(8.dp))

                var d = 0
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                    repeat(2) { Box(Modifier.width(32.dp).height(28.dp)) }
                    repeat(5) { d++; Day(d, selected = d == 10, textColor = text) }
                }
                Spacer(Modifier.height(6.dp))
                repeat(5) {
                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                        repeat(7) {
                            if (d < 31) { d++; Day(d, selected = d == 10, textColor = text) }
                            else Box(Modifier.width(32.dp).height(28.dp))
                        }
                    }
                    Spacer(Modifier.height(6.dp))
                }
            }

            Spacer(Modifier.height(16.dp))
            Tarea(icon = R.drawable.icono_asistencia, text = "Corte de alfalfa", chipColor = mint)
            Spacer(Modifier.height(8.dp))
            Tarea(icon = R.drawable.icono_gota, text = "Riego programado", chipColor = mint)

            Spacer(Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "+ ",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold, color = text
                    )
                )
                Column {
                    Text("Nueva", color = text, textDecoration = TextDecoration.Underline)
                    Text("tarea", color = text, textDecoration = TextDecoration.Underline)
                }
            }

            Spacer(Modifier.height(16.dp))

            // “ATRAS” visual
            Surface(color = Color(0xFFD6E35E), contentColor = Color.Black, shape = RoundedCornerShape(12.dp)) {
                Text("←  ATRAS", modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp))
            }
        }
    }
}
@Composable
fun Day(n: Int, selected: Boolean, textColor: Color) {
    Box(Modifier.width(32.dp).height(28.dp), contentAlignment = Alignment.Center) {
        if (selected) {
            Box(
                Modifier
                    .size(26.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF5BBE99))
            )
        }
        Text(
            n.toString(),
            style = MaterialTheme.typography.bodySmall.copy(
                color = if (selected) Color.White else textColor,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
            )
        )
    }
}

@Composable
fun Tarea(icon: Int, text: String, chipColor: Color) {
    Surface(color = chipColor, shape = RoundedCornerShape(12.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painterResource(icon), contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(10.dp))
            Text(text, style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAsistenciaCalendario() {
    AsistenciaCalendario()
}

