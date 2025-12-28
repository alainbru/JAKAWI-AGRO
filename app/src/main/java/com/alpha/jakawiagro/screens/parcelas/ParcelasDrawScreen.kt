package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParcelasDrawScreen(
    vm: ParcelasViewModel,
    onBack: () -> Unit
) {
    val ui by vm.uiState.collectAsState()

    val puno = LatLng(-15.8402, -70.0219)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(puno, 18f)
    }

    val mapProperties = remember { MapProperties(mapType = MapType.SATELLITE) }

    val uiSettings = remember {
        MapUiSettings(
            zoomControlsEnabled = false,
            compassEnabled = true,
            myLocationButtonEnabled = false,
            scrollGesturesEnabled = true,
            zoomGesturesEnabled = true,
            tiltGesturesEnabled = true,
        )
    }

    var showDialog by remember { mutableStateOf(false) }
    var nombre by remember { mutableStateOf("") }

    // convertir ParcelaPoint -> LatLng (sin mapper extra)
    val puntosLatLng = remember(ui.drawingPoints) {
        ui.drawingPoints.map { LatLng(it.lat, it.lng) }
    }

    val canSave = puntosLatLng.size >= 3

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dibujar parcela", maxLines = 1, overflow = TextOverflow.Ellipsis) },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, null) }
                },
                actions = {
                    IconButton(onClick = { vm.clearDrawing() }) {
                        Icon(Icons.Default.Delete, contentDescription = "Limpiar")
                    }
                    IconButton(
                        onClick = { if (canSave) showDialog = true },
                        enabled = canSave
                    ) {
                        Icon(Icons.Default.Save, contentDescription = "Guardar")
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
                    vm.addPoint(latLng.latitude, latLng.longitude) // ✅ correcto
                }
            ) {
                if (puntosLatLng.size >= 3) {
                    Polygon(
                        points = puntosLatLng,
                        fillColor = Color(0x55388E3C),
                        strokeColor = Color(0xFF1B5E20),
                        strokeWidth = 5f
                    )
                }

                puntosLatLng.forEachIndexed { i, p ->
                    Marker(state = MarkerState(p), title = "Punto ${i + 1}")
                }
            }

            // ✅ barra inferior estética (no tapa todo)
            ElevatedCard(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(14.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Toca el mapa para marcar puntos", style = MaterialTheme.typography.titleSmall)
                        Spacer(Modifier.height(4.dp))
                        Text("Puntos: ${puntosLatLng.size}", style = MaterialTheme.typography.bodySmall)
                    }

                    Spacer(Modifier.width(12.dp))

                    FilledTonalButton(onClick = { vm.clearDrawing() }) {
                        Text("Limpiar")
                    }

                    Spacer(Modifier.width(10.dp))

                    Button(
                        onClick = { showDialog = true },
                        enabled = canSave
                    ) {
                        Text("Guardar")
                    }
                }
            }
        }

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
                            vm.saveDrawing(nombre.trim()) // ✅ método alias
                            nombre = ""
                            showDialog = false
                        },
                        enabled = canSave
                    ) { Text("Guardar") }
                },
                dismissButton = {
                    OutlinedButton(onClick = { showDialog = false }) { Text("Cancelar") }
                }
            )
        }
    }
}

