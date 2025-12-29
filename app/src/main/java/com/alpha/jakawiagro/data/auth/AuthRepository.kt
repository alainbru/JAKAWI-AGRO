package com.alpha.jakawiagro.data.auth

import android.content.Context
import com.alpha.jakawiagro.storage.TokenDataStore

class AuthRepository(
    private val api: AuthApi,
    private val context: Context
) {

    suspend fun login(email: String, password: String) {
        val response = api.login(LoginRequest(email, password))
        TokenDataStore.saveToken(context, response.token)
    }

    suspend fun register(name: String, email: String, password: String) {
        val response = api.register(RegisterRequest(name, email, password))
        TokenDataStore.saveToken(context, response.token)
    }

    suspend fun logout() {
        TokenDataStore.clear(context)
    }
}
