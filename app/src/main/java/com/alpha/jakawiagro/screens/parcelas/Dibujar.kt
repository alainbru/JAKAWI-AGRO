package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParcelasDibujarScreen(
    userId: String,
    parcelasViewModel: ParcelasViewModel,
    onBack: () -> Unit,
    onSaved: () -> Unit
) {
    val state by parcelasViewModel.uiState.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var puntos by remember { mutableStateOf(listOf<LatLng>()) }

    var submitted by remember { mutableStateOf(false) }

    // cámara
    val cameraPositionState = rememberCameraPositionState()

    // satélite
    val props = remember {
        MapProperties(mapType = MapType.SATELLITE)
    }
    val ui = remember {
        MapUiSettings(
            zoomControlsEnabled = true,
            myLocationButtonEnabled = false
        )
    }

    // navegar solo si guardó bien
    LaunchedEffect(state.loading, state.error) {
        if (submitted && !state.loading) {
            if (state.error == null) onSaved()
            submitted = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dibujar Parcela") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Atrás") } }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier.fillMaxSize().padding(padding)
        ) {

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre de la parcela") },
                modifier = Modifier.fillMaxWidth().padding(12.dp)
            )

            // Mapa
            Box(Modifier.weight(1f).fillMaxWidth()) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    properties = props,
                    uiSettings = ui,
                    cameraPositionState = cameraPositionState,
                    onMapClick = { latLng ->
                        puntos = puntos + latLng
                    }
                ) {

                    // marcadores
                    puntos.forEachIndexed { idx, p ->
                        Marker(
                            state = MarkerState(position = p),
                            title = "Punto ${idx + 1}"
                        )
                    }

                    // línea
                    if (puntos.size >= 2) {
                        Polyline(points = puntos)
                    }

                    // polígono (cerrado)
                    if (puntos.size >= 3) {
                        Polygon(points = puntos)
                    }
                }
            }

            // acciones
            Column(Modifier.fillMaxWidth().padding(12.dp)) {

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    OutlinedButton(
                        onClick = { puntos = emptyList() },
                        enabled = puntos.isNotEmpty() && !state.loading
                    ) { Text("Limpiar") }

                    OutlinedButton(
                        onClick = { if (puntos.isNotEmpty()) puntos = puntos.dropLast(1) },
                        enabled = puntos.isNotEmpty() && !state.loading
                    ) { Text("Deshacer") }
                }

                Spacer(Modifier.height(10.dp))

                Button(
                    onClick = {
                        if (userId.isNotBlank() && nombre.isNotBlank() && puntos.size >= 3) {
                            submitted = true
                            parcelasViewModel.crearParcela(
                                ownerId = userId,
                                nombre = nombre,
                                puntosLatLng = puntos
                            )
                        }
                    },
                    enabled = userId.isNotBlank() && nombre.isNotBlank() && puntos.size >= 3 && !state.loading,
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    if (state.loading) CircularProgressIndicator(strokeWidth = 2.dp, modifier = Modifier.size(18.dp))
                    else Text("GUARDAR PARCELA")
                }

                if (state.error != null) {
                    Spacer(Modifier.height(8.dp))
                    Text(state.error ?: "", color = MaterialTheme.colorScheme.error)
                }

                Spacer(Modifier.height(4.dp))
                Text("Puntos: ${puntos.size} (mínimo 3)")
            }
        }
    }
}


