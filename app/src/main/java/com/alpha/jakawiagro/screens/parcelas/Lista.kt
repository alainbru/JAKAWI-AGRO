package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.viewmodel.auth.AuthViewModel
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lista(
    authViewModel: AuthViewModel,
    parcelasViewModel: ParcelasViewModel,
    onGoDetalles: () -> Unit,
    onGoEditar: () -> Unit,
    onAdd: () -> Unit,
    onBack: () -> Unit
) {
    val auth = authViewModel.uiState.collectAsState().value
    val state = parcelasViewModel.uiState.collectAsState().value

    LaunchedEffect(auth.userId) {
        val uid = auth.userId ?: return@LaunchedEffect
        parcelasViewModel.cargarParcelas(uid)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de parcelas") },
                navigationIcon = { IconButton(onClick = onBack) { Text("←") } }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) { Text("+") }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            if (state.loading) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())

            if (state.error != null) {
                Spacer(Modifier.height(8.dp))
                Text(state.error, color = MaterialTheme.colorScheme.error)
            }

            Spacer(Modifier.height(12.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(state.parcelas) { p ->
                    Card(onClick = onGoDetalles) {
                        Column(Modifier.padding(12.dp)) {
                            Text(p.nombre, style = MaterialTheme.typography.titleMedium)
                            Text("Puntos: ${p.puntos.size}")
                            Text("Área(ha): ${p.areaHa ?: "-"}")
                            Text("Ubicación: ${p.ubicacion ?: "-"}")
                            Spacer(Modifier.height(8.dp))
                            OutlinedButton(onClick = onGoEditar) { Text("Editar") }
                        }
                    }
                }
            }
        }
    }
}

