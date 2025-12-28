package com.alpha.jakawiagro.data.parcelas

data class ParcelaPoint(
    val lat: Double,
    val lng: Double
)

data class Parcela(
    val id: String,
    val nombre: String,
    val puntos: List<ParcelaPoint>
)
