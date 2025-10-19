package com.alpha.jakawiagro.screens

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
fun PlagasHistorial(
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val beige = Color(0xFFF1EAD9)
    val greenBar = Color(0xFF8BC34A)
    val chipSegBg = Color(0xFFE7F3E9)
    val chipSegTx = Color(0xFF1F5B45)
    val chipOkBg  = Color(0xFFBFECC4)
    val chipOkTx  = Color(0xFF0D4B2F)
    val btnInicioBg = Color(0xFFD9E6E0)
    val btnAtrasBg  = Color(0xFF78D9AE)

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "PLAGAS Y ENFERMEDADES",
                onMenuClick = onMenuClick,
                onProfileClick = onProfileClick
            )
        },
        bottomBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(beige)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(btnInicioBg, Color.Black),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f).height(44.dp)
                ) { Text("INICIO") }
                Spacer(Modifier.width(12.dp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(btnAtrasBg, Color.Black),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f).height(44.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("ATRAS")
                }
            }
        },
        containerColor = greenBar
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .background(beige)
                .padding(padding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(12.dp))

            Text(
                "HISTORIAL",
                color = Color(0xFF2B1F8A),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),

            )

            Historial(
                thumb = R.drawable.ejemplo_plaga,
                fecha = "12 de marzo 2024",
                parcela = "Parcela 1",
                chipText = "En seguimiento",
                chipBg = chipSegBg,
                chipTx = chipSegTx
            )
            Spacer(Modifier.height(10.dp))

            Historial(
                thumb = R.drawable.ejemplo_plaga_1,
                fecha = "24 de febrero 2024",
                parcela = "Parcela 1",
                chipText = "En seguimiento",
                chipBg = chipSegBg,
                chipTx = chipSegTx
            )
            Spacer(Modifier.height(10.dp))

            Historial(
                thumb = R.drawable.ejemplo_plaga_2,
                fecha = "3 de enero 2024",
                parcela = "Parcela 1",
                chipText = "Resuelto",
                chipBg = chipOkBg,
                chipTx = chipOkTx
            )

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun Historial(
    thumb: Int,
    fecha: String,
    parcela: String,
    chipText: String,
    chipBg: Color,
    chipTx: Color
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF6F2E9))
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
                fecha,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1C1C1C)
                )
            )
            Text(
                parcela,
                style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF6B6B6B))
            )
        }
        Box(
            Modifier
                .clip(RoundedCornerShape(50))
                .background(chipBg)
                .padding(horizontal = 10.dp, vertical = 6.dp)
        ) {
            Text(
                chipText,
                color = chipTx,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.3.sp
                )
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF1EAD9)
@Composable
fun PreviewPlagas() {
    PlagasHistorial()
}

