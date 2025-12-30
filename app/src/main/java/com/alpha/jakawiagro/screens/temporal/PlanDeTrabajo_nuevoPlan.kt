package com.alpha.jakawiagro.screens.temporal

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.ui.theme.JakawiAgroTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevoPlanDeTrabajoScreen(
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onConfirmClick: () -> Unit = {}
) {
    val colorScheme = MaterialTheme.colorScheme

    var nombre by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var variedad by remember { mutableStateOf("") }
    var periodo by remember { mutableStateOf("Verano Andino") }
    var anioCosecha by remember { mutableStateOf("") }
    var destino by remember { mutableStateOf("") }
    var objetivo by remember { mutableStateOf("") }

    Scaffold(
        containerColor = colorScheme.background,
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
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.primary,
                    focusedLabelColor = colorScheme.primary,
                    cursorColor = colorScheme.primary
                )
            )

            OutlinedTextField(
                value = tipo,
                onValueChange = { tipo = it },
                label = { Text("Tipo de forraje o cultivo") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.primary,
                    focusedLabelColor = colorScheme.primary
                )
            )

            OutlinedTextField(
                value = variedad,
                onValueChange = { variedad = it },
                label = { Text("Variedad") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.primary,
                    focusedLabelColor = colorScheme.primary
                )
            )

            Text(
                text = "Periodo de cosecha",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = colorScheme.onSurface
            )

            Row(verticalAlignment = Alignment.CenterVertically) {

                RadioButton(
                    selected = periodo == "Verano Andino",
                    onClick = { periodo = "Verano Andino" },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = colorScheme.primary
                    )
                )
                Text(
                    text = "Verano Andino",
                    color = colorScheme.onSurface
                )

                Spacer(modifier = Modifier.width(16.dp))

                RadioButton(
                    selected = periodo == "Invierno Andino",
                    onClick = { periodo = "Invierno Andino" },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = colorScheme.primary
                    )
                )
                Text(
                    text = "Invierno Andino",
                    color = colorScheme.onSurface
                )
            }

            OutlinedTextField(
                value = anioCosecha,
                onValueChange = { anioCosecha = it },
                label = { Text("Año de cosecha") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.primary,
                    focusedLabelColor = colorScheme.primary
                )
            )

            OutlinedTextField(
                value = destino,
                onValueChange = { destino = it },
                label = { Text("Destino (Consumo o venta)") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.primary,
                    focusedLabelColor = colorScheme.primary
                )
            )

            OutlinedTextField(
                value = objetivo,
                onValueChange = { objetivo = it },
                label = { Text("Objetivo") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.primary,
                    focusedLabelColor = colorScheme.primary
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onConfirmClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.primary,
                    contentColor = colorScheme.onPrimary
                )
            ) {
                Text(
                    text = "Crear plan de trabajo",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewNuevoPlan() {
    JakawiAgroTheme {
        NuevoPlanDeTrabajoScreen()
    }
}
