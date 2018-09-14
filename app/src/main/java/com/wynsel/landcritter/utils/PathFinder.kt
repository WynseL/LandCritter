package com.wynsel.landcritter.utils

import android.location.Location
import com.google.android.gms.location.places.Place
import com.google.android.gms.maps.model.LatLng

class PathFinder(private var places : ArrayList<Place>) {

    private fun calculateDistance(from : LatLng, to : LatLng) : Float {
        val result = FloatArray(1)
        Location.distanceBetween(from.latitude, from.longitude, to.latitude, to.longitude, result)
        return result[0]
    }

    fun reload(places : ArrayList<Place>) {
        this.places = places
    }

    fun calculatePathFinder() : ArrayList<CalculatedDistance> {
        val finalPaths = ArrayList<CalculatedDistance>()
        for (i in places.indices) {
            val distances = ArrayList<CalculatedDistance>()
            val placeFrom = places[i]
            for (o in places.indices) {
                if (i == o)
                    continue
                val placeTo = places[o]
                distances.add(CalculatedDistance(i, o,
                        placeFrom.latLng, placeTo.latLng,
                        calculateDistance(placeFrom.latLng, placeTo.latLng)))
            }
            val sorted = distances.sortedWith(compareBy { it.distance })
            if (!distances.isEmpty() && !sorted.isEmpty())
                finalPaths.add(sorted[0])
        }
        return finalPaths
    }
}