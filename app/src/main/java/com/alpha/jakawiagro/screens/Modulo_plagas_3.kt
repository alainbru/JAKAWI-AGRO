package com.alpha.jakawiagro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModuloPlagasEjemplo(
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val beige = Color(0xFFF1EAD9)
    val greenBar = Color(0xFF8BC34A)
    val frameGray = Color(0xFF666666)
    val mint = Color(0xFF78D9AE)

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "EJEMPLO",
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

            Card(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(0.8f),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .border(width = 6.dp, color = frameGray, shape = RoundedCornerShape(16.dp))
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ejemplo_plaga),
                        contentDescription = "Ejemplo de foto",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .fillMaxWidth()
                            .aspectRatio(3f / 4f)
                    )
                }
            }

            Button(
                onClick = onBackClick,
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
                androidx.compose.material3.Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver"
                )
                Spacer(Modifier.width(8.dp))
                Text("ATRAS")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewModuloPlagasEjemplo() {
    ModuloPlagasEjemplo()
}
