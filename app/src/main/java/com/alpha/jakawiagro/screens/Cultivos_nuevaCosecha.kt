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
import com.alpha.jakawiagro.ui.theme.JakawiAgroTheme

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
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            MainTopAppBar(
                title = "Nueva Cosecha"
                ,   onMenuClick = onMenuClick,
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = cantidad,
                    onValueChange = { cantidad = it },
                    label = { Text("Cantidad") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = MaterialTheme.shapes.medium,
                    colors = outlinedColors()
                )
                OutlinedTextField(
                    value = unidad,
                    onValueChange = { unidad = it },
                    label = { Text("Unidad") },
                    modifier = Modifier.weight(1f),
                    shape = MaterialTheme.shapes.medium,
                    colors = outlinedColors()
                )
            }

            OutlinedTextField(
                value = tipoCultivo,
                onValueChange = { tipoCultivo = it },
                label = { Text("Tipo de cultivo") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = outlinedColors()
            )

            OutlinedTextField(
                value = variedad,
                onValueChange = { variedad = it },
                label = { Text("Variedad") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = outlinedColors()
            )

            Text(
                text = "Periodo de inicio",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = 4.dp)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = periodoInicio == "Verano Andino",
                    onClick = { periodoInicio = "Verano Andino" },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary
                    )
                )
                Text("Verano Andino", color = MaterialTheme.colorScheme.onBackground)

                Spacer(modifier = Modifier.width(12.dp))

                RadioButton(
                    selected = periodoInicio == "Invierno Andino",
                    onClick = { periodoInicio = "Invierno Andino" },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary
                    )
                )
                Text("Invierno Andino", color = MaterialTheme.colorScheme.onBackground)
            }

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = outlinedColors()
            )

            OutlinedTextField(
                value = destino,
                onValueChange = { destino = it },
                label = { Text("Destino (Consumo o venta)") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = outlinedColors()
            )

            OutlinedTextField(
                value = parcela,
                onValueChange = { parcela = it },
                label = { Text("Escoger parcela") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = outlinedColors()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = fechaInicio,
                    onValueChange = { fechaInicio = it },
                    label = { Text("Fecha de inicio") },
                    modifier = Modifier.weight(1f),
                    shape = MaterialTheme.shapes.medium,
                    colors = outlinedColors()
                )
                OutlinedTextField(
                    value = fechaFin,
                    onValueChange = { fechaFin = it },
                    label = { Text("Fecha de fin") },
                    modifier = Modifier.weight(1f),
                    shape = MaterialTheme.shapes.medium,
                    colors = outlinedColors()
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onConfirmClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Confirmar", fontWeight = FontWeight.Bold)
            }
        }
    }
}

/* 🎨 Colores unificados para OutlinedTextField */
@Composable
private fun outlinedColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = MaterialTheme.colorScheme.primary,
    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
    focusedLabelColor = MaterialTheme.colorScheme.primary,
    cursorColor = MaterialTheme.colorScheme.primary,
    focusedTextColor = MaterialTheme.colorScheme.onSurface,
    unfocusedTextColor = MaterialTheme.colorScheme.onSurface
)





@Preview(uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewNuevaCosechaScreen() {
    JakawiAgroTheme {
        NuevaCosechaScreen()
    }
}

