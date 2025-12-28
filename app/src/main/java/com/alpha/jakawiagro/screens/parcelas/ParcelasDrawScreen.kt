package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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

    // ✅ fluidez
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

    var showDialog by remember { mutableStateOf(false) }
    var nombre by remember { mutableStateOf("") }

    // drawingPoints ya viene como ParcelaPoint(lat,lng)
    val puntosLatLng = remember(ui.drawingPoints) {
        ui.drawingPoints.map { LatLng(it.lat, it.lng) }
    }

    val canSave = puntosLatLng.size >= 3
    val faltan = (3 - puntosLatLng.size).coerceAtLeast(0)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Dibujar parcela",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
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

            // =================== MAPA ===================
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = mapProperties,
                uiSettings = uiSettings,
                onMapClick = { latLng ->
                    // ✅ OPCIÓN A: directo al ViewModel (sin mapper)
                    vm.addPoint(latLng.latitude, latLng.longitude)
                }
            ) {
                // Polígono en edición
                if (puntosLatLng.size >= 3) {
                    Polygon(
                        points = puntosLatLng,
                        fillColor = Color(0x55388E3C),
                        strokeColor = Color(0xFF1B5E20),
                        strokeWidth = 6f
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

            // =================== PANEL INFERIOR (ESTÉTICO) ===================
            Surface(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge,
                tonalElevation = 6.dp,
                shadowElevation = 10.dp
            ) {
                Column(modifier = Modifier.padding(14.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                "Toca el mapa para marcar límites",
                                style = MaterialTheme.typography.titleSmall
                            )
                            Text(
                                "Puntos: ${puntosLatLng.size}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                            IconButton(
                                onClick = {
                                    // ✅ deshacer sin tocar ViewModel:
                                    // quitamos 1 punto llamando clear y reinsertando (no ideal pero funciona)
                                    // MEJOR: crea undoLast en VM, pero tú pediste no tocarlo.
                                    val current = ui.drawingPoints
                                    if (current.isNotEmpty()) {
                                        vm.clearDrawing()
                                        current.dropLast(1).forEach { p ->
                                            vm.addPoint(p.lat, p.lng)
                                        }
                                    }
                                },
                                enabled = puntosLatLng.isNotEmpty()
                            ) {
                                Icon(Icons.Default.Undo, contentDescription = "Deshacer")
                            }

                            IconButton(
                                onClick = { vm.clearDrawing() },
                                enabled = puntosLatLng.isNotEmpty()
                            ) {
                                Icon(Icons.Default.Clear, contentDescription = "Limpiar")
                            }
                        }
                    }

                    Spacer(Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        if (!canSave) {
                            Text(
                                text = "Faltan $faltan punto(s) para guardar",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.error
                            )
                        } else {
                            Text(
                                text = "Listo para guardar ✅",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Button(
                            onClick = { showDialog = true },
                            enabled = canSave
                        ) {
                            Icon(Icons.Default.Save, contentDescription = null)
                            Spacer(Modifier.width(8.dp))
                            Text("Guardar")
                        }
                    }
                }
            }

            // =================== DIALOG GUARDAR ===================
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
                                // ✅ OPCIÓN A: método real del VM
                                vm.saveDrawingAsParcela(nombre.trim())
                                nombre = ""
                                showDialog = false
                            },
                            enabled = canSave
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
