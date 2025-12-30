package com.alpha.jakawiagro.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.launch
import com.alpha.jakawiagro.navigation.Routes
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainShell(
    navController: NavHostController,
    onLogout: () -> Unit,
    content: @Composable (Modifier) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val title = remember(currentRoute) {
        when (currentRoute) {
            Routes.HOME -> "Inicio"
            Routes.PARCELAS_INICIO -> "Parcelas"
            Routes.CLIMA -> "Clima"
            Routes.CALENDARIO -> "Calendario"
            Routes.CULTIVOS -> "Cultivos"
            Routes.PERFIL -> "Perfil"
            Routes.SETTINGS -> "Ajustes"
            else -> "Jakawi Agro"
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawerContent(
                currentRoute = currentRoute,
                onGo = { route ->
                    scope.launch { drawerState.close() }
                    if (route != currentRoute) navController.navigate(route)
                },
                onLogout = {
                    scope.launch { drawerState.close() }
                    onLogout()
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = title.uppercase(),
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        // ✅ más elegante que primary sólido
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menú",
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            if (currentRoute != Routes.PERFIL) navController.navigate(Routes.PERFIL)
                        }) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Perfil",
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                )
            }
        ) { padding ->
            content(Modifier.padding(padding))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppDrawerContent(
    currentRoute: String?,
    onGo: (String) -> Unit,
    onLogout: () -> Unit
) {
    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        Spacer(Modifier.height(12.dp))

        Text(
            text = "Menú",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp),
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(Modifier.height(10.dp))

        NavigationDrawerItem(
            label = { Text("Inicio") },
            selected = currentRoute == Routes.HOME,
            onClick = { onGo(Routes.HOME) },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Cultivos") },
            selected = currentRoute == Routes.CULTIVOS,
            onClick = { onGo(Routes.CULTIVOS) },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Parcelas") },
            selected = currentRoute == Routes.PARCELAS_INICIO,
            onClick = { onGo(Routes.PARCELAS_INICIO) },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Calendario") },
            selected = currentRoute == Routes.CALENDARIO,
            onClick = { onGo(Routes.CALENDARIO) },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Clima") },
            selected = currentRoute == Routes.CLIMA,
            onClick = { onGo(Routes.CLIMA) },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )

        Spacer(Modifier.height(12.dp))
        Divider()
        Spacer(Modifier.height(12.dp))

        NavigationDrawerItem(
            label = { Text("Ajustes") },
            selected = currentRoute == Routes.SETTINGS,
            onClick = { onGo(Routes.SETTINGS) },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Cerrar sesión") },
            selected = false,
            onClick = onLogout,
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )

        Spacer(Modifier.height(12.dp))
    }
}

