package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParcelaDetailScreen(
    parcelaId: String,
    onEdit: (String) -> Unit,
    onBack: () -> Unit
) {
    val vm: ParcelasViewModel = viewModel()
    val ui by vm.uiState.collectAsState()

    LaunchedEffect(parcelaId) { vm.selectParcela(parcelaId) }

    val parcela = ui.selected

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de parcela") },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, null) }
                },
                actions = {
                    if (parcela != null) {
                        IconButton(onClick = { onEdit(parcela.id) }) {
                            Icon(Icons.Default.Edit, contentDescription = "Editar")
                        }
                        IconButton(onClick = {
                            vm.deleteParcela(parcela.id)
                            onBack()
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                        }
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (ui.isLoading) {
                CircularProgressIndicator()
                return@Scaffold
            }

            if (parcela == null) {
                Text("No se encontró la parcela.", color = MaterialTheme.colorScheme.error)
                return@Scaffold
            }

            Text(parcela.nombre, style = MaterialTheme.typography.headlineSmall)
            Text("ID: ${parcela.id}", style = MaterialTheme.typography.bodySmall)
            Text("Puntos: ${parcela.puntos.size}", style = MaterialTheme.typography.bodyMedium)

            // Mini mapa
            val pointsLatLng = parcela.puntos.map { it.toLatLng() }
            val center = pointsLatLng.firstOrNull()
            if (center != null) {
                val cam = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(center, 18f)
                }

                Card {
                    GoogleMap(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp),
                        cameraPositionState = cam,
                        properties = MapProperties(mapType = MapType.SATELLITE),
                        uiSettings = MapUiSettings(
                            zoomControlsEnabled = false,
                            scrollGesturesEnabled = false,
                            zoomGesturesEnabled = false,
                            tiltGesturesEnabled = false
                        )
                    ) {
                        if (pointsLatLng.size >= 3) {
                            Polygon(
                                points = pointsLatLng,
                                fillColor = Color(0x55388E3C),
                                strokeColor = Color(0xFF1B5E20),
                                strokeWidth = 5f
                            )
                        }
                    }
                }
            }
        }
    }
}

