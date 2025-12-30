package com.alpha.jakawiagro.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

@Composable
fun HomeScreen(
    onGoCultivos: () -> Unit,
    onGoParcelas: () -> Unit,
    onGoCalendario: () -> Unit,
    onGoClima: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    var nombreCompleto by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            val uid = FirebaseAuth.getInstance().currentUser?.uid
            if (uid != null) {
                val snap = FirebaseFirestore.getInstance()
                    .collection("users") // ⚠️ cambia si tu colección tiene otro nombre
                    .document(uid)
                    .get()
                    .await()

                nombreCompleto = snap.getString("nombreCompleto")
            }
        } catch (_: Exception) {
            nombreCompleto = null
        }
    }

    val bg = Brush.verticalGradient(
        colors = listOf(
            colors.background,
            colors.background,
            colors.primary.copy(alpha = 0.08f)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {

        // ===== HEADER BIENVENIDA (más integrado) =====
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = colors.primaryContainer.copy(alpha = 0.55f)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 18.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // ✅ icono grande y alineado (sin cuadro)
                Image(
                    painter = painterResource(id = R.drawable.icono_bienvenida2),
                    contentDescription = "Bienvenida",
                    modifier = Modifier
                        .size(104.dp)
                        .padding(end = 14.dp)
                )

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "BIENVENIDO",
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.onBackground.copy(alpha = 0.65f),
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = (nombreCompleto ?: "AGRICULTOR").uppercase(),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = colors.onBackground
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = "Elige un módulo para empezar 🌱",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colors.onBackground.copy(alpha = 0.72f)
                    )
                }
            }
        }

        Spacer(Modifier.height(22.dp))

        Text(
            text = "Módulos",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.ExtraBold,
            color = colors.onBackground
        )

        Spacer(Modifier.height(14.dp))

        // ===== GRID =====
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                HomeModuleCard(
                    title = "Cultivos",
                    subtitle = "Control y seguimiento",
                    iconRes = R.drawable.icono_cultivos,
                    accent = colors.primary,
                    onClick = onGoCultivos,
                    modifier = Modifier.weight(1f)
                )
                HomeModuleCard(
                    title = "Parcelas",
                    subtitle = "Mapa y puntos",
                    iconRes = R.drawable.icono_parcelas,
                    accent = colors.tertiary,
                    onClick = onGoParcelas,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                HomeModuleCard(
                    title = "Calendario",
                    subtitle = "Tareas y recordatorios",
                    iconRes = R.drawable.icono_calendario, // si no existe, cambia por el que usabas antes
                    accent = colors.secondary,
                    onClick = onGoCalendario,
                    modifier = Modifier.weight(1f)
                )
                HomeModuleCard(
                    title = "Clima",
                    subtitle = "Alertas y pronóstico",
                    iconRes = R.drawable.icono_clima, // si no existe, cambia por el que usabas antes
                    accent = colors.primary,
                    onClick = onGoClima,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        Text(
            text = "Tip: usa ☰ para navegar rápido",
            style = MaterialTheme.typography.bodySmall,
            color = colors.onBackground.copy(alpha = 0.55f),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun HomeModuleCard(
    title: String,
    subtitle: String,
    iconRes: Int,
    accent: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme

    val cardBg = Brush.verticalGradient(
        colors = listOf(
            accent.copy(alpha = 0.14f),
            colors.surface
        )
    )

    Card(
        modifier = modifier
            .height(162.dp)
            .shadow(10.dp, RoundedCornerShape(28.dp))
            .clip(RoundedCornerShape(28.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = colors.surface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(cardBg)
        ) {
            // ✅ barra de acento superior (pro)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(accent.copy(alpha = 0.55f))
                    .align(Alignment.TopCenter)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // ✅ iconos consistentes: todos en badge circular suave
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(accent.copy(alpha = 0.18f)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = title,
                        modifier = Modifier.size(36.dp)
                    )
                }

                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = colors.onSurface
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.onSurface.copy(alpha = 0.72f)
                    )
                }
            }
        }
    }
}


