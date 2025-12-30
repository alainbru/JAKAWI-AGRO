package com.alpha.jakawiagro.screens.temporal

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.R
import com.alpha.jakawiagro.ui.theme.JakawiAgroTheme

@Composable
fun VistaParcelas() {
    val colors = MaterialTheme.colorScheme

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
                containerColor = colors.surface
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val buttonColor = colors.primary
                    val contentColor = colors.onPrimary

                    Button(
                        onClick = { /* sin funcionalidad aún */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonColor,
                            contentColor = contentColor
                        )
                    ) {
                        Text("EDITAR")
                    }

                    Button(
                        onClick = { /* sin funcionalidad aún */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonColor,
                            contentColor = contentColor
                        )
                    ) {
                        Text("AGREGAR")
                    }

                    Button(
                        onClick = { /* sin funcionalidad aún */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonColor,
                            contentColor = contentColor
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
            Image(
                painter = painterResource(id = R.drawable.mapa_parcela),
                contentDescription = "Vista satelital predial",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Dark Mode"
)
@Composable
fun VistaParcelasPreview() {
    JakawiAgroTheme {
        VistaParcelas()
    }
}

