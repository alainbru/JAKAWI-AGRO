package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel

@Composable
fun ParcelasListaScreen(
    userId: String,
    parcelasViewModel: ParcelasViewModel,
    onGoDetalles: (String) -> Unit,
    onGoEditar: (String) -> Unit,
    onBack: () -> Unit
) {
    val colors = MaterialTheme.colorScheme
    val state by parcelasViewModel.uiState.collectAsState()

    var toDeleteId by remember { mutableStateOf<String?>(null) }
    var toDeleteName by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(userId) {
        if (userId.isNotBlank()) parcelasViewModel.cargarParcelas(userId)
    }

    val bg = Brush.verticalGradient(
        listOf(
            colors.background,
            colors.primary.copy(alpha = 0.04f),
            colors.background
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Header campo
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(containerColor = colors.primaryContainer),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = colors.onPrimaryContainer)
                }
                Spacer(Modifier.width(6.dp))
                Icon(Icons.Default.Nature, contentDescription = null, tint = colors.onPrimaryContainer)
                Spacer(Modifier.width(10.dp))
                Column {
                    Text(
                        text = "MIS PARCELAS",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = colors.onPrimaryContainer
                    )
                    Text(
                        text = "Edita o elimina parcelas de tu terreno",
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.onPrimaryContainer.copy(alpha = 0.9f)
                    )
                }
            }
        }

        if (state.loading) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        if (state.error != null) {
            AssistChip(onClick = { parcelasViewModel.clearError() }, label = { Text(state.error ?: "Error") })
        }

        // Estado vacío bonito
        if (state.parcelas.isEmpty() && !state.loading) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = colors.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.Map,
                        contentDescription = null,
                        modifier = Modifier.size(72.dp),
                        tint = colors.primary.copy(alpha = 0.55f)
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "Aún no tienes parcelas",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Vuelve atrás y dibuja tu primera parcela en el mapa.",
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.onSurfaceVariant
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.parcelas, key = { it.id }) { p ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = colors.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    onClick = { onGoDetalles(p.id) }
                ) {
                    Column(Modifier.padding(14.dp)) {

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Terrain,
                                contentDescription = null,
                                tint = colors.primary,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(Modifier.width(10.dp))
                            Column(Modifier.weight(1f)) {
                                Text(p.nombre, fontWeight = FontWeight.ExtraBold)
                                Spacer(Modifier.height(4.dp))
                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    AssistChip(onClick = {}, label = { Text("${p.puntos.size} puntos") })
                                    if (!p.ubicacion.isNullOrBlank()) {
                                        AssistChip(onClick = {}, label = { Text(p.ubicacion!!) })
                                    }
                                }
                            }
                        }

                        Spacer(Modifier.height(10.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            OutlinedButton(
                                onClick = { onGoEditar(p.id) },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(50.dp),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Icon(Icons.Default.Edit, contentDescription = null)
                                Spacer(Modifier.width(8.dp))
                                Text("Editar")
                            }

                            OutlinedButton(
                                onClick = {
                                    toDeleteId = p.id
                                    toDeleteName = p.nombre
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(50.dp),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = colors.error),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Icon(Icons.Default.Delete, contentDescription = null)
                                Spacer(Modifier.width(8.dp))
                                Text("Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }

    // Confirmación eliminar (misma lógica)
    if (toDeleteId != null) {
        AlertDialog(
            onDismissRequest = { toDeleteId = null; toDeleteName = null },
            title = { Text("Eliminar parcela", fontWeight = FontWeight.ExtraBold) },
            text = { Text("¿Seguro que deseas eliminar “${toDeleteName ?: ""}”? Esta acción no se puede deshacer.") },
            confirmButton = {
                Button(
                    onClick = {
                        val id = toDeleteId ?: return@Button
                        parcelasViewModel.eliminarParcela(id = id, ownerId = userId)
                        toDeleteId = null
                        toDeleteName = null
                    }
                ) { Text("Eliminar") }
            },
            dismissButton = {
                TextButton(onClick = { toDeleteId = null; toDeleteName = null }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
