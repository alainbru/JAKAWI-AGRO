package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.data.model.Parcela
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParcelasEditarScreen(
    parcelaId: String,
    parcelasViewModel: ParcelasViewModel,
    onBack: () -> Unit,
    onSaved: () -> Unit
) {
    val state by parcelasViewModel.uiState.collectAsState()

    // Busca la parcela en el state actual (debe haber sido cargada antes desde Lista/Inicio)
    val parcela = state.parcelas.firstOrNull { it.id == parcelaId }

    // Si no existe aún (ej. entras directo sin cargar), muestra mensaje
    if (parcela == null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Editar Parcela") },
                    navigationIcon = { TextButton(onClick = onBack) { Text("Atrás") } }
                )
            }
        ) { p ->
            Box(
                modifier = Modifier.fillMaxSize().padding(p),
                contentAlignment = Alignment.Center
            ) {
                Text("Parcela no encontrada (carga la lista primero).")
            }
        }
        return
    }

    // Estados editables
    var nombre by remember { mutableStateOf(parcela.nombre) }
    var ubicacion by remember { mutableStateOf(parcela.ubicacion ?: "") }
    var area by remember { mutableStateOf(parcela.areaHa?.toString() ?: "") }

    var submitted by remember { mutableStateOf(false) }

    // Navega solo cuando guardó OK (sin error) y terminó loading
    LaunchedEffect(state.loading, state.error) {
        if (submitted && !state.loading) {
            if (state.error == null) onSaved()
            submitted = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Parcela") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Atrás") } }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = ubicacion,
                onValueChange = { ubicacion = it },
                label = { Text("Ubicación") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = area,
                onValueChange = { area = it },
                label = { Text("Área (ha)") },
                modifier = Modifier.fillMaxWidth()
            )

            if (state.error != null) {
                Spacer(Modifier.height(10.dp))
                Text(
                    text = state.error ?: "",
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(Modifier.height(18.dp))

            Button(
                onClick = {
                    submitted = true

                    // ✅ ENVIAMOS LA PARCELA COMPLETA (copy con cambios)
                    val parcelaActualizada: Parcela = parcela.copy(
                        nombre = nombre.trim(),
                        ubicacion = ubicacion.trim().ifBlank { null },
                        areaHa = area.toDoubleOrNull()
                        // NO tocamos puntos, ownerId, createdAt, etc. -> se conservan
                    )

                    // ✅ Esta es la firma que tu VM espera: actualizarParcela(parcela = ...)
                    parcelasViewModel.actualizarParcela(parcela = parcelaActualizada)
                },
                enabled = nombre.isNotBlank() && !state.loading,
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                if (state.loading) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(18.dp)
                    )
                } else {
                    Text("GUARDAR CAMBIOS")
                }
            }
        }
    }
}
