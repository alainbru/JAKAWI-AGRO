package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParcelasDetallesScreen(
    parcelaId: String,
    parcelasViewModel: ParcelasViewModel,
    onBack: () -> Unit
) {
    val state by parcelasViewModel.uiState.collectAsState()
    val parcela = state.parcelas.firstOrNull { it.id == parcelaId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle Parcela") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Atrás") } }
            )
        }
    ) { padding ->

        if (parcela == null) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("Parcela no encontrada")
            }
            return@Scaffold
        }

        Column(Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Text(parcela.nombre, style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(10.dp))
            Text("ID: ${parcela.id}")
            Text("Puntos: ${parcela.puntos.size}")
            Spacer(Modifier.height(10.dp))
            Text("Ubicación: ${parcela.ubicacion ?: "-"}")
            Text("Área (ha): ${parcela.areaHa ?: "-"}")
        }
    }
}


