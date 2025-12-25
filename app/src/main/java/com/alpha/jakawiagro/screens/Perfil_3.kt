package com.alpha.jakawiagro.screens

import android.content.res.Configuration
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
import com.alpha.jakawiagro.ui.theme.JakawiAgroTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfiguracionCuenta(
    onGuardarClick: () -> Unit = {},
    onVolverClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val colorScheme = MaterialTheme.colorScheme

    var notificaciones by remember { mutableStateOf(true) }
    var geolocalizacion by remember { mutableStateOf(true) }
    var usoOffline by remember { mutableStateOf(false) }
    var idiomaSeleccionado by remember { mutableStateOf("Español") }

    Scaffold(
        containerColor = colorScheme.background,
        topBar = {
            MainTopAppBar(
                title = "CONFIGURACIONES",
                onMenuClick = onMenuClick,
                onProfileClick = onProfileClick
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Ajusta tus preferencias de cuenta",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(30.dp))

            ConfigCard {
                ConfigSwitchItem(
                    label = "Notificaciones",
                    checked = notificaciones,
                    onCheckedChange = { notificaciones = it }
                )
            }

            ConfigCard {
                IdiomaSelector(
                    idioma = idiomaSeleccionado,
                    onIdiomaSeleccionado = { idiomaSeleccionado = it }
                )
            }

            ConfigCard {
                ConfigSwitchItem(
                    label = "Geolocalización automática",
                    checked = geolocalizacion,
                    onCheckedChange = { geolocalizacion = it }
                )
            }

            ConfigCard {
                ConfigSwitchItem(
                    label = "Uso offline",
                    checked = usoOffline,
                    onCheckedChange = { usoOffline = it }
                )
            }

            Spacer(Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = onGuardarClick,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorScheme.primary,
                        contentColor = colorScheme.onPrimary
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
                        contentColor = colorScheme.primary
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
    val colorScheme = MaterialTheme.colorScheme

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
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
    val colorScheme = MaterialTheme.colorScheme

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            color = colorScheme.onSurface
        )

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = colorScheme.primary,
                checkedTrackColor = colorScheme.primaryContainer,
                uncheckedThumbColor = colorScheme.outline,
                uncheckedTrackColor = colorScheme.surfaceVariant
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
    val colorScheme = MaterialTheme.colorScheme
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
                focusedBorderColor = colorScheme.primary,
                unfocusedBorderColor = colorScheme.outline,
                focusedLabelColor = colorScheme.primary,
                cursorColor = colorScheme.primary
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listOf("Español", "Quechua", "Inglés").forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        onIdiomaSeleccionado(it)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewConfiguracionCuenta() {
    JakawiAgroTheme {
        ConfiguracionCuenta()
    }
}