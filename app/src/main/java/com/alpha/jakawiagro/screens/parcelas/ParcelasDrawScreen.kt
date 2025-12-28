package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alpha.jakawiagro.screens.parcelas.toLatLng
import com.alpha.jakawiagro.screens.parcelas.toPoint
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParcelasDrawScreen(
    onBack: () -> Unit
) {
    val vm: ParcelasViewModel = viewModel()
    val ui by vm.uiState.collectAsState()

    val puno = LatLng(-15.8402, -70.0219)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(puno, 18f)
    }

    val mapProperties = remember {
        MapProperties(mapType = MapType.SATELLITE)
    }

    // ✅ gestos fluidos (y sin tilt si no quieres “3D”)
    val uiSettings = remember {
        MapUiSettings(
            zoomControlsEnabled = false,
            compassEnabled = true,
            myLocationButtonEnabled = false,
            scrollGesturesEnabled = true,
            zoomGesturesEnabled = true,
            tiltGesturesEnabled = false
        )
    }

    var showDialog by remember { mutableStateOf(false) }
    var nombre by remember { mutableStateOf("") }

    // ✅ convertir puntos del ViewModel a LatLng para pintar
    val puntosLatLng by remember(ui.drawingPoints) {
        mutableStateOf(ui.drawingPoints.map { it.toLatLng() })
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dibujar parcela") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = { vm.clearDrawing() }) {
                        Icon(Icons.Default.Clear, contentDescription = "Limpiar")
                    }
                }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = mapProperties,
                uiSettings = uiSettings,
                onMapClick = { latLng ->
                    vm.addPoint(latLng.latitude, latLng.longitude)
                }
            ) {

                // Polígono en edición
                if (puntosLatLng.size >= 3) {
                    Polygon(
                        points = puntosLatLng,
                        fillColor = Color(0x55388E3C),
                        strokeColor = Color(0xFF1B5E20),
                        strokeWidth = 5f
                    )
                }

                // Marcadores
                puntosLatLng.forEachIndexed { i, p ->
                    Marker(
                        state = MarkerState(p),
                        title = "Punto ${i + 1}"
                    )
                }
            }

            // Panel inferior
            Card(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(14.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            "Toca el mapa para marcar puntos",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            "Puntos: ${puntosLatLng.size}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                        FilledTonalButton(
                            onClick = { vm.clearDrawing() }
                        ) {
                            Icon(Icons.Default.Clear, contentDescription = null)
                            Spacer(Modifier.width(6.dp))
                            Text("Limpiar")
                        }

                        Button(
                            onClick = { showDialog = true },
                            enabled = puntosLatLng.size >= 3
                        ) {
                            Icon(Icons.Default.Save, contentDescription = null)
                            Spacer(Modifier.width(6.dp))
                            Text("Guardar")
                        }
                    }
                }
            }

            // Dialog guardar
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Guardar parcela") },
                    text = {
                        OutlinedTextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            singleLine = true,
                            placeholder = { Text("Ej: Parcela Norte") }
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                vm.saveDrawingAsParcela(nombre.trim())
                                nombre = ""
                                showDialog = false
                            },
                            enabled = puntosLatLng.size >= 3
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
}
