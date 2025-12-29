package com.alpha.jakawiagro.data.parcelas

class ParcelaRepository(
    private val api: ParcelaApi
) {
    suspend fun create(req: CreateParcelaRequest): ParcelaResponse = api.create(req)

    suspend fun listByUser(userId: String): List<ParcelaResponse> = api.listByUser(userId)

    suspend fun getById(parcelaId: String): ParcelaResponse = api.getById(parcelaId)

    suspend fun update(parcelaId: String, req: CreateParcelaRequest): ParcelaResponse =
        api.update(parcelaId, req)

    suspend fun delete(parcelaId: String): String = api.delete(parcelaId)
}
