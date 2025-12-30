package com.alpha.jakawiagro.screens.temporal

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alpha.jakawiagro.ui.theme.JakawiAgroTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevoForrajeScreen(
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onConfirmClick: () -> Unit = {}
) {
    var cantidad by remember { mutableStateOf("") }
    var unidad by remember { mutableStateOf("") }
    var tipoForraje by remember { mutableStateOf("") }
    var variedad by remember { mutableStateOf("") }
    var periodoInicio by remember { mutableStateOf("Verano Andino") }
    var descripcion by remember { mutableStateOf("") }
    var tipoGanado by remember { mutableStateOf("") }
    var destino by remember { mutableStateOf("") }
    var parcela by remember { mutableStateOf("") }
    var fechaInicio by remember { mutableStateOf("") }
    var fechaFin by remember { mutableStateOf("") }

    val colors = MaterialTheme.colorScheme
    val shapes = MaterialTheme.shapes

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Nuevo Forraje",
                onMenuClick = onMenuClick,
                onProfileClick = onProfileClick
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Cantidad y Unidad (misma fila)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = cantidad,
                    onValueChange = { cantidad = it },
                    label = { Text("Cantidad", color = colors.onSurface) },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = shapes.medium,


                )
                OutlinedTextField(
                    value = unidad,
                    onValueChange = { unidad = it },
                    label = { Text("Unidad", color = colors.onSurface) },
                    modifier = Modifier.weight(1f),
                    shape = shapes.medium,

                )
            }

            // Tipo de forraje
            OutlinedTextField(
                value = tipoForraje,
                onValueChange = { tipoForraje = it },
                label = { Text("Tipo de forraje", color = colors.onSurface) },
                modifier = Modifier.fillMaxWidth(),
                shape = shapes.medium,

            )

            // Variedad
            OutlinedTextField(
                value = variedad,
                onValueChange = { variedad = it },
                label = { Text("Variedad", color = colors.onSurface) },
                modifier = Modifier.fillMaxWidth(),
                shape = shapes.medium,

            )

            // Período de inicio
            Text(
                "Periodo de inicio",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = colors.onSurface,
                modifier = Modifier.padding(top = 4.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = periodoInicio == "Verano Andino",
                    onClick = { periodoInicio = "Verano Andino" },
                    colors = RadioButtonDefaults.colors(selectedColor = colors.primary)
                )
                Text("Verano Andino", color = colors.onSurface)
                Spacer(modifier = Modifier.width(12.dp))
                RadioButton(
                    selected = periodoInicio == "Invierno Andino",
                    onClick = { periodoInicio = "Invierno Andino" },
                    colors = RadioButtonDefaults.colors(selectedColor = colors.primary)
                )
                Text("Invierno Andino", color = colors.onSurface)
            }

            // Descripción y Tipo de ganado
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción", color = colors.onSurface) },
                    modifier = Modifier.weight(1f),
                    shape = shapes.medium,

                )
                OutlinedTextField(
                    value = tipoGanado,
                    onValueChange = { tipoGanado = it },
                    label = { Text("Tipo de ganado", color = colors.onSurface) },
                    modifier = Modifier.weight(1f),
                    shape = shapes.medium,

                )
            }

            // Destino
            OutlinedTextField(
                value = destino,
                onValueChange = { destino = it },
                label = { Text("Destino (uso propio o venta)", color = colors.onSurface) },
                modifier = Modifier.fillMaxWidth(),
                shape = shapes.medium,


            )

            // Escoger parcela
            OutlinedTextField(
                value = parcela,
                onValueChange = { parcela = it },
                label = { Text("Escoger parcela", color = colors.onSurface) },
                modifier = Modifier.fillMaxWidth(),
                shape = shapes.medium,

            )

            // Fecha de inicio y fin (misma fila)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = fechaInicio,
                    onValueChange = { fechaInicio = it },
                    label = { Text("Fecha de Inicio", color = colors.onSurface) },
                    modifier = Modifier.weight(1f),
                    shape = shapes.medium,

                )
                OutlinedTextField(
                    value = fechaFin,
                    onValueChange = { fechaFin = it },
                    label = { Text("Fecha de Fin", color = colors.onSurface) },
                    modifier = Modifier.weight(1f),
                    shape = shapes.medium,

                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onConfirmClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colors.primary),
                shape = shapes.medium
            ) {
                Text("Confirmar", fontWeight = FontWeight.Bold, color = colors.onPrimary)
            }
        }
    }
}



@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewNuevoForrajeScreen() {
    JakawiAgroTheme {
        NuevoForrajeScreen()
    }
}
