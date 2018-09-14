package com.wynsel.landcritter.utils

import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceSelectionListener
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment
import com.wynsel.landcritter.database.models.Bounds
import com.wynsel.landcritter.database.models.Coordinates
import com.wynsel.landcritter.database.models.PlaceInformation

class PreparePlaces(private val placesFragment: SupportPlaceAutocompleteFragment,
                    private val listener : OnPlacesListener) : PlaceSelectionListener {

    interface OnPlacesListener {
        fun onSuccess(place: Place?)
        fun onError(status: Status?)
    }

    init {
        registerListeners()
    }

    private fun registerListeners() {
        placesFragment.setOnPlaceSelectedListener(this)
        placesFragment.setHint("Search me pls...")
    }

    override fun onPlaceSelected(p0: Place?) {

        /*
        val latLng = p0?.latLng
        val viewport = p0?.viewport

        val coordinates = Coordinates(latLng?.latitude!!, latLng.longitude)
        val coordinatesSW = Coordinates(viewport?.southwest!!.latitude, viewport.southwest.longitude)
        val coordinatesNE = Coordinates(viewport.northeast!!.latitude, viewport.northeast.longitude)

        PlaceInformation(
                p0.id,
                p0.address.toString(),
                p0.name.toString(),
                coordinates,
                Bounds(coordinatesSW, coordinatesNE)
        )
         */

        listener.onSuccess(p0)
    }

    override fun onError(p0: Status?) {
        listener.onError(p0)
    }
}