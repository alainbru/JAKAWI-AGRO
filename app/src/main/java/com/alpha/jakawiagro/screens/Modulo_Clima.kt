package com.alpha.jakawiagro.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.R
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun Clima() {
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Clima",

                onMenuClick = { /* Abrir drawer o menú */ },
                onProfileClick = { /* Ir a perfil o ajustes */ }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Bienvenido 'USUARIO'",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                // 🔹 Sección de encabezado con clima actual
                HeaderSection()
                Spacer(modifier = Modifier.height(12.dp))

                // 🔹 Mensaje de alerta principal
                ForecastMessage()
                Spacer(modifier = Modifier.height(12.dp))

                // 🔹 Pronóstico por hora
                HourlyForecast()
                Spacer(modifier = Modifier.height(16.dp))

                // 🔹 Alertas agrícolas
                AgriculturalAlerts()
            }
        }
    )
}



@Composable
fun WeatherForecastScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFF3F6))
            .padding(16.dp)
    ) {
        HeaderSection()
        Spacer(modifier = Modifier.height(12.dp))
        ForecastMessage()
        Spacer(modifier = Modifier.height(12.dp))
        HourlyForecast()
        Spacer(modifier = Modifier.height(16.dp))
        AgriculturalAlerts()
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("UBICACIÓN", style = MaterialTheme.typography.labelSmall)
            Text("13°C", style = MaterialTheme.typography.displaySmall)
        }
        Icon(
            painter = painterResource(id = R.drawable.icono_lluvia),
            contentDescription = "Clima",
            modifier = Modifier.size(48.dp),
            tint = Color.Unspecified // mantiene el color real del drawable
        )
        Switch(checked = false, onCheckedChange = {}) // sin funcionalidad aún
    }
}

@Composable
fun ForecastMessage() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFDDEAF2))
    ) {
        Text(
            text = "🌨️ En tu parcela de quinua en Puno se espera helada el 23 de abril.",
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun HourlyForecast() {
    LazyRow {
        items(listOf("1:00", "2:00", "3:00", "3:00", "3:00")) { hour ->
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .width(64.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(hour, style = MaterialTheme.typography.labelSmall)
                Icon(
                    painter = painterResource(id = R.drawable.icono_clima),
                    contentDescription = "Clima",
                    modifier = Modifier.size(48.dp),
                    tint = Color.Unspecified // mantiene el color real del drawable
                )
            }
        }
    }
}

@Composable
fun AgriculturalAlerts() {
    val alerts = listOf(
        "🌨️ En tu parcela Quinua 1 se espera helada el 23 de abril.",
        "🌧️ En tu parcela Papa Roja se prevé lluvia intensa el 24 de abril.",
        "🌾 En tu parcela Avena Sur hay riesgo de sequía leve el 26 de abril.",
        "🌿 En tu parcela Alfalfa Este corte próximo el 30 de abril.",
        "🌾 En tu parcela Avena Sur hay riesgo de sequía leve el 26 de abril."
    )

    Column {
        alerts.forEach { alert ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Text(
                    text = alert,
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewClima() {
    MaterialTheme {
        Clima()
    }
}