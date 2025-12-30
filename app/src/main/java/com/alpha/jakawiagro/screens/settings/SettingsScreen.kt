package com.alpha.jakawiagro.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    useSystemTheme: Boolean,
    darkMode: Boolean,
    onToggleUseSystemTheme: (Boolean) -> Unit,
    onToggleDarkMode: (Boolean) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ajustes") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Atrás") } }
            )
        }
    ) { padding ->

        Column(Modifier.fillMaxSize().padding(padding).padding(16.dp)) {

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Usar tema del sistema")
                Switch(checked = useSystemTheme, onCheckedChange = onToggleUseSystemTheme)
            }

            Spacer(Modifier.height(12.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Modo oscuro")
                Switch(
                    checked = darkMode,
                    onCheckedChange = onToggleDarkMode,
                    enabled = !useSystemTheme
                )
            }

            Spacer(Modifier.height(8.dp))
            Text(
                text = if (useSystemTheme) "El modo oscuro depende del sistema." else "Modo manual activado.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
