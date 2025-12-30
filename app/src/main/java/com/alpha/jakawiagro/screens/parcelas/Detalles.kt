package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Forest
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.data.model.Parcela
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import com.google.maps.android.compose.*
import kotlinx.coroutines.tasks.await

@Composable
fun ParcelasDetallesScreen(
    parcelaId: String,
    parcelasViewModel: ParcelasViewModel,
    onBack: () -> Unit
) {
    val colors = MaterialTheme.colorScheme
    var parcela by remember { mutableStateOf<Parcela?>(null) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(parcelaId) {
        loading = true
        error = null
        try {
            val snap = FirebaseFirestore.getInstance()
                .collection("parcelas")
                .document(parcelaId)
                .get()
                .await()
            parcela = snap.toObject(Parcela::class.java)
        } catch (e: Exception) {
            error = e.message ?: "Error al cargar detalles"
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

    val puntos = p.puntos.map { LatLng(it.latitude, it.longitude) }
    val center = puntos.firstOrNull() ?: LatLng(-15.8402, -70.0219)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(center, 15f)
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
                Icon(Icons.Default.Forest, contentDescription = null, tint = colors.onPrimaryContainer)
                Spacer(Modifier.width(10.dp))
                Column {
                    Text(
                        text = "DETALLES",
                        fontWeight = FontWeight.ExtraBold,
                        color = colors.onPrimaryContainer
                    )
                    Text(
                        text = p.nombre,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = colors.onPrimaryContainer
                    )
                }
            }
        }

        // Info
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = colors.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Terrain, contentDescription = null, tint = colors.primary)
                    Spacer(Modifier.width(10.dp))
                    Text("Puntos del terreno: ${p.puntos.size}", fontWeight = FontWeight.SemiBold)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, contentDescription = null, tint = colors.primary)
                    Spacer(Modifier.width(10.dp))
                    Text("Ubicación: ${p.ubicacion ?: "-"}", fontWeight = FontWeight.SemiBold)
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    AssistChip(onClick = {}, label = { Text("Mapa satelital") })
                    AssistChip(onClick = {}, label = { Text("Parcela") })
                }
            }
        }

        // Mapa en card
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
                uiSettings = MapUiSettings(zoomControlsEnabled = false)
            ) {
                puntos.forEach { pt -> Marker(state = MarkerState(position = pt)) }
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
    }
}



