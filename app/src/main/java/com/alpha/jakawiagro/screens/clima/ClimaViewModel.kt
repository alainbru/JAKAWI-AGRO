package com.alpha.jakawiagro.screens.clima

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpha.jakawiagro.screens.clima.data.OpenMeteoClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ClimaUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val lugar: String = "Puno",
    val lat: Double = -15.8402,
    val lon: Double = -70.0219,

    val tempC: Int? = null,
    val humedad: Int? = null,
    val vientoKmh: Int? = null,

    val pronosticoDias: List<DayForecast> = emptyList()
)

data class DayForecast(
    val day: String,
    val tMin: Int,
    val tMax: Int,
    val lluviaMax: Int
)

class ClimaViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ClimaUiState())
    val uiState: StateFlow<ClimaUiState> = _uiState

    fun setLugar(lugar: LugarPreset) {
        _uiState.value = _uiState.value.copy(
            lugar = lugar.nombre,
            lat = lugar.lat,
            lon = lugar.lon
        )
        cargar()
    }

    fun cargar() {
        val s = _uiState.value
        viewModelScope.launch {
            _uiState.value = s.copy(loading = true, error = null)

            try {
                val res = OpenMeteoClient.api.forecast(lat = s.lat, lon = s.lon)

                val temp = res.current?.temperature_2m?.toInt()
                val hum = res.current?.relative_humidity_2m
                val wind = res.current?.wind_speed_10m?.toInt()

                val daily = res.daily
                val days = buildList {
                    val n = minOf(
                        daily?.time?.size ?: 0,
                        daily?.temperature_2m_min?.size ?: 0,
                        daily?.temperature_2m_max?.size ?: 0,
                        daily?.precipitation_probability_max?.size ?: 0
                    )
                    for (i in 0 until minOf(n, 5)) {
                        add(
                            DayForecast(
                                day = daily!!.time[i],
                                tMin = daily.temperature_2m_min[i].toInt(),
                                tMax = daily.temperature_2m_max[i].toInt(),
                                lluviaMax = daily.precipitation_probability_max[i]
                            )
                        )
                    }
                }

                _uiState.value = _uiState.value.copy(
                    loading = false,
                    tempC = temp,
                    humedad = hum,
                    vientoKmh = wind,
                    pronosticoDias = days
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    error = e.message ?: "No se pudo cargar el clima"
                )
            }
        }
    }
}

data class LugarPreset(val nombre: String, val lat: Double, val lon: Double)

val LUGARES_PERU = listOf(
    LugarPreset("Puno", -15.8402, -70.0219),
    LugarPreset("Cusco", -13.5319, -71.9675),
    LugarPreset("Arequipa", -16.4090, -71.5375),
    LugarPreset("Huancayo", -12.0651, -75.2049),
    LugarPreset("Ayacucho", -13.1588, -74.2232)
)
