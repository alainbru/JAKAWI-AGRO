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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.MaterialTheme
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
import com.alpha.jakawiagro.ui.theme.JakawiAgroTheme

@Composable
fun AgregarParcela() {
    val colors = MaterialTheme.colorScheme

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

                // Imagen de previsualización
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
                            .border(1.dp, colors.outline, RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )

                    // Botón flotante encima de la imagen
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                    ) {
                        BotonFlotanteEditar(onClick = { /* TODO: Acción de edición */ })
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Tipo Forraje o Cultivo
                Text(
                    text = "Tipo Forraje o Cultivo",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = colors.onBackground
                    ),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
                )

                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Ingrese el tipo...") },
                    enabled = false,

                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Medida
                Text(
                    text = "Medida",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = colors.onBackground
                    ),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
                )

                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Ingrese la medida...") },
                    enabled = false,

                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botones
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    val buttonColor = colors.primary
                    val contentColor = colors.onPrimary

                    Button(
                        onClick = { /* TODO: Añadir funcionalidad */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonColor,
                            contentColor = contentColor
                        )
                    ) {
                        Text("AÑADIR")
                    }

                    Button(
                        onClick = { /* TODO: Modificar funcionalidad */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonColor,
                            contentColor = contentColor
                        )
                    ) {
                        Text("MODIFICAR")
                    }
                }
            }
        }
    )
}

@Composable
fun BotonFlotanteEditar(onClick: () -> Unit) {
    val colors = MaterialTheme.colorScheme
    FloatingActionButton(
        onClick = onClick,
        containerColor = colors.primary,
        contentColor = colors.onPrimary
    ) {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Editar",
            tint = colors.onPrimary
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun PreviewAgregarParcela() {
    JakawiAgroTheme {
        AgregarParcela()
    }
}
