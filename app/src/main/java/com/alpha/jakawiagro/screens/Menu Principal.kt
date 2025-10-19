package com.alpha.jakawiagro.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ExampleScreen() {
    Scaffold(
        topBar = {
            MainTopAppBar(
                onMenuClick = { /* Abrir drawer o menú */ },
                onProfileClick = { /* Ir a perfil o ajustes */ }
            )
        },
        content = { padding ->
            // Aquí va el contenido de cada pantalla
            Box(modifier = Modifier.padding(padding)) {
                Text("Contenido de la pantalla")
            }
        }
    )
}
