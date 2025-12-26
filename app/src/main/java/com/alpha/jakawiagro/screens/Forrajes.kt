package com.alpha.jakawiagro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alpha.jakawiagro.ui.theme.JakawiAgroTheme

@Composable
fun ForrajesScreen(
    onAddForrajeClick: () -> Unit,
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme
    val shapes = MaterialTheme.shapes

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Forrajes",
                onMenuClick = onMenuClick,
                onProfileClick = onProfileClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddForrajeClick,
                containerColor = colors.primary,
                shape = shapes.medium
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Forraje", tint = colors.onPrimary)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("🌿 Tus forrajes", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = colors.onSurface)
            Spacer(modifier = Modifier.height(8.dp))
            Text("0 forrajes registrados", color = colors.onSurfaceVariant)
        }
    }
}


@Preview(uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewForrajesScreenDark() {
    JakawiAgroTheme { // tu tema personalizado
        ForrajesScreen(
            onAddForrajeClick = {},
            onMenuClick = {},
            onProfileClick = {}
        )
    }
}

