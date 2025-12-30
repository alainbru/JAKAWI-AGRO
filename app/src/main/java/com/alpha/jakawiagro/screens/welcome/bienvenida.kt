package com.alpha.jakawiagro.screens.welcome

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.R

@Composable
fun PantallaBienvenidaHakwai(
    onContinue: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    // Animación suave: flotación del logo (como “friendly”)
    val transition = rememberInfiniteTransition(label = "welcomeAnim")
    val floatY by transition.animateFloat(
        initialValue = 0f,
        targetValue = -10f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "floatY"
    )

    // Fondo tipo la guía: degradado vertical
    val bg = Brush.verticalGradient(
        colors = listOf(
            colors.primary.copy(alpha = 0.95f),
            colors.primary.copy(alpha = 0.75f),
            colors.background
        )
    )

    Scaffold(containerColor = colors.background) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(bg)
        ) {

            // Decoración “montañas/nubes” estilo flat
            WelcomeIllustration(
                modifier = Modifier.fillMaxSize(),
                primary = colors.primary,
                secondary = colors.secondary,
                onPrimary = colors.onPrimary,
                surface = colors.surface
            )

            // Contenido principal centrado
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // Logo en “badge” como la imagen guía
                Card(
                    shape = RoundedCornerShape(26.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = colors.surface.copy(alpha = 0.95f)
                    ),
                    modifier = Modifier
                        .size(150.dp)
                        .shadow(18.dp, RoundedCornerShape(26.dp))
                        .graphicsLayer { translationY = floatY }
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.icono),
                            contentDescription = "Jakawi Agro",
                            modifier = Modifier.size(92.dp)
                        )
                    }
                }

                Spacer(Modifier.height(26.dp))

                // Título estilo guía (grande y limpio)
                Text(
                    text = "Bienvenido a Jakawi Agro",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = colors.onPrimary,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(10.dp))

                // Descripción
                Text(
                    text = "Tu asistente para decisiones agrícolas inteligentes.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = colors.onPrimary.copy(alpha = 0.85f),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(36.dp))

                // Botón “pill” como la imagen guía
                Button(
                    onClick = onContinue,
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.surface,
                        contentColor = colors.primary
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(52.dp)
                        .shadow(10.dp, RoundedCornerShape(50))
                ) {
                    Text(
                        text = "Continuar",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

/**
 * Ilustración estilo “welcome” (montañas + nubes) en Canvas
 * No usa imágenes externas (más pro y consistente con theme).
 */
@Composable
private fun WelcomeIllustration(
    modifier: Modifier,
    primary: Color,
    secondary: Color,
    onPrimary: Color,
    surface: Color
) {
    // Animación mínima de nubes (super sutil)
    val transition = rememberInfiniteTransition(label = "clouds")
    val cloudShift by transition.animateFloat(
        initialValue = 0f,
        targetValue = 28f,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "cloudShift"
    )

    Canvas(modifier = modifier) {
        drawTopClouds(onPrimary.copy(alpha = 0.10f), cloudShift)
        drawMountains(
            base = primary.copy(alpha = 0.35f),
            mid = secondary.copy(alpha = 0.55f),
            top = secondary.copy(alpha = 0.75f),
            highlight = onPrimary.copy(alpha = 0.22f)
        )
        drawBottomClouds(surface.copy(alpha = 0.95f), surface.copy(alpha = 0.85f))
    }
}

private fun DrawScope.drawTopClouds(color: Color, shift: Float) {
    // Pequeñas nubes arriba tipo “flat”
    val y = size.height * 0.20f
    val w = size.width
    fun cloud(x: Float, y: Float, s: Float) {
        drawRoundRect(
            color = color,
            topLeft = Offset(x, y),
            size = Size(w * 0.18f * s, size.height * 0.055f * s),
            cornerRadius = CornerRadius(40f, 40f)
        )
        drawCircle(color, radius = (w * 0.04f * s), center = Offset(x + w * 0.03f * s, y + size.height * 0.03f * s))
        drawCircle(color, radius = (w * 0.05f * s), center = Offset(x + w * 0.07f * s, y + size.height * 0.02f * s))
        drawCircle(color, radius = (w * 0.04f * s), center = Offset(x + w * 0.12f * s, y + size.height * 0.03f * s))
    }
    cloud(w * 0.16f + shift, y, 1.0f)
    cloud(w * 0.62f - shift, y + size.height * 0.05f, 0.9f)
    cloud(w * 0.38f + shift * 0.5f, y + size.height * 0.10f, 0.75f)
}

private fun DrawScope.drawMountains(
    base: Color,
    mid: Color,
    top: Color,
    highlight: Color
) {
    val w = size.width
    val h = size.height

    // Montaña grande central
    val big = Path().apply {
        moveTo(w * 0.18f, h * 0.58f)
        lineTo(w * 0.50f, h * 0.30f)
        lineTo(w * 0.82f, h * 0.58f)
        close()
    }
    drawPath(big, top)

    // Montañas laterales
    val left = Path().apply {
        moveTo(w * 0.02f, h * 0.62f)
        lineTo(w * 0.28f, h * 0.40f)
        lineTo(w * 0.48f, h * 0.62f)
        close()
    }
    drawPath(left, mid)

    val right = Path().apply {
        moveTo(w * 0.52f, h * 0.64f)
        lineTo(w * 0.72f, h * 0.45f)
        lineTo(w * 0.98f, h * 0.64f)
        close()
    }
    drawPath(right, mid)

    // Base suave
    drawRoundRect(
        color = base,
        topLeft = Offset(0f, h * 0.60f),
        size = Size(w, h * 0.20f),
        cornerRadius = CornerRadius(80f, 80f)
    )

    // Highlights tipo “nieve” sobre montañas
    val snow = Path().apply {
        moveTo(w * 0.44f, h * 0.35f)
        lineTo(w * 0.50f, h * 0.30f)
        lineTo(w * 0.56f, h * 0.35f)
        lineTo(w * 0.52f, h * 0.38f)
        close()
    }
    drawPath(snow, highlight)
}

private fun DrawScope.drawBottomClouds(main: Color, alt: Color) {
    val w = size.width
    val h = size.height

    // Nubes abajo (grandes) como la guía
    fun blob(cx: Float, cy: Float, r: Float, c: Color) = drawCircle(c, r, Offset(cx, cy))

    val baseY = h * 0.86f

    // capa 1
    blob(w * 0.12f, baseY, w * 0.22f, main)
    blob(w * 0.36f, baseY + 10f, w * 0.25f, main)
    blob(w * 0.62f, baseY, w * 0.24f, main)
    blob(w * 0.86f, baseY + 14f, w * 0.22f, main)

    // capa 2 (para profundidad)
    blob(w * 0.22f, baseY + 46f, w * 0.26f, alt)
    blob(w * 0.52f, baseY + 56f, w * 0.30f, alt)
    blob(w * 0.84f, baseY + 54f, w * 0.26f, alt)

    // rect base para tapar abajo
    drawRect(
        color = alt,
        topLeft = Offset(0f, h * 0.90f),
        size = Size(w, h * 0.10f)
    )
}

