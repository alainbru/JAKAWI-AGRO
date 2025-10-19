package com.alpha.jakawiagro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFEDF6F0) // fondo suave tipo agro
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
                    painter = painterResource(id = R.drawable.icono), // ← Tu logo aquí
                    contentDescription = "Logo Jakawi Agro",
                    modifier = Modifier
                        .size(120.dp)
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
