package com.alpha.jakawiagro.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpha.jakawiagro.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthState(
    val loading: Boolean = false,
    val error: String? = null,
    val isLogged: Boolean = false,
    val userId: String? = null
)

class AuthViewModel(
    private val repo: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthState())
    val uiState: StateFlow<AuthState> = _uiState.asStateFlow()

    fun checkSession() {
        val user = repo.currentUser()
        _uiState.value = _uiState.value.copy(
            isLogged = user != null,
            userId = user?.uid
        )
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, error = null)
            try {
                val user = repo.login(email, password)
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    isLogged = true,
                    userId = user.uid
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    error = e.message ?: "Error al iniciar sesión"
                )
            }
        }
    }

    fun register(
        nombreCompleto: String,
        email: String,
        password: String,
        telefono: String? = null,
        region: String? = null
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, error = null)
            try {
                val user = repo.register(nombreCompleto, email, password, telefono, region)
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    isLogged = true,
                    userId = user.uid
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    error = e.message ?: "Error al registrar"
                )
            }
        }
    }

    fun logout() {
        repo.logout()
        _uiState.value = AuthState()
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
    fun resetPassword(email: String) {
        if (email.isBlank()) {
            _uiState.value = _uiState.value.copy(error = "Ingresa tu correo")
            return
        }
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, error = null)
            try {
                repo.resetPassword(email.trim())
                _uiState.value = _uiState.value.copy(loading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(loading = false, error = e.message)
            }
        }
    }

}


