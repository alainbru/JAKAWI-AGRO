import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alpha.jakawiagro.screens.MainTopAppBar
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarioMensualConAnimacion(
    year: Int,
    month: Int,
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit
) {
    val diasSemana = listOf("L", "M", "Mi", "J", "V", "S", "D")
    val primerDiaMes = LocalDate.of(year, month, 1)
    val diasEnMes = primerDiaMes.lengthOfMonth()
    val primerDiaSemana = primerDiaMes.dayOfWeek.value - 1

    Column(modifier = Modifier.padding(16.dp)) {
        // Encabezado de días de semana
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            diasSemana.forEach { diaSemana ->
                Text(
                    text = diaSemana,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Días animados
        val totalCeldas = ((primerDiaSemana + diasEnMes + 6) / 7) * 7
        for (fila in 0 until totalCeldas step 7) {
            Row(modifier = Modifier.fillMaxWidth()) {
                for (columna in 0..6) {
                    val index = fila + columna
                    val dia = index - primerDiaSemana + 1
                    val fechaActual = if (dia in 1..diasEnMes) LocalDate.of(year, month, dia) else null

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clickable(enabled = fechaActual != null) {
                                fechaActual?.let { onDateSelected(it) }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (fechaActual != null) {
                            val isSelected = fechaActual == selectedDate

                            // Animación para el cambio de selección del día
                            AnimatedContent(targetState = isSelected) { selected ->
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(if (selected) Color(0xFF1976D2) else Color.Transparent),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = dia.toString(),
                                        color = if (selected) Color.White else Color.Black,
                                        style = MaterialTheme.typography.bodyLarge,
                                        modifier = Modifier.padding(4.dp)
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
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CalendarioConNavegacionAnimado() {
    var fechaActual by remember { mutableStateOf(LocalDate.now()) }
    var fechaSeleccionada by remember { mutableStateOf<LocalDate?>(null) }
    var mesAnterior by remember { mutableStateOf(fechaActual.monthValue) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                mesAnterior = fechaActual.monthValue
                fechaActual = fechaActual.minusMonths(1)
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Mes anterior")
            }

            AnimatedContent(
                targetState = fechaActual,
                transitionSpec = {
                    if (targetState.monthValue > initialState.monthValue) {
                        slideInHorizontally(animationSpec = tween(300)) { width -> width } + fadeIn() with
                                slideOutHorizontally(animationSpec = tween(300)) { width -> -width } + fadeOut()
                    } else {
                        slideInHorizontally(animationSpec = tween(300)) { width -> -width } + fadeIn() with
                                slideOutHorizontally(animationSpec = tween(300)) { width -> width } + fadeOut()
                    }.using(SizeTransform(clip = false))
                }
            ) { fecha ->
                Text(
                    text = "${fecha.month.getDisplayName(TextStyle.FULL, Locale("es"))} ${fecha.year}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            IconButton(onClick = {
                mesAnterior = fechaActual.monthValue
                fechaActual = fechaActual.plusMonths(1)
            }) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Mes siguiente")
            }
        }

        CalendarioMensualConAnimacion(
            year = fechaActual.year,
            month = fechaActual.monthValue,
            selectedDate = fechaSeleccionada,
            onDateSelected = { fechaSeleccionada = it }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCalendarioAnimado() {
    MaterialTheme {
        Scaffold(
            topBar = {
                MainTopAppBar(
                    title = "CALENDARIO",
                    onMenuClick = {},
                    onProfileClick = {}
                )
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Calendario Animado",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(16.dp)
                    )

                    CalendarioConNavegacionAnimado()
                }
            }
        )
    }
}



