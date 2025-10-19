package com.alpha.jakawiagro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevaCosechaScreen(
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onConfirmClick: () -> Unit = {}
) {
    var cantidad by remember { mutableStateOf("") }
    var unidad by remember { mutableStateOf("") }
    var tipoCultivo by remember { mutableStateOf("") }
    var variedad by remember { mutableStateOf("") }
    var periodoInicio by remember { mutableStateOf("Verano Andino") }
    var descripcion by remember { mutableStateOf("") }
    var destino by remember { mutableStateOf("") }
    var parcela by remember { mutableStateOf("") }
    var fechaInicio by remember { mutableStateOf("") }
    var fechaFin by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Nueva Cosecha",
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
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = cantidad,
                    onValueChange = { cantidad = it },
                    label = { Text("Cantidad") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = unidad,
                    onValueChange = { unidad = it },
                    label = { Text("Unidad") },
                    modifier = Modifier.weight(1f)
                )
            }

            OutlinedTextField(
                value = tipoCultivo,
                onValueChange = { tipoCultivo = it },
                label = { Text("Tipo de cultivo") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = variedad,
                onValueChange = { variedad = it },
                label = { Text("Variedad") },
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Periodo de inicio",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = periodoInicio == "Verano Andino",
                    onClick = { periodoInicio = "Verano Andino" }
                )
                Text("Verano Andino")
                Spacer(modifier = Modifier.width(12.dp))
                RadioButton(
                    selected = periodoInicio == "Invierno Andino",
                    onClick = { periodoInicio = "Invierno Andino" }
                )
                Text("Invierno Andino")
            }

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = destino,
                onValueChange = { destino = it },
                label = { Text("Destino (Consumo o venta)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = parcela,
                onValueChange = { parcela = it },
                label = { Text("Escoger parcela") },
                modifier = Modifier.fillMaxWidth()
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = fechaInicio,
                    onValueChange = { fechaInicio = it },
                    label = { Text("Fecha de inicio") },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = fechaFin,
                    onValueChange = { fechaFin = it },
                    label = { Text("Fecha de fin") },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onConfirmClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF56C7C1))
            ) {
                Text("Confirmar", fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNuevaCosechaScreen() {
    NuevaCosechaScreen()
}

