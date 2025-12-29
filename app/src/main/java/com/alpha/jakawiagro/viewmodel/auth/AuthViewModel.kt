package com.alpha.jakawiagro.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpha.jakawiagro.data.auth.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)

class AuthViewModel(
    private val repo: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    fun login(email: String, password: String) {
        _uiState.value = AuthUiState(loading = true)
        viewModelScope.launch {
            try {
                repo.login(email, password)
                _uiState.value = AuthUiState(success = true)
            } catch (e: Exception) {
                _uiState.value = AuthUiState(error = e.message ?: "Error en login")
            }
        }
    }

    fun register(name: String, email: String, password: String) {
        _uiState.value = AuthUiState(loading = true)
        viewModelScope.launch {
            try {
                repo.register(name, email, password)
                _uiState.value = AuthUiState(success = true)
            } catch (e: Exception) {
                _uiState.value = AuthUiState(error = e.message ?: "Error en registro")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repo.logout()
            _uiState.value = AuthUiState()
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}
