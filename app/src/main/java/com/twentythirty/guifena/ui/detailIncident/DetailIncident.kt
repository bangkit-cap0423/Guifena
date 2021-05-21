package com.twentythirty.guifena.ui.detailIncident

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.twentythirty.guifena.R
import com.twentythirty.guifena.databinding.ActivityDetailIncidentBinding

class DetailIncident : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_POINT_LOC = "extra_point_loc"
        const val EXTRA_COORDINATE = "extra_coordinate"
        const val EXTRA_STATUS = "extra_status"
        const val EXTRA_SENSOR_NAME = "extra_sensor_name"
        const val EXTRA_TIME = "extra_time"
    }

    private lateinit var binding: ActivityDetailIncidentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailIncidentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val sensorName = intent.getIntExtra(EXTRA_SENSOR_NAME, 0)
        val pointLocation = intent.getStringExtra(EXTRA_POINT_LOC)
        val coordinate = intent.getStringExtra(EXTRA_COORDINATE)
        val status = intent.getIntExtra(EXTRA_STATUS, 0)
        val timeStamp = intent.getStringExtra(EXTRA_TIME)

        binding.apply {
            supportActionBar?.title = "Insiden #$id"

            tvSensorName.text = getString(R.string.sensor_name, sensorName)
            tvPointName.text = pointLocation
            tvCoordinate.text = coordinate
            tvTime.text = timeStamp

            var statusChange = status
            setStatus(statusChange)
            btnStatus.setOnClickListener {
                //set incident status here
                statusChange = 1
                setStatus(statusChange)
            }

            btnDone.setOnClickListener {
                //Destroy Incident here
                finish()
            }
        }
    }

    private fun setStatus(status: Int) {
        binding.apply {
            when (status) {
                0 -> {
                    //set "Perlu Ditangani" Button to visible
                    btnStatus.visibility = View.VISIBLE
                    textStatus.visibility = View.GONE
                }
                1 -> {
                    //set "Selesaikan Penanganan" Button and current status to visible
                    textStatus.visibility = View.VISIBLE
                    btnDone.visibility = View.VISIBLE
                    btnStatus.visibility = View.GONE
                }
            }
        }
    }
}