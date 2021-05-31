package com.twentythirty.guifena.ui.maps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.twentythirty.guifena.R
import com.twentythirty.guifena.ui.sensor.SensorViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private val sensorViewModel: SensorViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-7.393174, 109.258939), 15F))
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(-7.393174, 109.258939))
                .title("Marker")
        )
        //  setMarkers(googleMap)
    }

    private fun setMarkers(googleMap: GoogleMap) {
        /*  lifecycleScope.launch {
              val sensors = sensorViewModel.getSensorAsync()
              for (sensor in sensors) {
                  googleMap.addMarker(
                      MarkerOptions()
                          .pos
                  )
              }
          }*/
    }
}