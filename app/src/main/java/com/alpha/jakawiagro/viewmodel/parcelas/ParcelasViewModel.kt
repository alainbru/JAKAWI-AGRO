package com.alpha.jakawiagro.viewmodel.parcelas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpha.jakawiagro.data.model.Parcela
import com.alpha.jakawiagro.data.repository.ParcelasRepository
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.GeoPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ParcelasState(
    val loading: Boolean = false,
    val error: String? = null,
    val parcelas: List<Parcela> = emptyList()
)

class ParcelasViewModel(
    private val repo: ParcelasRepository = ParcelasRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ParcelasState())
    val uiState: StateFlow<ParcelasState> = _uiState.asStateFlow()

    fun cargarParcelas(ownerId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, error = null)
            try {
                val list = repo.obtenerParcelas(ownerId)
                _uiState.value = _uiState.value.copy(loading = false, parcelas = list)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    error = e.message ?: "Error al cargar parcelas"
                )
            }
        }
    }

    fun crearParcela(
        ownerId: String,
        nombre: String,
        puntosLatLng: List<LatLng>,
        areaHa: Double? = null,
        ubicacion: String? = null
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, error = null)
            try {
                val puntos: List<GeoPoint> =
                    puntosLatLng.map { GeoPoint(it.latitude, it.longitude) }

                val parcela = Parcela(
                    ownerId = ownerId,
                    nombre = nombre.trim(),
                    puntos = puntos,
                    areaHa = areaHa,
                    ubicacion = ubicacion?.trim()?.ifBlank { null }
                )

                repo.crearParcela(parcela)
                cargarParcelas(ownerId)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    error = e.message ?: "Error al crear parcela"
                )
            }
        }
    }

    fun actualizarParcela(parcela: Parcela) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, error = null)
            try {
                repo.actualizarParcela(parcela)
                cargarParcelas(parcela.ownerId)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    error = e.message ?: "Error al actualizar parcela"
                )
            }
        }
    }

    fun eliminarParcela(id: String, ownerId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, error = null)
            try {
                repo.eliminarParcela(id)
                cargarParcelas(ownerId)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    error = e.message ?: "Error al eliminar parcela"
                )
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}


