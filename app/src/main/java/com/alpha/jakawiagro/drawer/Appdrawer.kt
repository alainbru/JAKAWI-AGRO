package com.alpha.jakawiagro.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppDrawer(
    currentRoute: String?,
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {

        /* ---------- HEADER ---------- */
        Text(
            text = "Jakawi Agro",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(24.dp))

        /* ---------- ITEMS ---------- */
        drawerItems.forEach { item ->

            val selected = currentRoute == item.route

            NavigationDrawerItem(
                label = { Text(item.title) },
                icon = { Icon(item.icon, contentDescription = item.title) },
                selected = selected,
                onClick = { onItemClick(item.route) },
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}
