package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParcelaEditScreen(
    parcelaId: String,
    onBack: () -> Unit,
    vm: ParcelasViewModel
) {
    val ui by vm.uiState.collectAsState()

    LaunchedEffect(parcelaId) { vm.selectParcela(parcelaId) }

    val parcela = ui.selected

    var nombre by remember(parcela?.nombre) { mutableStateOf(parcela?.nombre ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar parcela") },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, null) }
                },
                actions = {
                    IconButton(
                        onClick = {
                            if (parcela != null) {
                                vm.updateParcelaName(parcela.id, nombre.trim())
                                onBack()
                            }
                        },
                        enabled = parcela != null && nombre.trim().isNotEmpty()
                    ) { Icon(Icons.Default.Save, contentDescription = "Guardar") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (ui.isLoading) {
                CircularProgressIndicator()
                return@Scaffold
            }

            if (parcela == null) {
                Text("No se encontró la parcela.", color = MaterialTheme.colorScheme.error)
                return@Scaffold
            }

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre de parcela") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Text("Puntos: ${parcela.puntos.size}", style = MaterialTheme.typography.bodySmall)

            ui.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        }
    }
}
