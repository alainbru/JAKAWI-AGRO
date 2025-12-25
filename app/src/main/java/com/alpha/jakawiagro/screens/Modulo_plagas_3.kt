package com.alpha.jakawiagro.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.R
import com.alpha.jakawiagro.ui.theme.JakawiAgroTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModuloPlagasEjemplo(
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    // 🎨 Colores desde el theme (dark / light friendly)
    val background = MaterialTheme.colorScheme.background
    val topBarColor = MaterialTheme.colorScheme.primary
    val frameColor = MaterialTheme.colorScheme.outline
    val actionButton = MaterialTheme.colorScheme.secondaryContainer
    val actionText = MaterialTheme.colorScheme.onSecondaryContainer

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "EJEMPLO",
                onMenuClick = onMenuClick,
                onProfileClick = onProfileClick
            )
        },
        containerColor = topBarColor
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
                .padding(padding)
        ) {

            Card(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(0.8f),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Box(
                    modifier = Modifier
                        .border(
                            width = 4.dp,
                            color = frameColor,
                            shape = RoundedCornerShape(16.dp)
                        )
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
                    containerColor = actionButton,
                    contentColor = actionText
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .height(44.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Volver"
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "ATRÁS",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Preview(
    name = "Modulo Plagas Ejemplo",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewModuloPlagasEjemploDark() {
    JakawiAgroTheme {
        ModuloPlagasEjemplo()
    }
}
