package com.alpha.jakawiagro.drawer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppDrawer(
    currentRoute: String?,
    onItemClick: (String) -> Unit
) {
    ModalDrawerSheet {

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Jakawi Agro 🌱",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        drawerItems.forEach { item ->
            NavigationDrawerItem(
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = { onItemClick(item.route) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                modifier = Modifier.padding(
                    NavigationDrawerItemDefaults.ItemPadding
                )
            )
        }
    }
}
