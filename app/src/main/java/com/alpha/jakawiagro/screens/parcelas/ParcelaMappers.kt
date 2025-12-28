package com.alpha.jakawiagro.screens.parcelas

import com.alpha.jakawiagro.data.parcelas.ParcelaPoint
import com.google.android.gms.maps.model.LatLng

fun LatLng.toPoint(): ParcelaPoint = ParcelaPoint(lat = latitude, lng = longitude)
fun ParcelaPoint.toLatLng(): LatLng = LatLng(lat, lng)

