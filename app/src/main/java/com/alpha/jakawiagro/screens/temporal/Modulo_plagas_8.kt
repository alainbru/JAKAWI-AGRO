package com.alpha.jakawiagro.screens.temporal

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alpha.jakawiagro.R
import com.alpha.jakawiagro.ui.theme.JakawiAgroTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlagasHistorial(
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme

    Scaffold(
        containerColor = colors.background,
        topBar = {
            MainTopAppBar(
                title = "PLAGAS Y ENFERMEDADES",
                onMenuClick = onMenuClick,
                onProfileClick = onProfileClick
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.surface)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.secondaryContainer,
                        contentColor = colors.onSecondaryContainer
                    )
                ) {
                    Text("INICIO")
                }

                Spacer(Modifier.width(12.dp))

                Button(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.primaryContainer,
                        contentColor = colors.onPrimaryContainer
                    )
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("ATRÁS")
                }
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(12.dp))

            Text(
                text = "HISTORIAL",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.sp,
                color = colors.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            )

            HistorialItemPlaga(
                thumb = R.drawable.ejemplo_plaga,
                fecha = "12 de marzo 2024",
                parcela = "Parcela 1",
                estado = EstadoPlaga.Seguimiento
            )

            Spacer(Modifier.height(10.dp))

            HistorialItemPlaga(
                thumb = R.drawable.ejemplo_plaga_1,
                fecha = "24 de febrero 2024",
                parcela = "Parcela 1",
                estado = EstadoPlaga.Seguimiento
            )

            Spacer(Modifier.height(10.dp))

            HistorialItemPlaga(
                thumb = R.drawable.ejemplo_plaga_2,
                fecha = "3 de enero 2024",
                parcela = "Parcela 1",
                estado = EstadoPlaga.Resuelto
            )

            Spacer(Modifier.height(24.dp))
        }
    }
}

enum class EstadoPlaga {
    Seguimiento,
    Resuelto
}

@Composable
fun HistorialItemPlaga(
    thumb: Int,
    fecha: String,
    parcela: String,
    estado: EstadoPlaga
) {
    val colors = MaterialTheme.colorScheme

    val chipColors = when (estado) {
        EstadoPlaga.Seguimiento -> colors.tertiaryContainer to colors.onTertiaryContainer
        EstadoPlaga.Resuelto -> colors.primaryContainer to colors.onPrimaryContainer
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(colors.surfaceVariant)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(thumb),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(Modifier.width(12.dp))

        Column(Modifier.weight(1f)) {
            Text(
                text = fecha,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = colors.onSurface
            )

            Text(
                text = parcela,
                style = MaterialTheme.typography.bodySmall,
                color = colors.onSurfaceVariant
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(chipColors.first)
                .padding(horizontal = 10.dp, vertical = 6.dp)
        ) {
            Text(
                text = estado.name.uppercase(),
                color = chipColors.second,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.3.sp
            )
        }
    }
}


@Preview(
    name = "Plagas Historial - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
fun PreviewPlagasHistorialDark() {
    JakawiAgroTheme {
        PlagasHistorial()
    }
}
