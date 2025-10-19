package com.alpha.jakawiagro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alpha.jakawiagro.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanDeTrabajoScreen(
    onAddPlanClick: () -> Unit,
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Plan de trabajo",
                onMenuClick = onMenuClick,
                onProfileClick = onProfileClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddPlanClick,
                containerColor = Color(0xFF56C7C1)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Añadir cultivo")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Seleccione la parcela", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text("Seleccionar todo")

            // Tarjeta de ejemplo de parcela con imagen
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5DC), shape = RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // ✅ Imagen actualizada
                    Image(
                        painter = painterResource(id = R.drawable.img_parcela1),
                        contentDescription = "Imagen de parcela",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        contentScale = ContentScale.Crop
                    )

                    Text("Parcela 1", fontWeight = FontWeight.Bold, color = Color(0xFF4B7F52))
                    Text("Descripción", color = Color.Gray)

                    Button(
                        onClick = { /* acción añadir cultivo */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB7E0C1))
                    ) {
                        Text("Añadir Plan de Trabajo", color = Color.Black)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPlanDeTrabajoScreen() {
    PlanDeTrabajoScreen(onAddPlanClick = {})
}
