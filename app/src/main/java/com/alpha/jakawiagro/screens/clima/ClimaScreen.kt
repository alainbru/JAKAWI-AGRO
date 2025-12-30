@file:OptIn(ExperimentalMaterial3Api::class)

package com.alpha.jakawiagro.screens.clima

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
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ClimaScreen(viewModel: ClimaViewModel = viewModel()) {
    val colors = MaterialTheme.colorScheme
    val ui by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) { viewModel.cargar() }

    val bg = Brush.verticalGradient(
        listOf(
            colors.background,
            colors.primary.copy(alpha = 0.05f),
            colors.background
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        // Header
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(containerColor = colors.primaryContainer),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Cloud, null, tint = colors.onPrimaryContainer)
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "CLIMA",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = colors.onPrimaryContainer
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, null, tint = colors.onPrimaryContainer)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Zona: ${ui.lugar}",
                        color = colors.onPrimaryContainer.copy(alpha = 0.92f),
                        fontWeight = FontWeight.SemiBold
                    )
                }

                // Selector lugar
                var expanded by remember { mutableStateOf(false) }

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = ui.lugar,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Elegir lugar") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        LUGARES_PERU.forEach { lugar ->
                            DropdownMenuItem(
                                text = { Text(lugar.nombre) },
                                onClick = {
                                    expanded = false
                                    viewModel.setLugar(lugar)
                                }
                            )
                        }
                    }
                }

                if (ui.loading) {
                    LinearProgressIndicator(Modifier.fillMaxWidth())
                }

                ui.error?.let { err ->
                    AssistChip(
                        onClick = { viewModel.cargar() },
                        label = { Text("Reintentar: $err") }
                    )
                }
            }
        }

        // Resumen
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = colors.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Thermostat, null, tint = colors.primary)
                    Spacer(Modifier.width(10.dp))
                    Text("Resumen actual", fontWeight = FontWeight.ExtraBold)
                }

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    MetricChip("Temp.", ui.tempC?.let { "$it°C" } ?: "--")
                    MetricChip("Humedad", ui.humedad?.let { "$it%" } ?: "--")
                    MetricChip("Viento", ui.vientoKmh?.let { "$it km/h" } ?: "--")
                }

                val alerta = when {
                    (ui.tempC ?: 99) <= 2 -> "ALERTA: posible helada. Protege cultivos sensibles."
                    else -> null
                }

                if (alerta != null) {
                    Card(
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = colors.errorContainer)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Warning, null, tint = colors.onErrorContainer)
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = alerta,
                                color = colors.onErrorContainer,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }

        // Pronóstico
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = colors.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CalendarMonth, null, tint = colors.primary)
                    Spacer(Modifier.width(10.dp))
                    Text("Pronóstico (5 días)", fontWeight = FontWeight.ExtraBold)
                }

                if (ui.pronosticoDias.isEmpty() && !ui.loading) {
                    Text("Sin datos por ahora.", color = colors.onSurfaceVariant)
                } else {
                    ui.pronosticoDias.forEach { d ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = d.day,
                                modifier = Modifier.weight(1f),
                                fontWeight = FontWeight.SemiBold
                            )
                            AssistChip(onClick = {}, label = { Text("${d.tMin}° / ${d.tMax}°") })
                            Spacer(Modifier.width(8.dp))
                            AssistChip(onClick = {}, label = { Text("Lluvia ${d.lluviaMax}%") })
                        }

                        HorizontalDivider(color = colors.outline.copy(alpha = 0.25f))
                    }
                }
            }
        }

        Button(
            onClick = { viewModel.cargar() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(18.dp)
        ) {
            Icon(Icons.Default.Refresh, null)
            Spacer(Modifier.width(10.dp))
            Text("Actualizar clima")
        }
    }
}

@Composable
private fun MetricChip(label: String, value: String) {
    AssistChip(onClick = {}, label = { Text("$label: $value") })
}
