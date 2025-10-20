package com.alpha.jakawiagro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.R

@Composable
fun VistaParcelas() {
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Mis Parcelas",
                onMenuClick = { /* Abrir drawer o menú */ },
                onProfileClick = { /* Ir a perfil o ajustes */ }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { /* sin funcionalidad aún */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50), // 💚 Verde
                            contentColor = Color.White          // 🤍 Texto blanco
                        )
                    ) {
                        Text("EDITAR")
                    }

                    Button(
                        onClick = { /* sin funcionalidad aún */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50),
                            contentColor = Color.White
                        )
                    ) {
                        Text("AGREGAR")
                    }

                    Button(
                        onClick = { /* sin funcionalidad aún */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50),
                            contentColor = Color.White
                        )
                    ) {
                        Text("ELIMINAR")
                    }

                }
            }
        }

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            // Imagen de fondo (puedes reemplazar con painterResource si es local)
            Image(
                painter = painterResource(id = R.drawable.mapa_parcela),
                contentDescription = "Vista satelital predial",
                modifier = Modifier

                    .fillMaxSize(), // 🔹 Usa todo el espacio disponible

                contentScale = ContentScale.Crop
            )

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewVistaParcelas() {
    MaterialTheme {
        VistaParcelas()
    }
}
