package com.alpha.jakawiagro.data.repository

import com.alpha.jakawiagro.data.model.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val usersRepo: UsersRepository = UsersRepository()
) {
    fun currentUser(): FirebaseUser? = auth.currentUser

    suspend fun login(email: String, password: String): FirebaseUser {
        val result = auth.signInWithEmailAndPassword(email.trim(), password).await()
        return result.user ?: error("Error al iniciar sesión")
    }

    suspend fun register(
        nombreCompleto: String,
        email: String,
        password: String,
        telefono: String? = null,
        region: String? = null
    ): FirebaseUser {
        val result = auth.createUserWithEmailAndPassword(email.trim(), password).await()
        val user = result.user ?: error("Error al registrar usuario")

        val now = System.currentTimeMillis()
        val profile = UserProfile(
            uid = user.uid,
            email = user.email ?: email.trim(),
            nombreCompleto = nombreCompleto.trim(),
            telefono = telefono?.trim()?.ifBlank { null },
            region = region?.trim()?.ifBlank { null },
            rol = "AGRICULTOR",
            createdAt = now,
            updatedAt = now
        )

        usersRepo.createProfile(profile)
        return user
    }

    fun logout() {
        auth.signOut()
    }
    suspend fun resetPassword(email: String) {
        auth.sendPasswordResetEmail(email.trim()).await()
    }

}

