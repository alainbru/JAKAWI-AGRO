package com.alpha.jakawiagro.screens.clima.data

data class OpenMeteoResponse(
    val latitude: Double?,
    val longitude: Double?,
    val timezone: String?,
    val current: Current?,
    val hourly: Hourly?,
    val daily: Daily?
)

data class Current(
    val time: String?,
    val temperature_2m: Double?,
    val relative_humidity_2m: Int?,
    val wind_speed_10m: Double?
)

data class Hourly(
    val time: List<String> = emptyList(),
    val temperature_2m: List<Double> = emptyList(),
    val precipitation_probability: List<Int> = emptyList()
)

data class Daily(
    val time: List<String> = emptyList(),
    val temperature_2m_max: List<Double> = emptyList(),
    val temperature_2m_min: List<Double> = emptyList(),
    val precipitation_probability_max: List<Int> = emptyList()
)
