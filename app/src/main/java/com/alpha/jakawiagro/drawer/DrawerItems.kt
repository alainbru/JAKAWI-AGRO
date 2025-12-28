package com.alpha.jakawiagro.drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.alpha.jakawiagro.navigation.Routes

data class DrawerItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

val drawerItems = listOf(
    DrawerItem("Inicio", Icons.Default.Home, Routes.HOME),
    DrawerItem("Parcelas", Icons.Default.Map, Routes.PARCELAS),
    DrawerItem("Heladas", Icons.Default.Cloud, Routes.HELADAS),
    DrawerItem("Perfil", Icons.Default.Person, Routes.PERFIL),
    DrawerItem("Configuración", Icons.Default.Settings, Routes.SETTINGS)
)
