package com.ar.sihproject

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Maps : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val location8 = LatLng(23.4810, 77.7399)
        googleMap.addMarker(MarkerOptions().position(location8).title("Sanchi Stupa"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location8, 4f))

        val location= LatLng(27.1751, 78.0421)
        googleMap.addMarker(MarkerOptions().position(location).title("Taj Mahal"))

        val location1 = LatLng(31.618664192, 74.872829842)
        googleMap.addMarker(MarkerOptions().position(location1).title("Golden Temple"))

        val location2 = LatLng(9.9195, 78.1193)
        googleMap.addMarker(MarkerOptions().position(location2).title("Meenakshi Temple"))

        val location3 = LatLng(12.3052, 76.6552)
        googleMap.addMarker(MarkerOptions().position(location3).title("Mysore Palace"))

        val location4 = LatLng(18.9220, 72.8347)
        googleMap.addMarker(MarkerOptions().position(location4).title("Gateway of India"))

        val location5 = LatLng(28.6562, 77.2410)
        googleMap.addMarker(MarkerOptions().position(location5).title("Red Fort"))

        val location6 = LatLng(26.9239, 75.8267)
        googleMap.addMarker(MarkerOptions().position(location6).title("Hawa Mahal"))

        val location7 = LatLng(28.5245, 77.1855)
        googleMap.addMarker(MarkerOptions().position(location7).title("Qutub Minar"))

        val location9 = LatLng(17.3616, 78.4747)
        googleMap.addMarker(MarkerOptions().position(location9).title("Charminar"))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}