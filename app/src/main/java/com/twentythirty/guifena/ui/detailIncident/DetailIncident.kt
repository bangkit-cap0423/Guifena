package com.twentythirty.guifena.ui.detailIncident

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.twentythirty.guifena.R
import com.twentythirty.guifena.data.IncidentEntity
import com.twentythirty.guifena.databinding.ActivityDetailIncidentBinding

class DetailIncident : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailIncidentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailIncidentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<IncidentEntity>(EXTRA_DATA)

        binding.apply {
            supportActionBar?.title = "Insiden #${data?.id}"

            tvSensorName.text = getString(R.string.sensor_name, data?.sensor)
            tvPointName.text = data?.sensorName
            tvCoordinate.text = data?.sensorLocation
            tvTime.text = data?.timestamp

            var statusChange = data?.status
            setStatus(statusChange!!)
            btnStatus.setOnClickListener {
                //set incident status here
                statusChange = 1
                setStatus(statusChange!!)
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