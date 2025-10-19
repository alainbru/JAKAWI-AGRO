package com.alpha.jakawiagro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import androidx.compose.ui.unit.sp
import com.alpha.jakawiagro.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModuloPlagasResultado(
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onDetectarClick: () -> Unit = {},
    onRetomarFotoClick: () -> Unit = {}
) {
    val beige = Color(0xFFF1EAD9)
    val greenBar = Color(0xFF8BC34A)
    val frameGray = Color(0xFF666666)
    val mint = Color(0xFF78D9AE)

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "IA PLAGAS",
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
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(70.dp))

            Column(
                modifier = Modifier
                    .widthIn(max = 360.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(6.dp, frameGray, RoundedCornerShape(16.dp))
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ejemplo_plaga),
                        contentDescription = "Foto capturada",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .fillMaxWidth()
                            .aspectRatio(3f / 4f)
                    )
                }

                Spacer(Modifier.height(14.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Color(0x22000000))
                )

                Spacer(Modifier.height(12.dp))

                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0x14000000)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AccionIconButton(
                            iconRes = R.drawable.icono_busqueda,
                            text = "DETECTAR",
                            containerColor = mint,
                            onClick = onDetectarClick,
                            modifier = Modifier.weight(1f)
                        )
                        AccionIconButton(
                            iconRes = R.drawable.icono_camara,
                            text = "VOLVER A\nTOMAR FOTO",
                            containerColor = mint,
                            onClick = onRetomarFotoClick,
                            twoLines = true,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(Modifier.weight(1f))
        }
    }
}

/** Botón con icono a la izquierda y texto centrado (igual). */
@Composable
fun AccionIconButton(
    iconRes: Int,
    text: String,
    containerColor: Color,
    onClick: () -> Unit,
    twoLines: Boolean = false,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(horizontal = 12.dp),
        modifier = modifier.height(if (twoLines) 56.dp else 48.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.4.sp,
                    fontSize = if (twoLines) 12.sp else 14.sp
                ),
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(20.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewModuloPlagasResultado() {
    ModuloPlagasResultado()
}

