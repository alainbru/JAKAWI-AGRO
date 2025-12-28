package com.alpha.jakawiagro.navigation

object Routes {

    // ---------- AUTH ----------
    const val WELCOME = "welcome"
    const val LOGIN = "login"
    const val REGISTER = "register"

    const val FORGOT = "forgot"

    // ---------- MAIN ----------
    const val HOME = "home"
    const val PARCELAS = "parcelas"

    const val CLIMA = "heladas"
    const val PERFIL = "perfil"
    const val SETTINGS = "settings"

    // ---------- PARCELAS (flujo de 5 screens) ----------
    const val PARCELAS_HOME = "parcelas/home"
    const val PARCELAS_DRAW = "parcelas/draw"
    const val PARCELAS_LIST = "parcelas/list"

    const val PARCELA_DETAIL = "parcelas/detail/{id}"
    const val PARCELA_EDIT = "parcelas/edit/{id}"
    const val PARCELAS_GRAPH = "parcelas_graph"


    fun parcelaDetailRoute(id: String) = "parcelas/detail/$id"
    fun parcelaEditRoute(id: String) = "parcelas/edit/$id"
}

