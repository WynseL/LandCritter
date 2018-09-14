package com.wynsel.landcritter.utils

import com.google.android.gms.maps.model.LatLng

data class CalculatedDistance(
        val id : Int,
        val pointId : Int,
        val from : LatLng,
        val to : LatLng,
        val distance : Float
)