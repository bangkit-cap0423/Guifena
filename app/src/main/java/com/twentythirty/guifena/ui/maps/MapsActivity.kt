package com.twentythirty.guifena.ui.maps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.twentythirty.guifena.R
import com.twentythirty.guifena.ui.sensor.SensorViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private val sensorViewModel: SensorViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        supportActionBar?.title = "Peta Sensor"
    }

    override fun onMapReady(googleMap: GoogleMap) {

        setMarkers(googleMap)
    }

    private fun setMarkers(googleMap: GoogleMap) {
        lifecycleScope.launch {
            val sensors = sensorViewModel.getSensorAsync()
            if (sensors.isNotEmpty()) {
                val latlngArray: List<String> = sensors[0].location.split(",").map { it.trim() }
                val latLng = LatLng(latlngArray[0].toDouble(), latlngArray[1].toDouble())
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15F))
            }
            for (sensor in sensors) {
                val latlngArray: List<String> = sensor.location.split(",").map { it.trim() }
                val latLng = LatLng(latlngArray[0].toDouble(), latlngArray[1].toDouble())
                googleMap.addMarker(
                    MarkerOptions()
                        .position(latLng)
                        .title(sensor.nama)
                )
            }
        }
    }
}