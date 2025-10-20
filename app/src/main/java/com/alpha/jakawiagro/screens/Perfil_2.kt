package com.alpha.jakawiagro.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarPerfil(
    onGuardarClick: () -> Unit = {},
    onCancelarClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val beige = Color(0xFFF1EAD9)
    val greenBar = Color(0xFFBFD84E)
    val buttonGreen = Color(0xFFDCE775)
    val cardBg = Color(0xFFF9F6EC)

    var nombre by remember { mutableStateOf("Juan") }
    var apellido by remember { mutableStateOf("Pérez Condori") }
    var telefono by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("12345678") }

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "EDITAR PERFIL",
                onMenuClick = onMenuClick,
                onProfileClick = onProfileClick
            )
        },
        containerColor = greenBar
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(beige)
                .padding(padding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(20.dp))

            Text(
                text = "Actualiza tu información personal",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color(0xFF1B1B1B),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(24.dp))

            // ===== Campos con estilo mejorado =====
            PerfilInputCard(
                label = "Nombre",
                value = nombre,
                onValueChange = { nombre = it },
                keyboardType = KeyboardType.Text
            )

            PerfilInputCard(
                label = "Apellido",
                value = apellido,
                onValueChange = { apellido = it },
                keyboardType = KeyboardType.Text
            )

            PerfilInputCard(
                label = "Teléfono",
                value = telefono,
                onValueChange = { telefono = it },
                keyboardType = KeyboardType.Number
            )

            PerfilInputCard(
                label = "Correo electrónico",
                value = correo,
                onValueChange = { correo = it },
                keyboardType = KeyboardType.Email
            )

            PerfilInputCard(
                label = "DNI",
                value = dni,
                onValueChange = { dni = it },
                keyboardType = KeyboardType.Number
            )

            Spacer(Modifier.height(32.dp))

            // ===== Botones =====
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = onGuardarClick,
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonGreen,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .shadow(4.dp, RoundedCornerShape(14.dp))
                ) {
                    Text(
                        "GUARDAR CAMBIOS",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                Spacer(Modifier.width(12.dp))

                OutlinedButton(
                    onClick = onCancelarClick,
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF333333)
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                ) {
                    Text(
                        "CANCELAR",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

// ===== Campo de texto dentro de una tarjeta con sombra =====
@Composable
fun PerfilInputCard(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .shadow(3.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F6EC))
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF8BC34A),
                unfocusedBorderColor = Color(0xFFB0B0B0),
                focusedLabelColor = Color(0xFF558B2F),
                cursorColor = Color(0xFF558B2F)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEditarPerfil() {
    EditarPerfil()
}
