package com.alpha.jakawiagro.viewmodel.parcelas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpha.jakawiagro.data.parcelas.CreateParcelaRequest
import com.alpha.jakawiagro.data.parcelas.ParcelaRepository
import com.alpha.jakawiagro.data.parcelas.ParcelaResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ParcelasUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val parcelas: List<ParcelaResponse> = emptyList(),
    val selected: ParcelaResponse? = null
)

class ParcelasViewModel(
    private val repo: ParcelaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ParcelasUiState())
    val uiState: StateFlow<ParcelasUiState> = _uiState

    fun listar(userId: String) {
        _uiState.value = _uiState.value.copy(loading = true, error = null)
        viewModelScope.launch {
            try {
                val data = repo.listByUser(userId)
                _uiState.value = _uiState.value.copy(loading = false, parcelas = data)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    error = e.message ?: "Error listando parcelas"
                )
            }
        }
    }

    fun detalle(parcelaId: String) {
        _uiState.value = _uiState.value.copy(loading = true, error = null)
        viewModelScope.launch {
            try {
                val p = repo.getById(parcelaId)
                _uiState.value = _uiState.value.copy(loading = false, selected = p)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    error = e.message ?: "Error obteniendo detalle"
                )
            }
        }
    }

    fun crear(req: CreateParcelaRequest, onDone: (() -> Unit)? = null) {
        _uiState.value = _uiState.value.copy(loading = true, error = null)
        viewModelScope.launch {
            try {
                repo.create(req)
                _uiState.value = _uiState.value.copy(loading = false)
                onDone?.invoke()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    error = e.message ?: "Error creando parcela"
                )
            }
        }
    }

    fun actualizar(parcelaId: String, req: CreateParcelaRequest, onDone: (() -> Unit)? = null) {
        _uiState.value = _uiState.value.copy(loading = true, error = null)
        viewModelScope.launch {
            try {
                val updated = repo.update(parcelaId, req)
                _uiState.value = _uiState.value.copy(loading = false, selected = updated)
                onDone?.invoke()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    error = e.message ?: "Error actualizando parcela"
                )
            }
        }
    }

    fun eliminar(parcelaId: String, onDone: (() -> Unit)? = null) {
        _uiState.value = _uiState.value.copy(loading = true, error = null)
        viewModelScope.launch {
            try {
                repo.delete(parcelaId)
                _uiState.value = _uiState.value.copy(loading = false, selected = null)
                onDone?.invoke()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    error = e.message ?: "Error eliminando parcela"
                )
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}
