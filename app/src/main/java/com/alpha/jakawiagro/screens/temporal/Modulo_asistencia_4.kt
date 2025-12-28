package com.alpha.jakawiagro.screens.temporal

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.alpha.jakawiagro.ui.theme.JakawiAgroTheme
import com.alpha.jakawiagro.ui.theme.shapes

@Composable
fun AsistenciaCalendario() {
    val mint = MaterialTheme.colorScheme.secondaryContainer
    val textColor = MaterialTheme.colorScheme.onBackground
    val backgroundColor = MaterialTheme.colorScheme.background
    val primaryBar = MaterialTheme.colorScheme.primary
    val selectedDayColor = MaterialTheme.colorScheme.primaryContainer

    Scaffold(
        topBar = { MainTopAppBar("ASISTENCIA") },
        containerColor = primaryBar
    ) { p ->
        Column(
            Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(p)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "CALENDARIO\nAGRICOLA",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 22.sp,
                    letterSpacing = 2.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor
                )
            )
            Spacer(Modifier.height(16.dp))

            Column(Modifier.widthIn(max = 360.dp)) {
                // Días de la semana
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                    listOf("LU","MA","MI","JU","VI","SA","DO").forEach {
                        Text(
                            it,
                            modifier = Modifier.width(32.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = textColor
                            )
                        )
                    }
                }
                Spacer(Modifier.height(8.dp))

                // Calendario
                var d = 0
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                    repeat(2) { Box(Modifier.width(32.dp).height(28.dp)) }
                    repeat(5) { d++; Day(d, selected = d == 10, selectedColor = selectedDayColor, textColor = textColor) }
                }
                Spacer(Modifier.height(6.dp))
                repeat(5) {
                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                        repeat(7) {
                            if (d < 31) { d++; Day(d, selected = d == 10, selectedColor = selectedDayColor, textColor = textColor) }
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
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                )
                Column {
                    Text("Nueva", color = textColor, textDecoration = TextDecoration.Underline)
                    Text("tarea", color = textColor, textDecoration = TextDecoration.Underline)
                }
            }

            Spacer(Modifier.height(16.dp))

            Surface(
                color = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                shape = shapes.medium
            ) {
                Text(
                    "←  ATRAS",
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Composable
fun Day(n: Int, selected: Boolean, selectedColor: Color, textColor: Color) {
    Box(Modifier.width(32.dp).height(28.dp), contentAlignment = Alignment.Center) {
        if (selected) {
            Box(
                Modifier
                    .size(26.dp)
                    .clip(CircleShape)
                    .background(selectedColor)
            )
        }
        Text(
            n.toString(),
            style = MaterialTheme.typography.bodySmall.copy(
                color = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else textColor,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
            )
        )
    }
}

@Composable
fun Tarea(icon: Int, text: String, chipColor: Color) {
    Surface(
        color = chipColor,
        shape = shapes.medium
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painterResource(icon), contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(10.dp))
            Text(
                text,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
        }
    }
}



@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAsistenciaCalendario() {
    JakawiAgroTheme{
        AsistenciaCalendario()
    }
}

