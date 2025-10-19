package com.alpha.jakawiagro.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModuloPlagasRegistrarParcelaScreen(
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onRegistrarClick: (String) -> Unit = {},
    onAtrasClick: () -> Unit = {}
) {
    val beige = Color(0xFFF1EAD9)
    val greenBar = Color(0xFF8BC34A)
    val mint = Color(0xFF78D9AE)
    val lime = Color(0xFFD6E35E)
    val purpleText = Color(0xFF2B1F8A)

    // Lista de parcelas (reemplaza por las tuyas si vienen de BD)
    val parcelas = remember { listOf("Parcela 1", "Parcela 2", "Parcela 3") }
    var selected by remember { mutableStateOf(parcelas.first()) }
    var expanded by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "REGISTRAR EN PARCELA",
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
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Spacer(Modifier.height(200.dp))

                Text(
                    text = "Selecciona en que\nparcela se\ndetecto la plaga\no enfermedad",
                    color = purpleText,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 26.sp
                    )
                )

                Spacer(Modifier.height(18.dp))

                // Selector de parcela (Exposed Dropdown)
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                        if (!expanded) focusManager.clearFocus()
                    },
                    modifier = Modifier.widthIn(max = 320.dp)
                ) {
                    OutlinedTextField(
                        value = selected,
                        onValueChange = {},
                        readOnly = true,
                        leadingIcon = {
                            // Cuadro de color tipo "leyenda" de parcela
                            Box(
                                modifier = Modifier
                                    .size(18.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color(0xFF1B3A2A))
                            )
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Abrir"
                            )
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp)),
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        parcelas.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    selected = option
                                    expanded = false
                                    focusManager.clearFocus()
                                }
                            )
                        }
                    }
                }

                Spacer(Modifier.height(28.dp))

                // Botón principal
                Button(
                    onClick = { onRegistrarClick(selected) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = lime,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .widthIn(max = 320.dp)
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        "REGISTRAR EN PARCELA",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.4.sp
                        )
                    )
                }
            }

            // Botón ATRAS abajo a la derecha
            Button(
                onClick = onAtrasClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = mint,
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
fun PreviewModuloPlagasRegistrarParcelaScreen() {
    ModuloPlagasRegistrarParcelaScreen()
}
