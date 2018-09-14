package com.wynsel.landcritter.utils

import android.view.View
import android.view.ViewTreeObserver
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class PrepareMap(private val mapFragment: SupportMapFragment,
                 private val notifier : OnGlobalLayoutAndMapReadyListener) :
        ViewTreeObserver.OnGlobalLayoutListener, OnMapReadyCallback {

    private val mapView: View? = mapFragment.view

    private var map: GoogleMap? = null

    interface OnGlobalLayoutAndMapReadyListener {
        fun onMapReady(googleMap: GoogleMap)
    }

    init {
        registerListeners()
    }

    private fun registerListeners() {
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {
        map = p0 ?: return
        notifier.onMapReady(map!!)
    }

    override fun onGlobalLayout() {

    }


}