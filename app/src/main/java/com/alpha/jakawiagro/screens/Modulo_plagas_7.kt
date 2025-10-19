package com.alpha.jakawiagro.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModuloPlagasRegistro(
    parcelaNombre: String = "Parcela 1",
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onVerHistorialClick: () -> Unit = {},
    onAsignarTareaClick: () -> Unit = {},
    onAtrasClick: () -> Unit = {}
) {
    val beige = Color(0xFFF1EAD9)
    val greenBar = Color(0xFF8BC34A)
    val mint = Color(0xFFA6E6C9)
    val deepGreen = Color(0xFF2F7261)
    val mintBtn = Color(0xFF78D9AE)

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "REGISTRO EXITOSO",
                onMenuClick = onMenuClick,
                onProfileClick = onProfileClick
            )
        },
        containerColor = greenBar
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(beige)
                .padding(padding)
        ) {
            // Contenido
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 360.dp)
                    .align(Alignment.Center)
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    tint = deepGreen,
                    contentDescription = "Éxito",
                    modifier = Modifier.size(48.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Plaga registrada\nen $parcelaNombre",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1C3A31),
                        lineHeight = 28.sp
                    )
                )

                Spacer(Modifier.height(20.dp))

                // Botón: Ver historial (menta claro)
                Button(
                    onClick = onVerHistorialClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = mint,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        "Ver historial",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }

                Spacer(Modifier.height(12.dp))

                // Botón: Asignar tarea (verde oscuro)
                Button(
                    onClick = onAsignarTareaClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = deepGreen,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        "Asignar tarea",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }

            Button(
                onClick = onAtrasClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = mintBtn,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .height(44.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Atrás"
                )
                Spacer(Modifier.width(8.dp))
                Text("ATRAS")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewModuloPlagas() {
    ModuloPlagasRegistro()
}
