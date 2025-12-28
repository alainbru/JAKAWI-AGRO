package com.alpha.jakawiagro.data.parcelas

import kotlinx.coroutines.delay

/**
 * Repositorio FAKE (memoria) para probar el flujo.
 * Luego lo reemplazas por uno real conectado a Oracle (API).
 */
class ParcelaRepositoryFake : ParcelaRepository {

    private val data = mutableListOf<Parcela>()

    override suspend fun getParcelas(): List<Parcela> {
        delay(150) // simula red / BD
        return data.toList()
    }

    override suspend fun getParcelaById(id: String): Parcela? {
        delay(80)
        return data.firstOrNull { it.id == id }
    }

    override suspend fun saveParcela(parcela: Parcela) {
        delay(120)
        data.add(parcela)
    }

    override suspend fun updateParcela(parcela: Parcela) {
        delay(120)
        val index = data.indexOfFirst { it.id == parcela.id }
        if (index != -1) data[index] = parcela
    }

    override suspend fun deleteParcela(id: String) {
        delay(120)
        data.removeAll { it.id == id }
    }
}
