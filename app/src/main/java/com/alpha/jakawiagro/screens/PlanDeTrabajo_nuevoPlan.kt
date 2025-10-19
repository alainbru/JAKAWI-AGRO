package com.alpha.jakawiagro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevoPlanDeTrabajoScreen(
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onConfirmClick: () -> Unit = {}
) {
    var nombre by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var variedad by remember { mutableStateOf("") }
    var periodo by remember { mutableStateOf("Verano Andino") }
    var anioCosecha by remember { mutableStateOf("") }
    var destino by remember { mutableStateOf("") }
    var objetivo by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Nuevo plan de trabajo",
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
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre del Plan de Trabajo") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = tipo,
                onValueChange = { tipo = it },
                label = { Text("Tipo de forraje o cultivo") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = variedad,
                onValueChange = { variedad = it },
                label = { Text("Variedad") },
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Año de cosecha",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = periodo == "Verano Andino",
                    onClick = { periodo = "Verano Andino" }
                )
                Text("Verano Andino")
                Spacer(modifier = Modifier.width(12.dp))
                RadioButton(
                    selected = periodo == "Invierno Andino",
                    onClick = { periodo = "Invierno Andino" }
                )
                Text("Invierno Andino")
            }

            OutlinedTextField(
                value = anioCosecha,
                onValueChange = { anioCosecha = it },
                label = { Text("Año de cosecha") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = destino,
                onValueChange = { destino = it },
                label = { Text("Destino (Consumo o venta)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = objetivo,
                onValueChange = { objetivo = it },
                label = { Text("Objetivo") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onConfirmClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF56C7C1))
            ) {
                Text("Crear plan de trabajo", fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNuevoPlanDeTrabajoScreen() {
    NuevoPlanDeTrabajoScreen()
}
