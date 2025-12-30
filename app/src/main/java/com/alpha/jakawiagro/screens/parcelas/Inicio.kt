package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParcelasInicioScreen(
    userId: String,
    parcelasViewModel: ParcelasViewModel,
    onGoLista: () -> Unit,
    onGoDibujar: () -> Unit,
    onBack: () -> Unit
) {
    val state by parcelasViewModel.uiState.collectAsState()

    LaunchedEffect(userId) {
        if (userId.isNotBlank()) parcelasViewModel.cargarParcelas(userId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Parcelas") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Atrás") } }
            )
        }
    ) { padding ->

        Column(Modifier.fillMaxSize().padding(padding).padding(16.dp)) {

            Text("Total: ${state.parcelas.size}", style = MaterialTheme.typography.titleMedium)

            Spacer(Modifier.height(16.dp))

            Button(onClick = onGoLista, modifier = Modifier.fillMaxWidth().height(48.dp)) {
                Text("Ver Lista")
            }

            Spacer(Modifier.height(10.dp))

            Button(onClick = onGoDibujar, modifier = Modifier.fillMaxWidth().height(48.dp)) {
                Text("Dibujar Nueva Parcela (Mapa)")
            }
        }
    }
}


