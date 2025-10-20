package com.alpha.jakawiagro.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfiguracionCuenta(
    onGuardarClick: () -> Unit = {},
    onVolverClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val beige = Color(0xFFF1EAD9)
    val greenBar = Color(0xFFBFD84E)
    val buttonGreen = Color(0xFFDCE775)
    val cardBg = Color(0xFFF9F6EC)

    // Estados de configuración
    var notificaciones by remember { mutableStateOf(true) }
    var geolocalizacion by remember { mutableStateOf(true) }
    var usoOffline by remember { mutableStateOf(false) }
    var idiomaSeleccionado by remember { mutableStateOf("Español") }

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "CONFIGURACIONES",
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
                text = "Ajusta tus preferencias de cuenta",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color(0xFF1B1B1B),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(30.dp))

            // Card: Notificaciones
            ConfigCard {
                ConfigSwitchItem(
                    label = "Notificaciones",
                    checked = notificaciones,
                    onCheckedChange = { notificaciones = it }
                )
            }

            // Card: Idioma
            ConfigCard {
                IdiomaSelector(
                    idioma = idiomaSeleccionado,
                    onIdiomaSeleccionado = { idiomaSeleccionado = it }
                )
            }

            // Card: Geolocalización automática
            ConfigCard {
                ConfigSwitchItem(
                    label = "Geolocalización automática",
                    checked = geolocalizacion,
                    onCheckedChange = { geolocalizacion = it }
                )
            }

            // Card: Uso offline
            ConfigCard {
                ConfigSwitchItem(
                    label = "Uso offline",
                    checked = usoOffline,
                    onCheckedChange = { usoOffline = it }
                )
            }

            Spacer(Modifier.height(32.dp))

            // Botones de acción
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = onGuardarClick,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonGreen,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("GUARDAR CONFIGURACIÓN", textAlign = TextAlign.Center)
                }

                Spacer(Modifier.width(12.dp))

                OutlinedButton(
                    onClick = onVolverClick,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF333333)
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("VOLVER", textAlign = TextAlign.Center)
                }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun ConfigCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F6EC))
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            content()
        }
    }
}

@Composable
fun ConfigSwitchItem(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color(0xFF1B1B1B),
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp
            )
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFF8BC34A),
                checkedTrackColor = Color(0xFFAED581)
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdiomaSelector(
    idioma: String,
    onIdiomaSeleccionado: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = idioma,
            onValueChange = {},
            readOnly = true,
            label = { Text("Idioma") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF8BC34A),
                unfocusedBorderColor = Color(0xFFB0B0B0)
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Español") },
                onClick = {
                    onIdiomaSeleccionado("Español")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Quechua") },
                onClick = {
                    onIdiomaSeleccionado("Quechua")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Inglés") },
                onClick = {
                    onIdiomaSeleccionado("Inglés")
                    expanded = false
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConfiguracionCuenta() {
    ConfiguracionCuenta()
}
