package com.wynsel.landcritter.activities

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.wynsel.landcritter.R
import com.wynsel.landcritter.database.CritterDatabase
import com.wynsel.landcritter.http.CallService
import com.wynsel.landcritter.http.RestClient
import com.wynsel.landcritter.utils.PathFinder
import com.wynsel.landcritter.utils.PrepareMap
import com.wynsel.landcritter.utils.PreparePlaces
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity(),
        PrepareMap.OnGlobalLayoutAndMapReadyListener,
        PreparePlaces.OnPlacesListener {

    private lateinit var googleMap: GoogleMap
    private var placeList: ArrayList<Place> = ArrayList()
    private lateinit var callService: CallService

    override fun onSuccess(place: Place?) {
        refresh(place)
    }

    override fun onError(status: Status?) {

    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        this.callService = RestClient.callService!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CritterDatabase.getInstance(this)
        RestClient.init()

        val supportMapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        val autocompleteFragment = supportFragmentManager.findFragmentById(R.id.searchPlacesFragment) as SupportPlaceAutocompleteFragment
        PrepareMap(supportMapFragment, this)
        PreparePlaces(autocompleteFragment, this)
    }

    private fun refresh(place: Place?) {
        placeList.add(place!!)

        googleMap.clear()

        placeList.forEach {
            val latLng = it.latLng!!
            googleMap.addMarker(MarkerOptions().position(latLng))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
        }
        calculate()
    }

    private fun calculate() {
        val pathFinder = PathFinder(placeList)
        pathFinder.calculatePathFinder().forEach {
            googleMap.addPolyline(PolylineOptions().add(it.from, it.to).width(5f).color(Color.BLUE))
        }
    }

    private fun xx() {
        val first = placeList.first().id
        val last = placeList.last().id

        val query = HashMap<String, String>()
        query["origin"] = "place_id:$first"
        query["destination"] = "place_id:$last"
        query["key"] = resources.getString(R.string.dir_api)

        callService.search(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> Log.e("asdad", result.result) },
                        { error -> Log.e("asdad", error.message) }
                )
    }
}
