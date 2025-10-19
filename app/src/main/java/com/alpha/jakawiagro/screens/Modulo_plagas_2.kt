package com.alpha.jakawiagro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alpha.jakawiagro.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModuloPlagasFoto(
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onTomarFotoClick: () -> Unit = {},
    onSubirFotoClick: () -> Unit = {},
    onVerEjemploClick: () -> Unit = {}
) {
    val beige = Color(0xFFF1EAD9)
    val greenBar = Color(0xFF8BC34A)
    val mint = Color(0xFF78D9AE)
    val mintLight = Color(0xFFA6E6C9)

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "PLAGAS Y ENFERMEDADES",
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .align(Alignment.Center)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.planta_lupa),
                    contentDescription = "Ilustración instrucciones",
                    modifier = Modifier.size(240.dp)
                )

                Spacer(Modifier.height(1.dp))

                Text(
                    text = "Usa buena luz y\nenfoca la hoja\nafectada",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1B1B1B),
                        lineHeight = 26.sp
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(20.dp))

                Card(
                    colors = CardDefaults.cardColors(containerColor = mintLight.copy(alpha = 0.15f)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        AccionFotoButton(
                            iconRes = R.drawable.icono_camara,
                            text = "TOMAR FOTO",
                            containerColor = mint,
                            onClick = onTomarFotoClick
                        )

                        Spacer(Modifier.height(16.dp))

                        AccionFotoButton(
                            iconRes = R.drawable.icono_subir,
                            text = "SUBIR FOTO",
                            containerColor = mint,
                            onClick = onSubirFotoClick
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "VER EJEMPLO",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1B1B1B),
                        letterSpacing = 0.5.sp
                    ),

                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickableNoRipple(onVerEjemploClick)
                )
            }
        }
    }
}

/**
 * Botón grande con icono a la izquierda y texto centrado.
 * Reemplaza 'iconRes' por tu recurso en res/drawable.
 */
@Composable
fun AccionFotoButton(
    iconRes: Int,
    text: String,
    containerColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = Color(0xFF0A2F24)
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth(0.92f)
            .height(56.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(

                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.width(14.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                ),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.width(24.dp))
        }
    }
}


fun Modifier.clickableNoRipple(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewModuloPlagasFoto() {
    ModuloPlagasFoto()
}

