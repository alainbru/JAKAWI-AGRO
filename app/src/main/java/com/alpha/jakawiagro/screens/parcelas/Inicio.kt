package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Forest
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

@Composable
fun ParcelasInicioScreen(
    onGoLista: () -> Unit,
    onGoDibujar: () -> Unit,
    onBack: () -> Unit,
    userId: String,
    parcelasViewModel: ParcelasViewModel
) {
    val colors = MaterialTheme.colorScheme
    val state by parcelasViewModel.uiState.collectAsState()

    var nombre by remember { mutableStateOf("USUARIO") }
    var loadingName by remember { mutableStateOf(true) }

    // Cargar nombre usuario (misma lógica)
    LaunchedEffect(userId) {
        if (userId.isBlank()) return@LaunchedEffect
        loadingName = true
        try {
            val snap = FirebaseFirestore.getInstance()
                .collection("users")
                .document(userId)
                .get()
                .await()
            nombre = (snap.getString("nombreCompleto") ?: "USUARIO").ifBlank { "USUARIO" }
        } catch (_: Exception) {
            nombre = "USUARIO"
        } finally {
            loadingName = false
        }
    }

    // Cargar parcelas (misma lógica)
    LaunchedEffect(userId) {
        if (userId.isNotBlank()) parcelasViewModel.cargarParcelas(userId)
    }

    val bg = Brush.verticalGradient(
        listOf(
            colors.background,
            colors.primary.copy(alpha = 0.05f),
            colors.background
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Header campo
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(containerColor = colors.primaryContainer),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = colors.onPrimaryContainer
                        )
                    }
                    Spacer(Modifier.width(6.dp))
                    Icon(
                        Icons.Default.Forest,
                        contentDescription = null,
                        tint = colors.onPrimaryContainer
                    )
                    Spacer(Modifier.width(10.dp))
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = "PARCELAS",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = colors.onPrimaryContainer
                        )
                        Text(
                            text = "Gestión agrícola del terreno",
                            style = MaterialTheme.typography.bodySmall,
                            color = colors.onPrimaryContainer.copy(alpha = 0.9f)
                        )
                    }
                }

                Spacer(Modifier.height(10.dp))

                Text(
                    text = if (loadingName) "Hola..." else "Hola, ${nombre.trim().uppercase()} 🌿",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = colors.onPrimaryContainer
                )

                Spacer(Modifier.height(6.dp))

                val total = state.parcelas.size
                Text(
                    text = "Tienes $total parcela${if (total == 1) "" else "s"} registradas.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.onPrimaryContainer.copy(alpha = 0.92f)
                )
            }
        }

        if (state.loading) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        if (state.error != null) {
            AssistChip(
                onClick = { parcelasViewModel.clearError() },
                label = { Text(state.error ?: "Error") }
            )
        }

        // Acciones
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = colors.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocalFlorist, contentDescription = null, tint = colors.primary)
                    Spacer(Modifier.width(10.dp))
                    Text(
                        "Acciones rápidas",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                FilledTonalButton(
                    onClick = onGoLista,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Icon(Icons.Default.ViewList, contentDescription = null)
                    Spacer(Modifier.width(10.dp))
                    Text("Ver mis parcelas")
                }

                Button(
                    onClick = onGoDibujar,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Icon(Icons.Default.AddLocationAlt, contentDescription = null)
                    Spacer(Modifier.width(10.dp))
                    Text("Dibujar nueva parcela")
                }
            }
        }

        // Tip campo
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = colors.surfaceVariant.copy(alpha = 0.35f))
        ) {
            Row(
                modifier = Modifier.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Map, contentDescription = null, tint = colors.primary)
                Spacer(Modifier.width(10.dp))
                Text(
                    text = "Tip: toca el mapa para marcar puntos del terreno. Deshacer y Limpiar te ayudan a corregir.",
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.onSurfaceVariant
                )
            }
        }
    }
}


