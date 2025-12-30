package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Inicio(
    onGoLista: () -> Unit,
    onGoDibujar: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Parcelas") },
                navigationIcon = { IconButton(onClick = onBack) { Text("←") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = onGoLista, modifier = Modifier.fillMaxWidth()) {
                Text("Ver mis parcelas")
            }
            Spacer(Modifier.height(12.dp))
            Button(onClick = onGoDibujar, modifier = Modifier.fillMaxWidth()) {
                Text("Dibujar nueva parcela")
            }
        }
    }
}


