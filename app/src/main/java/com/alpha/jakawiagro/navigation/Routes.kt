package com.alpha.jakawiagro.navigation

object Routes {
    // START
    const val SPLASH = "splash"
    const val WELCOME = "welcome"

    // AUTH
    const val LOGIN = "login"
    const val REGISTRO = "registro"
    const val RECUPERAR_CLAVE = "recuperar_clave"

    // HOME
    const val HOME = "home"

    // PARCELAS
    const val PARCELAS_INICIO = "parcelas/inicio"
    const val PARCELAS_LISTA = "parcelas/lista"
    const val PARCELAS_DIBUJAR = "parcelas/dibujar"

    // con parámetros
    const val PARCELAS_DETALLES = "parcelas/detalles/{id}"
    const val PARCELAS_EDITAR = "parcelas/editar/{id}"

    fun detalles(id: String) = "parcelas/detalles/$id"
    fun editar(id: String) = "parcelas/editar/$id"

    // PERFIL / SETTINGS / CLIMA
    const val PERFIL = "perfil"
    const val SETTINGS = "settings"
    const val CLIMA = "clima"
    const val CULTIVOS = "cultivos"
    const val CALENDARIO = "calendario"

}