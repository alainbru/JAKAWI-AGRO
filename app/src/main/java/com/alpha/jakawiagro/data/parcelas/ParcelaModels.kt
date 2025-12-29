package com.alpha.jakawiagro.data.parcelas

data class ParcelaPuntoDto(
    val orden: Int,
    val lat: Double,
    val lng: Double
)

data class ParcelaResponse(
    val id: String,
    val userId: String,
    val nombre: String,
    val puntos: List<ParcelaPuntoDto>
)

data class CreateParcelaRequest(
    val userId: String,
    val nombre: String,
    val puntos: List<ParcelaPuntoDto>
)
