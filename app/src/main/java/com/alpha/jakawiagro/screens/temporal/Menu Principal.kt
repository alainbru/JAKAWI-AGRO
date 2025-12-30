package com.alpha.jakawiagro.screens.temporal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import com.alpha.jakawiagro.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alpha.jakawiagro.ui.theme.shapes


@Composable
fun MenuPrincipal(navController: NavController) {
    Scaffold(
        topBar = {
            MainTopAppBar(
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
                MenuAgricola(navController)
            }
        }
    )
}

@Composable
fun MenuAgricola(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                MenuItem("Cultivos", R.drawable.icono_cultivos) {
                    navController.navigate("cultivos")
                }
                MenuItem("Forraje", R.drawable.icono_forraje) {
                    navController.navigate("forraje")
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                MenuItem("Plagas IA", R.drawable.icono_plagas) {
                    navController.navigate("plagas_ia")
                }
                MenuItem("Asistencia", R.drawable.icono_asistencia) {
                    navController.navigate("asistencia")
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                MenuItem("Clima y Alertas", R.drawable.icono_clima) {
                    navController.navigate("clima_alertas")
                }
                MenuItem("Gestión de Parcelas", R.drawable.icono_parcelas) {
                    navController.navigate("gestion_parcelas")
                }
            }
        }
    }
}

@Composable
fun MenuItem(titulo: String, iconoRes: Int, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(120.dp)
            .clip(shapes.large)
            .clickable(onClick = onClick)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(12.dp)
    ) {
        Image(
            painter = painterResource(id = iconoRes),
            contentDescription = titulo,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = titulo,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            textAlign = TextAlign.Center
        )
    }
}

