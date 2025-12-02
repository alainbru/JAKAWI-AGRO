package com.alpha.jakawiagro.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// ===================== LIGHT SCHEME =====================
private val LightColorScheme = lightColorScheme(

    primary = Verde,                    // #276a49
    onPrimary = VerdeClaro,
    primaryContainer = VerdeContenedor,
    onPrimaryContainer = VerdeContenedorOscuro,

    secondary = Verde2,
    onSecondary = Verde2Claro,
    secondaryContainer = Verde2Contenedor,
    onSecondaryContainer = Verde2ContenedorOscuro,

    tertiary = Azul,
    onTertiary = AzulClaro,
    tertiaryContainer = AzulContenedor,
    onTertiaryContainer = AzulContenedorOscuro,

    error = RojoError,
    onError = RojoClaro,
    errorContainer = RojoContenedor,
    onErrorContainer = RojoContenedorOscuro,

    background = Fondo,
    onBackground = FondoTexto,
    surface = Superficie,
    onSurface = SuperficieTexto,
    surfaceVariant = SuperficieVariante,
    onSurfaceVariant = SuperficieVarianteTexto,

    outline = Borde,
    outlineVariant = BordeVariante,
    surfaceTint = Tintado,

    inverseSurface = SuperficieInversa,
    inverseOnSurface = SuperficieInversaTexto,
    inversePrimary = VerdeInverso
)


// ===================== DARK SCHEME =====================
private val DarkColorScheme = darkColorScheme(

    primary = VerdeDark,
    onPrimary = VerdeDarkTexto,
    primaryContainer = VerdeDarkContenedor,
    onPrimaryContainer = VerdeDarkContenedorTexto,

    secondary = Verde2Dark,
    onSecondary = Verde2DarkTexto,
    secondaryContainer = Verde2DarkContenedor,
    onSecondaryContainer = Verde2DarkContenedorTexto,

    tertiary = AzulDark,
    onTertiary = AzulDarkTexto,
    tertiaryContainer = AzulDarkContenedor,
    onTertiaryContainer = AzulDarkContenedorTexto,

    error = RojoErrorDark,
    onError = RojoDarkTexto,
    errorContainer = RojoDarkContenedor,
    onErrorContainer = RojoDarkContenedorTexto,

    background = FondoDark,
    onBackground = FondoDarkTexto,
    surface = SuperficieDark,
    onSurface = SuperficieDarkTexto,
    surfaceVariant = SuperficieVarianteDark,
    onSurfaceVariant = SuperficieVarianteDarkTexto,

    outline = BordeDark,
    outlineVariant = BordeVarianteDark,
    surfaceTint = TintadoDark,

    inverseSurface = SuperficieInversaDark,
    inverseOnSurface = SuperficieInversaTextoDark,
    inversePrimary = VerdeInversoDark
)


// ===================== THEME WRAPPER =====================
@Composable
fun JakawiAgroTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
