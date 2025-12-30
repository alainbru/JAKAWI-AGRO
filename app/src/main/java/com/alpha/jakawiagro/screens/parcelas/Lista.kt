package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParcelasListaScreen(
    userId: String,
    parcelasViewModel: ParcelasViewModel,
    onGoDetalles: (String) -> Unit,
    onGoEditar: (String) -> Unit,
    onBack: () -> Unit
) {
    val state by parcelasViewModel.uiState.collectAsState()

    LaunchedEffect(userId) {
        if (userId.isNotBlank()) parcelasViewModel.cargarParcelas(userId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Parcelas") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Atrás") } }
            )
        }
    ) { padding ->

        when {
            state.loading -> {
                Box(Modifier.fillMaxSize().padding(padding), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                Box(Modifier.fillMaxSize().padding(padding), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    Text(state.error ?: "", color = MaterialTheme.colorScheme.error)
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(state.parcelas) { p ->
                        Card(
                            modifier = Modifier.fillMaxWidth().clickable { onGoDetalles(p.id) }
                        ) {
                            Column(Modifier.padding(12.dp)) {
                                Text(p.nombre, style = MaterialTheme.typography.titleMedium)
                                Spacer(Modifier.height(6.dp))
                                Text("Puntos: ${p.puntos.size}")

                                Spacer(Modifier.height(10.dp))

                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    OutlinedButton(onClick = { onGoDetalles(p.id) }) { Text("Detalles") }
                                    OutlinedButton(onClick = { onGoEditar(p.id) }) { Text("Editar") }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

