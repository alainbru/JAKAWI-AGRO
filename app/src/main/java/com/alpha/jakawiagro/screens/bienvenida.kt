package com.alpha.jakawiagro.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.R

@Preview
@Composable
fun PantallaBienvenidaHakwai() {
    // InfiniteTransition para animar el tamaño del logo (efecto respiración)
    val infiniteTransition = rememberInfiniteTransition()
    val size by infiniteTransition.animateFloat(
        initialValue = 110f,
        targetValue = 130f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFEDF6F0)
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(24.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icono),
                    contentDescription = "Logo Jakawi Agro",
                    modifier = Modifier
                        .size(size.dp)    // Tamaño animado
                        .padding(bottom = 8.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Bienvenido a Hakwai Agro 🌱",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32)
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Tu asistente para decisiones agrícolas inteligentes.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color(0xFF4E6C50)
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

