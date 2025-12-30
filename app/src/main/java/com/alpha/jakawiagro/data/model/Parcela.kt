package com.alpha.jakawiagro.data.model

import com.google.firebase.firestore.GeoPoint

data class Parcela(
    val id: String = "",
    val ownerId: String = "",
    val nombre: String = "",

    // ✅ Polígono: lista de puntos lat/lng
    val puntos: List<GeoPoint> = emptyList(),

    // opcionales
    val areaHa: Double? = null,
    val ubicacion: String? = null,

    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

