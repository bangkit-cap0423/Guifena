package com.twentythirty.guifena.ui.detailIncident

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.twentythirty.guifena.R
import com.twentythirty.guifena.data.IncidentEntity
import com.twentythirty.guifena.databinding.ActivityDetailIncidentBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DetailIncident : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailIncidentBinding
    private var statusChange: Int? = null
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

            statusChange = data?.status
            setStatus(statusChange!!)
            setOnClikListeners(statusChange!!)

        }
    }

    private fun setOnClikListeners(status: Int) {
        binding.apply {
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
                1 -> {
                    //set "Perlu Ditangani" Button to visible
                    btnStatus.visibility = View.VISIBLE
                    textStatus.visibility = View.GONE
                }
                2 -> {
                    //set "Selesaikan Penanganan" Button and current status to visible
                    textStatus.visibility = View.VISIBLE
                    btnDone.visibility = View.VISIBLE
                    btnStatus.visibility = View.GONE
                }
                3 -> {
                    //set "Selesaikan Penanganan" Button and current status to visible
                    textStatus.text = "Masalah Selesai"
                    textStatus.setTextColor(resources.getColor(R.color.white))
                    textStatus.visibility = View.VISIBLE
                    btnDone.visibility = View.VISIBLE
                    btnStatus.visibility = View.GONE

                }
            }
        }
    }

    private fun getDateTime(s: String): String? {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        var convertedDate: Date? = null
        var formattedDate: String? = null
        try {
            convertedDate = sdf.parse(s)
            formattedDate = SimpleDateFormat("MMMMM dd,yyyy").format(convertedDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return formattedDate
    }
}