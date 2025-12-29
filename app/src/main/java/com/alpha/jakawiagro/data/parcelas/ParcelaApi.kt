package com.alpha.jakawiagro.data.parcelas

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ParcelaApi {

    @POST("parcelas")
    suspend fun create(@Body req: CreateParcelaRequest): ParcelaResponse

    @GET("parcelas/user/{userId}")
    suspend fun listByUser(@Path("userId") userId: String): List<ParcelaResponse>

    @GET("parcelas/{parcelaId}")
    suspend fun getById(@Path("parcelaId") parcelaId: String): ParcelaResponse

    @PUT("parcelas/{parcelaId}")
    suspend fun update(
        @Path("parcelaId") parcelaId: String,
        @Body req: CreateParcelaRequest
    ): ParcelaResponse

    @DELETE("parcelas/{parcelaId}")
    suspend fun delete(@Path("parcelaId") parcelaId: String): String
}

