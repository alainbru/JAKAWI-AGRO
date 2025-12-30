package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.data.model.Parcela
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

@Composable
fun ParcelasEditarScreen(
    parcelaId: String,
    parcelasViewModel: ParcelasViewModel,
    onBack: () -> Unit,
    onSaved: () -> Unit
) {
    val colors = MaterialTheme.colorScheme
    val vmState by parcelasViewModel.uiState.collectAsState()

    var loading by remember { mutableStateOf(true) }
    var parcela by remember { mutableStateOf<Parcela?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }

    LaunchedEffect(parcelaId) {
        loading = true
        error = null
        try {
            val snap = FirebaseFirestore.getInstance()
                .collection("parcelas")
                .document(parcelaId)
                .get()
                .await()
            val p = snap.toObject(Parcela::class.java)
            parcela = p
            nombre = p?.nombre ?: ""
            ubicacion = p?.ubicacion ?: ""
        } catch (e: Exception) {
            error = e.message ?: "Error al cargar parcela"
        } finally {
            loading = false
        }
    }

    if (loading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val p = parcela
    if (p == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(error ?: "No se encontró la parcela")
        }
        return
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
            Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = colors.onPrimaryContainer)
                }
                Spacer(Modifier.width(6.dp))
                Icon(Icons.Default.Spa, contentDescription = null, tint = colors.onPrimaryContainer)
                Spacer(Modifier.width(10.dp))
                Column {
                    Text("EDITAR PARCELA", fontWeight = FontWeight.ExtraBold, color = colors.onPrimaryContainer)
                    Text(
                        text = p.nombre,
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.onPrimaryContainer.copy(alpha = 0.9f)
                    )
                }
            }
        }

        if (vmState.loading) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        if (vmState.error != null) {
            AssistChip(onClick = { parcelasViewModel.clearError() }, label = { Text(vmState.error ?: "Error") })
        }

        // Form
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = colors.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    leadingIcon = { Icon(Icons.Default.Badge, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                OutlinedTextField(
                    value = ubicacion,
                    onValueChange = { ubicacion = it },
                    label = { Text("Ubicación (opcional)") },
                    leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Text(
                    text = "Consejo: usa un nombre corto y una ubicación clara (ej: “Puno – Sector A”).",
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.onSurfaceVariant
                )
            }
        }

        Button(
            onClick = {
                val updated = p.copy(
                    nombre = nombre.trim().ifBlank { p.nombre },
                    ubicacion = ubicacion.trim().ifBlank { null }
                )
                parcelasViewModel.actualizarParcela(updated)
                onSaved()
            },
            enabled = !vmState.loading,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(18.dp)
        ) {
            Icon(Icons.Default.Save, contentDescription = null)
            Spacer(Modifier.width(10.dp))
            Text(if (vmState.loading) "Guardando..." else "Guardar cambios")
        }
    }
}
