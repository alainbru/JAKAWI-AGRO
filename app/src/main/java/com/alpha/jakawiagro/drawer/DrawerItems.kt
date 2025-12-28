package com.alpha.jakawiagro.drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.alpha.jakawiagro.navigation.Routes

data class DrawerItem(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val route: String
)

val drawerItems = listOf(
    DrawerItem(
        title = "Inicio",
        icon = Icons.Default.Home,
        route = Routes.HOME
    ),
    DrawerItem(
        title = "Parcelas",
        icon = Icons.Default.Map,
        route = Routes.PARCELAS_HOME
    ),
    DrawerItem(
        title = "Heladas",
        icon = Icons.Default.Cloud,
        route = Routes.CLIMA
    ),
    DrawerItem(
        title = "Perfil",
        icon = Icons.Default.Person,
        route = Routes.PERFIL
    ),
    DrawerItem(
        title = "Configuración",
        icon = Icons.Default.Settings,
        route = Routes.SETTINGS
    )
)
