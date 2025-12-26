package com.alpha.jakawiagro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.alpha.jakawiagro.R
import com.alpha.jakawiagro.ui.theme.JakawiAgroTheme
import com.alpha.jakawiagro.ui.theme.shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModuloAsistencia(
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val primaryBar = MaterialTheme.colorScheme.primary
    val backgroundColor = MaterialTheme.colorScheme.background
    val cardColor = MaterialTheme.colorScheme.secondaryContainer
    val textColor = MaterialTheme.colorScheme.onBackground

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "ASISTENCIA",
                onMenuClick = onMenuClick,
                onProfileClick = onProfileClick
            )
        },
        containerColor = primaryBar
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(padding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.icono_bienvenida2),
                contentDescription = "Asistente",
                modifier = Modifier
                    .size(180.dp)
                    .clip(shapes.medium)
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "HOLA! bienvenido al\nasistente de actividades practicas\ndel campo.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Black,
                    color = textColor,
                    lineHeight = 20.sp
                )
            )

            Spacer(Modifier.height(18.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 360.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OpcionAsistenciaCard(
                        iconRes = R.drawable.icono_cultivos,
                        label = "CULTIVOS",
                        containerColor = cardColor,
                        modifier = Modifier.weight(1f)
                    )
                    OpcionAsistenciaCard(
                        iconRes = R.drawable.icono_clima,
                        label = "RIEGO",
                        containerColor = cardColor,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OpcionAsistenciaCard(
                        iconRes = R.drawable.icono_asistencia,
                        label = "PODA /\nCORTE",
                        containerColor = cardColor,
                        modifier = Modifier.weight(1f)
                    )
                    OpcionAsistenciaCard(
                        iconRes = R.drawable.icono_plagas,
                        label = "PLAGAS",
                        containerColor = cardColor,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

/** Tarjeta cuadrada con ícono y texto centrados. */
@Composable
fun OpcionAsistenciaCard(
    iconRes: Int,
    label: String,
    containerColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = shapes.large,
        modifier = modifier
            .height(96.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(34.dp)
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = label,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    lineHeight = 16.sp
                )
            )
        }
    }
}


@Preview(uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMenuPrincipalDark() {
    JakawiAgroTheme {
        // NavController de prueba para preview
        val navController = rememberNavController()
        MenuPrincipal(navController)
    }
}

