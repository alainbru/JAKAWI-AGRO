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
import com.alpha.jakawiagro.ui.theme.JakawiAgroTheme


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
                title = "Mis Parcelas",
                onMenuClick = { /* Abrir drawer o menú */ },
                onProfileClick = { /* Ir a perfil o ajustes */ }
            )
        },
        bottomBar = { BottomActionBar() }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
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
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(parcela.nombre, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
            Text(parcela.uso, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text("${parcela.areaHa} ha", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
@Composable
fun BottomActionBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
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
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(label)
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GestionParcelaDarkPreview() {
    JakawiAgroTheme {
        Gestion_parcela()
    }
}


