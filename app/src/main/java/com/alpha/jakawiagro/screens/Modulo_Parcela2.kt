package com.alpha.jakawiagro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.R

@Composable
fun Agregar_parcela() {
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Parcela",
                onMenuClick = { /* Abrir drawer o menú */ },
                onProfileClick = { /* Ir a perfil o ajustes */ }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Imagen de previsualización (desde drawable)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(440.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mapa_parcela),
                        contentDescription = "Vista satelital del terreno",
                        modifier = Modifier
                            .matchParentSize()
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )

                    // 🖊️ Botón flotante encima de la imagen
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                    ) {
                        BotonFlotanteEditar(onClick = { /* TODO: Acción de edición */ })
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

// 📝 Campo: Tipo Forraje o Cultivo
                Text(
                    text = "Tipo Forraje o Cultivo",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Start, //

                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .fillMaxWidth() //
                        .padding(bottom = 4.dp)
                )

                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Ingrese el tipo...") },
                    enabled = false, // Solo previsualización
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Medida",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Start, //

                    modifier = Modifier.
                    padding(bottom = 4.dp)
                        .fillMaxWidth() //

                )

                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Ingrese la medida...") },
                    enabled = false, // Solo previsualización
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                )

                // Botones sin funcionalidad aún
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {Button(
                    onClick = { /* TODO: Añadir funcionalidad */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50), // 💚 Verde
                        contentColor = Color.White          // 🏷 Texto blanco
                    )
                ) {
                    Text("AÑADIR")
                }

                    Button(
                        onClick = { /* TODO: Modificar funcionalidad */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50), // 💚 Verde
                            contentColor = Color.White
                        )
                    ) {
                        Text("MODIFICAR")
                    }
                }
                }

        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAgregarParcela() {
    MaterialTheme {
        Agregar_parcela()
    }
}

@Composable
fun BotonFlotanteEditar(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color(0xFF4CAF50), // 💚 verde
        contentColor = Color.White
    ) {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Editar",
            tint = Color.White
        )
    }
}
