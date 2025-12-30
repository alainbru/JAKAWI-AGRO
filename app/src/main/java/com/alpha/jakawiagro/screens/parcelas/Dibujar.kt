package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

private val PUNO = LatLng(-15.8402, -70.0219)

@Composable
fun ParcelasDibujarScreen(
    userId: String,
    parcelasViewModel: ParcelasViewModel,
    onBack: () -> Unit,
    onSaved: () -> Unit
) {
    val colors = MaterialTheme.colorScheme
    val state by parcelasViewModel.uiState.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var puntos by remember { mutableStateOf(listOf<LatLng>()) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(PUNO, 14f)
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
            Column(Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = colors.onPrimaryContainer)
                    }
                    Spacer(Modifier.width(6.dp))
                    Icon(Icons.Default.Spa, contentDescription = null, tint = colors.onPrimaryContainer)
                    Spacer(Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "DIBUJAR PARCELA",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = colors.onPrimaryContainer
                        )
                        Text(
                            text = "Marca los puntos del terreno en el mapa",
                            style = MaterialTheme.typography.bodySmall,
                            color = colors.onPrimaryContainer.copy(alpha = 0.9f)
                        )
                    }
                }
            }
        }

        if (state.loading) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        if (state.error != null) {
            AssistChip(onClick = { parcelasViewModel.clearError() }, label = { Text(state.error ?: "Error") })
        }

        // Form
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = colors.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre de la parcela") },
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

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedButton(
                        onClick = { if (puntos.isNotEmpty()) puntos = puntos.dropLast(1) },
                        enabled = puntos.isNotEmpty() && !state.loading,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Icon(Icons.Default.Undo, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Deshacer")
                    }
                    OutlinedButton(
                        onClick = { puntos = emptyList() },
                        enabled = puntos.isNotEmpty() && !state.loading,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Icon(Icons.Default.DeleteSweep, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Limpiar")
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    AssistChip(onClick = {}, label = { Text("Puntos: ${puntos.size}") })
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "Inicio: Puno",
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.onSurfaceVariant
                    )
                }
            }
        }

        // Map SATELLITE en card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(containerColor = colors.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(mapType = MapType.SATELLITE),
                uiSettings = MapUiSettings(zoomControlsEnabled = false),
                onMapClick = { latLng ->
                    puntos = puntos + latLng
                }
            ) {
                puntos.forEachIndexed { index, p ->
                    Marker(
                        state = MarkerState(position = p),
                        title = "Punto ${index + 1}"
                    )
                }
                if (puntos.size >= 3) {
                    Polygon(
                        points = puntos,
                        fillColor = colors.primary.copy(alpha = 0.18f),
                        strokeColor = colors.primary,
                        strokeWidth = 6f
                    )
                }
            }
        }

        // Guardar (misma lógica)
        Button(
            onClick = {
                val nombreOk = nombre.trim().ifBlank { "Mi parcela" }
                parcelasViewModel.crearParcela(
                    ownerId = userId,
                    nombre = nombreOk,
                    puntosLatLng = puntos,
                    areaHa = null,
                    ubicacion = ubicacion.trim().ifBlank { null }
                )
                onSaved()
            },
            enabled = userId.isNotBlank() && puntos.size >= 3 && !state.loading,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(18.dp)
        ) {
            Icon(Icons.Default.Save, contentDescription = null)
            Spacer(Modifier.width(10.dp))
            Text(if (state.loading) "Guardando..." else "Guardar parcela")
        }
    }
}


