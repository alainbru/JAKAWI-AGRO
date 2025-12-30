package com.alpha.jakawiagro.data.model

data class UserProfile(
    val uid: String = "",
    val email: String = "",
    val nombreCompleto: String = "",
    val telefono: String? = null,
    val region: String? = null,
    val rol: String = "AGRICULTOR",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
