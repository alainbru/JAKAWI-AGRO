package com.alpha.jakawiagro.data.parcelas

interface ParcelaRepository {

    suspend fun getParcelas(): List<Parcela>

    suspend fun getParcelaById(id: String): Parcela?

    suspend fun saveParcela(parcela: Parcela)

    suspend fun updateParcela(parcela: Parcela)

    suspend fun deleteParcela(id: String)
}
