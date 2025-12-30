import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.screens.temporal.MainTopAppBar
import com.alpha.jakawiagro.ui.theme.JakawiAgroTheme
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PantallaCalendario(onAddCosechaClick: () -> Unit,
                                  onMenuClick: () -> Unit = {},
                                  onProfileClick: () -> Unit = {}) {

    val colorScheme = MaterialTheme.colorScheme

    var fechaActual by remember { mutableStateOf(LocalDate.now()) }
    var fechaSeleccionada by remember { mutableStateOf<LocalDate?>(null) }

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "CALENDARIO"
                ,   onMenuClick = onMenuClick,
                onProfileClick = onProfileClick
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {

            // ───────── Navegación del mes ─────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = {
                    fechaActual = fechaActual.minusMonths(1)
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Mes anterior",
                        tint = colorScheme.primary
                    )
                }

                AnimatedContent(
                    targetState = fechaActual,
                    transitionSpec = {
                        if (targetState.monthValue > initialState.monthValue) {
                            slideInHorizontally { it } + fadeIn() with
                                    slideOutHorizontally { -it } + fadeOut()
                        } else {
                            slideInHorizontally { -it } + fadeIn() with
                                    slideOutHorizontally { it } + fadeOut()
                        }.using(SizeTransform(clip = false))
                    }
                ) { fecha ->
                    Text(
                        text = "${fecha.month.getDisplayName(TextStyle.FULL, Locale("es"))} ${fecha.year}",
                        style = MaterialTheme.typography.titleMedium,
                        color = colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                }

                IconButton(onClick = {
                    fechaActual = fechaActual.plusMonths(1)
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Mes siguiente",
                        tint = colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // ───────── Calendario ─────────
            CalendarioMensualConAnimacion(
                year = fechaActual.year,
                month = fechaActual.monthValue,
                selectedDate = fechaSeleccionada,
                onDateSelected = { fechaSeleccionada = it }
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CalendarioMensualConAnimacion(
    year: Int,
    month: Int,
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    val diasSemana = listOf("L", "M", "Mi", "J", "V", "S", "D")

    val primerDiaMes = LocalDate.of(year, month, 1)
    val diasEnMes = primerDiaMes.lengthOfMonth()
    val primerDiaSemana = primerDiaMes.dayOfWeek.value - 1

    Column(
        modifier = Modifier
            .background(
                color = colorScheme.surface,
                shape = MaterialTheme.shapes.medium
            )
            .padding(12.dp)
    ) {

        // Encabezado días
        Row(modifier = Modifier.fillMaxWidth()) {
            diasSemana.forEach { dia ->
                Text(
                    text = dia,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        val totalCeldas = ((primerDiaSemana + diasEnMes + 6) / 7) * 7

        for (fila in 0 until totalCeldas step 7) {
            Row(modifier = Modifier.fillMaxWidth()) {
                for (columna in 0..6) {

                    val index = fila + columna
                    val dia = index - primerDiaSemana + 1
                    val fecha =
                        if (dia in 1..diasEnMes) LocalDate.of(year, month, dia) else null

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clip(MaterialTheme.shapes.small)
                            .clickable(enabled = fecha != null) {
                                fecha?.let(onDateSelected)
                            },
                        contentAlignment = Alignment.Center
                    ) {

                        if (fecha != null) {
                            val isSelected = fecha == selectedDate

                            AnimatedContent(targetState = isSelected) { selected ->
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (selected)
                                                colorScheme.primary
                                            else
                                                colorScheme.surface
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = dia.toString(),
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = if (selected)
                                            colorScheme.onPrimary
                                        else
                                            colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PantallaCalendarioa() {
    JakawiAgroTheme {
        PantallaCalendario(onAddCosechaClick = {})
    }
}

