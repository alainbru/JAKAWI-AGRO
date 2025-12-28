package com.alpha.jakawiagro.viewmodel.parcelas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpha.jakawiagro.data.parcelas.Parcela
import com.alpha.jakawiagro.data.parcelas.ParcelaPoint
import com.alpha.jakawiagro.data.parcelas.ParcelaRepository
import com.alpha.jakawiagro.data.parcelas.ParcelaRepositoryFake
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

data class ParcelasUiState(
    val isLoading: Boolean = false,
    val error: String? = null,

    // lista general
    val parcelas: List<Parcela> = emptyList(),

    // puntos que el usuario está dibujando
    val drawingPoints: List<ParcelaPoint> = emptyList(),

    // parcela seleccionada (detalle/editar)
    val selected: Parcela? = null
)

class ParcelasViewModel(
    // ✅ aquí está la clave: usar fake por defecto
    private val repo: ParcelaRepository = ParcelaRepositoryFake()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ParcelasUiState(isLoading = true))
    val uiState: StateFlow<ParcelasUiState> = _uiState.asStateFlow()

    init {
        loadParcelas()
    }

    fun loadParcelas() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            runCatching {
                repo.getParcelas()
            }.onSuccess { list ->
                _uiState.value = _uiState.value.copy(isLoading = false, parcelas = list)
            }.onFailure { e ->
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message ?: "Error")
            }
        }
    }

    // ====== DIBUJO ======
    fun addPoint(lat: Double, lng: Double) {
        val current = _uiState.value.drawingPoints
        _uiState.value = _uiState.value.copy(
            drawingPoints = current + ParcelaPoint(lat, lng)
        )
    }

    fun clearDrawing() {
        _uiState.value = _uiState.value.copy(drawingPoints = emptyList())
    }

    // ====== CRUD ======
    fun saveDrawingAsParcela(nombre: String) {
        val puntos = _uiState.value.drawingPoints
        if (puntos.size < 3) return

        val parcela = Parcela(
            id = UUID.randomUUID().toString(),
            nombre = nombre.ifBlank { "Parcela ${_uiState.value.parcelas.size + 1}" },
            puntos = puntos
        )

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            runCatching { repo.saveParcela(parcela) }
                .onSuccess {
                    clearDrawing()
                    loadParcelas()
                }
                .onFailure { e ->
                    _uiState.value = _uiState.value.copy(isLoading = false, error = e.message ?: "Error")
                }
        }
    }

    fun selectParcela(id: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            runCatching { repo.getParcelaById(id) }
                .onSuccess { parcela ->
                    _uiState.value = _uiState.value.copy(isLoading = false, selected = parcela)
                }
                .onFailure { e ->
                    _uiState.value = _uiState.value.copy(isLoading = false, error = e.message ?: "Error")
                }
        }
    }

    fun updateSelected(nombre: String, puntos: List<ParcelaPoint>) {
        val current = _uiState.value.selected ?: return

        val updated = current.copy(
            nombre = nombre.ifBlank { current.nombre },
            puntos = if (puntos.size >= 3) puntos else current.puntos
        )

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            runCatching { repo.updateParcela(updated) }
                .onSuccess {
                    _uiState.value = _uiState.value.copy(selected = updated)
                    loadParcelas()
                }
                .onFailure { e ->
                    _uiState.value = _uiState.value.copy(isLoading = false, error = e.message ?: "Error")
                }
        }
    }
    fun updateParcelaName(id: String, newName: String) {
        val name = newName.trim()
        if (name.isBlank()) return

        viewModelScope.launch {
            val current = repo.getParcelaById(id) ?: return@launch
            repo.updateParcela(current.copy(nombre = name))
            loadParcelas()
            selectParcela(id)
        }
    }

    fun deleteParcela(id: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            runCatching { repo.deleteParcela(id) }
                .onSuccess { loadParcelas() }
                .onFailure { e ->
                    _uiState.value = _uiState.value.copy(isLoading = false, error = e.message ?: "Error")
                }
        }
    }
}
