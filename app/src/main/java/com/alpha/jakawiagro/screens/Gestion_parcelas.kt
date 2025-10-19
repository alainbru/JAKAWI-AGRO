package com.alpha.jakawiagro.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Gestion_parcela() {
    val parcelas = listOf(
        Parcela("Parcela Quinua 1", "Consumo humano", 2.5),
        Parcela("Parcela Papa Roja", "Consumo humano", 1.2),
        Parcela("Quinua Altiplano", "Consumo humano", 1.8),
        Parcela("Ichu Pastoreo", "Forraje animal", 4.0)
    )

    Scaffold(
        topBar = {
            MainTopAppBar(
                onMenuClick = { /* Abrir drawer o menú */ },
                onProfileClick = { /* Ir a perfil o ajustes */ }
            )
        },
        bottomBar = { BottomActionBar() // 👈 aquí agregamos la barra inferior
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF2F2F2)),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(parcelas) { parcela ->
                ParcelaCard(parcela)
            }

        }
    }
}
data class Parcela(
    val nombre: String,
    val uso: String,
    val areaHa: Double
)

@Composable
fun ParcelaCard(parcela: Parcela) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(parcela.nombre, style = MaterialTheme.typography.titleMedium)
            Text(parcela.uso, style = MaterialTheme.typography.bodyMedium)
            Text("${parcela.areaHa} ha", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun BottomActionBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ActionButton("+EDITAR")
        ActionButton("+AGREGAR")
        ActionButton("🗑️ELIMINAR")
    }
}

@Composable
fun ActionButton(label: String) {
    Button(
        onClick = { /* sin funcionalidad por ahora */ },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4CAF50),
            contentColor = Color.White
        )
    ) {
        Text(label)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewGestionParcela() {
    MaterialTheme {
        Gestion_parcela()
    }
}
