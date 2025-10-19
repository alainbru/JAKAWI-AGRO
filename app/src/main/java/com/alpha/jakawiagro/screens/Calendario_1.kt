package com.alpha.jakawiagro.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale


@Composable
fun CalendarioP() {
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "CALENDARIO",
                onMenuClick = { /* Abrir drawer o menú */ },
                onProfileClick = { /* Ir a perfil o ajustes */ }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Calendario'",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    )
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarioMensual(
    year: Int,
    month: Int,
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit
) {
    val diasSemana = listOf("L", "M", "Mi", "J", "V", "S", "D")
    val primerDiaMes = LocalDate.of(year, month, 1)
    val diasEnMes = primerDiaMes.lengthOfMonth()
    val primerDiaSemana = primerDiaMes.dayOfWeek.value % 7 // Lunes = 1 → 0 index

    Column(modifier = Modifier.padding(16.dp)) {
        // Encabezado de días
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            diasSemana.forEach {
                Text(text = it, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            }
        }

        // Celdas de días
        val totalCeldas = ((primerDiaSemana + diasEnMes + 6) / 7) * 7
        for (fila in 0 until totalCeldas step 7) {
            Row(modifier = Modifier.fillMaxWidth()) {
                for (columna in 0..6) {
                    val index = fila + columna
                    val dia = index - primerDiaSemana + 1
                    val fechaActual = if (dia in 1..diasEnMes) LocalDate.of(year, month, dia) else null

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clickable(enabled = fechaActual != null) {
                                fechaActual?.let { onDateSelected(it) }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (fechaActual != null) {
                            val isSelected = fechaActual == selectedDate
                            Text(
                                text = dia.toString(),
                                color = if (isSelected) Color.White else Color.Black,
                                modifier = Modifier
                                    .background(
                                        if (isSelected) Color.Blue else Color.Transparent,
                                        shape = CircleShape
                                    )
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarioConNavegacion() {
    var fechaActual by remember { mutableStateOf(LocalDate.now()) }
    var fechaSeleccionada by remember { mutableStateOf<LocalDate?>(null) }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween


        ) {
            IconButton(onClick = {
                fechaActual = fechaActual.minusMonths(1)
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Mes anterior")
            }

            Text(
                text = "${fechaActual.month.getDisplayName(TextStyle.FULL, Locale("es"))} ${fechaActual.year}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            IconButton(onClick = {
                fechaActual = fechaActual.plusMonths(1)
            }) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Mes siguiente")
            }
        }

        CalendarioMensual(
            year = fechaActual.year,
            month = fechaActual.monthValue,
            selectedDate = fechaSeleccionada,
            onDateSelected = { fechaSeleccionada = it }
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCalendarioP() {
    MaterialTheme {
        Scaffold(
            topBar = {
                MainTopAppBar(
                    title = "CALENDARIO",
                    onMenuClick = { },
                    onProfileClick = { }
                )
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Título
                    Text(
                        text = "Calendario",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Llamamos al calendario con navegación
                    CalendarioConNavegacion()
                }
            }
        )
    }
}


