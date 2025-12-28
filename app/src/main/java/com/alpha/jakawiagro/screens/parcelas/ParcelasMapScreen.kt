@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

/* ===================== DATA ===================== */
data class Parcela(
    val nombre: String,
    val puntos: List<LatLng>
)

/* ===================== SCREEN ===================== */
@Composable
fun ParcelasMapScreen() {

    /* ---------- Estado ---------- */
    val parcelas = remember { mutableStateListOf<Parcela>() }
    val puntosActuales = remember { mutableStateListOf<LatLng>() }

    var showDialog by remember { mutableStateOf(false) }
    var nombreParcela by remember { mutableStateOf("") }
    var showList by remember { mutableStateOf(false) }

    val puno = LatLng(-15.8402, -70.0219)

    // Cámara estable → más fluidez
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(puno, 18f)
    }

    val mapProperties = remember {
        MapProperties(mapType = MapType.SATELLITE)
    }

    val uiSettings = remember {
        MapUiSettings(
            zoomControlsEnabled = false,
            compassEnabled = true,
            myLocationButtonEnabled = false,
            scrollGesturesEnabled = true,
            zoomGesturesEnabled = true,
            tiltGesturesEnabled = true
        )
    }


    /* ===================== UI ===================== */
    Box(Modifier.fillMaxSize()) {

        /* ---------- MAPA ---------- */
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = uiSettings,
            onMapClick = { puntosActuales.add(it) }
        ) {

            // Parcelas guardadas
            parcelas.forEach { parcela ->
                if (parcela.puntos.size >= 3) {
                    Polygon(
                        points = parcela.puntos,
                        fillColor = Color(0x552E7D32),
                        strokeColor = Color(0xFF1B5E20),
                        strokeWidth = 4f
                    )
                }
            }

            // Parcela en edición
            if (puntosActuales.size >= 3) {
                Polygon(
                    points = puntosActuales.toList(),
                    fillColor = Color(0x5581C784),
                    strokeColor = Color(0xFF388E3C),
                    strokeWidth = 4f
                )
            }

            // Marcadores de edición
            puntosActuales.forEachIndexed { i, punto ->
                Marker(
                    state = MarkerState(position = punto),
                    title = "Punto ${i + 1}"
                )
            }
        }

        /* ---------- FABs ---------- */
        val canSave = puntosActuales.size >= 3

        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            FloatingActionButton(
                onClick = { showList = !showList },
                containerColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(Icons.Default.List, contentDescription = "Ver parcelas")
            }

            FloatingActionButton(
                onClick = { puntosActuales.clear() },
                containerColor = MaterialTheme.colorScheme.error
            ) {
                Icon(Icons.Default.Clear, contentDescription = "Limpiar")
            }

            FloatingActionButton(
                onClick = {
                    if (!canSave) return@FloatingActionButton
                    nombreParcela = ""
                    showDialog = true
                },
                containerColor = if (canSave) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.surfaceVariant,
                contentColor = if (canSave) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.onSurfaceVariant
            ) {
                Icon(Icons.Default.Save, contentDescription = "Guardar")
            }
        } // ✅ CIERRE DEL Column (aquí estaba tu error)

        /* ---------- LISTA DE PARCELAS ---------- */
        if (showList && parcelas.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
                    .width(260.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text("Mis parcelas", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(8.dp))

                    LazyColumn {
                        items(parcelas) { parcela ->
                            TextButton(
                                onClick = {
                                    val target = parcela.puntos.firstOrNull() ?: return@TextButton
                                    cameraPositionState.position =
                                        CameraPosition.fromLatLngZoom(target, 19f)
                                    showList = false
                                }
                            ) {
                                Text(parcela.nombre)
                            }
                        }
                    }
                }
            }
        }

        /* ---------- DIALOG ---------- */
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Guardar parcela") },
                text = {
                    OutlinedTextField(
                        value = nombreParcela,
                        onValueChange = { nombreParcela = it },
                        placeholder = { Text("Ej: Parcela Norte") },
                        singleLine = true
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            parcelas.add(
                                Parcela(
                                    nombre = nombreParcela.trim().ifBlank {
                                        "Parcela ${parcelas.size + 1}"
                                    },
                                    puntos = puntosActuales.toList()
                                )
                            )
                            puntosActuales.clear()
                            showDialog = false
                            // 🔜 aquí luego conectas Oracle
                        }
                    ) { Text("Guardar") }
                },
                dismissButton = {
                    OutlinedButton(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}


