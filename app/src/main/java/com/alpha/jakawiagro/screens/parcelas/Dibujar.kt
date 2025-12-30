package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.viewmodel.auth.AuthViewModel
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel
import com.google.android.gms.maps.model.LatLng

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dibujar(
    authViewModel: AuthViewModel,
    parcelasViewModel: ParcelasViewModel,
    onSaved: () -> Unit,
    onBack: () -> Unit
) {
    val auth = authViewModel.uiState.collectAsState().value
    val state = parcelasViewModel.uiState.collectAsState().value

    var nombre by remember { mutableStateOf("Parcela demo") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dibujar parcela") },
                navigationIcon = { IconButton(onClick = onBack) { Text("←") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text("Aquí luego irá Google Maps + polígono.")
            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            if (state.error != null) {
                Spacer(Modifier.height(8.dp))
                Text(state.error, color = MaterialTheme.colorScheme.error)
            }

            Spacer(Modifier.height(16.dp))
            Button(
                enabled = !state.loading && auth.userId != null,
                onClick = {
                    val uid = auth.userId ?: return@Button
                    val puntos = listOf(
                        LatLng(-12.0464, -77.0428),
                        LatLng(-12.0460, -77.0420),
                        LatLng(-12.0468, -77.0415),
                        LatLng(-12.0472, -77.0423)
                    )

                    parcelasViewModel.crearParcela(
                        ownerId = uid,
                        nombre = nombre,
                        puntosLatLng = puntos,
                        areaHa = null,
                        ubicacion = null
                    )
                    onSaved()
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text(if (state.loading) "Guardando..." else "Guardar") }
        }
    }
}


