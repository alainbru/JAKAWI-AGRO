package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParcelasListScreen(
    onBack: () -> Unit,
    onOpenDetail: (String) -> Unit,
    vm: ParcelasViewModel
) {
    val ui by vm.uiState.collectAsState()

    LaunchedEffect(Unit) { vm.loadParcelas() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis parcelas") },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, null) }
                }
            )
        }
    ) { padding ->

        if (ui.isLoading) {
            Box(Modifier.fillMaxSize().padding(padding)) {
                CircularProgressIndicator(modifier = Modifier.padding(24.dp))
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            if (ui.parcelas.isEmpty()) {
                Text("Aún no tienes parcelas guardadas.", style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(8.dp))
                Text("Ve a “Dibujar parcela” para crear una.", style = MaterialTheme.typography.bodySmall)
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(ui.parcelas) { p ->
                        ElevatedCard(
                            onClick = { onOpenDetail(p.id) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(p.nombre, style = MaterialTheme.typography.titleMedium)
                                    Text("Puntos: ${p.puntos.size}", style = MaterialTheme.typography.bodySmall)
                                }
                                Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
                            }
                        }
                    }
                }
            }

            ui.error?.let {
                Spacer(Modifier.height(12.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
