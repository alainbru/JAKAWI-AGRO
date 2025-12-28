package com.alpha.jakawiagro.screens.parcelas

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditLocationAlt
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.viewmodel.parcelas.ParcelasViewModel

@Composable
fun ParcelasHomeScreen(
    onDraw: () -> Unit,
    onList: () -> Unit,
    vm: ParcelasViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Módulo de Parcelas",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Dibuja tu parcela sobre el mapa satelital, guárdala y gestiona tu listado.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(8.dp))

        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = onDraw,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.EditLocationAlt, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Dibujar parcela")
                }

                OutlinedButton(
                    onClick = onList,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.List, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Mis parcelas")
                }
            }
        }
    }
}

