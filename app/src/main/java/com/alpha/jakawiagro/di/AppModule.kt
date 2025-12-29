package com.alpha.jakawiagro.di

import android.content.Context
import com.alpha.jakawiagro.data.auth.AuthApi
import com.alpha.jakawiagro.data.auth.AuthRepository
import com.alpha.jakawiagro.data.parcelas.ParcelaApi
import com.alpha.jakawiagro.data.parcelas.ParcelaRepository
import com.alpha.jakawiagro.network.ApiClient
import com.alpha.jakawiagro.storage.TokenStore

object AppModule {

    // 1) Token store (DataStore / SharedPreferences según tu implementación)
    fun tokenStore(context: Context): TokenStore = TokenStore(context)

    // 2) Retrofit base (con interceptor que mete Bearer token)
    private fun retrofit(context: Context) =
        ApiClient.create(tokenProvider = { tokenStore(context).getToken() })

    // 3) APIs
    fun authApi(context: Context): AuthApi =
        retrofit(context).create(AuthApi::class.java)

    fun parcelaApi(context: Context): ParcelaApi =
        retrofit(context).create(ParcelaApi::class.java)

    // 4) Repos
    fun authRepository(context: Context): AuthRepository =
        AuthRepository(api = authApi(context), tokenStore = tokenStore(context))

    fun parcelaRepository(context: Context): ParcelaRepository =
        ParcelaRepository(api = parcelaApi(context))
}
