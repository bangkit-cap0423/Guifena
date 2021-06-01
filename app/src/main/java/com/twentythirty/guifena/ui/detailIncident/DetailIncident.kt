package com.twentythirty.guifena.ui.detailIncident

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.twentythirty.guifena.R
import com.twentythirty.guifena.data.IncidentEntity
import com.twentythirty.guifena.databinding.ActivityDetailIncidentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DetailIncident : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailIncidentBinding
    private var statusChange: Int? = null
    private val detailIncidentViewModel: DetailIncidentViewModel by viewModel()
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
            tvTime.text = getDateTime(data?.timestamp)

            statusChange = data?.status
            setStatus(statusChange!!, data!!.id)
            setOnClikListeners(statusChange!!, data)

        }
    }

    private fun setOnClikListeners(status: Int, incident: IncidentEntity) {
        binding.apply {
            btnStatus.setOnClickListener {
                //set incident status here
                statusChange = 2
                setStatus(statusChange!!, incident.id)
            }

            btnDone.setOnClickListener {
                statusChange = 3
                setStatus(statusChange!!, incident.id)
                finish()
            }
        }

    }

    private fun setStatus(status: Int, id: Int) {
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
                    detailIncidentViewModel.changeStatus(2, id)
                }
                3 -> {
                    //set "Selesaikan Penanganan" Button and current status to visible
                    textStatus.text = "Masalah Selesai"
                    textStatus.setTextColor(
                        ContextCompat.getColor(
                            this@DetailIncident,
                            R.color.white
                        )
                    )
                    textStatus.setBackgroundColor(
                        ContextCompat.getColor(
                            this@DetailIncident,
                            R.color.green
                        )
                    )
                    textStatus.visibility = View.VISIBLE
                    btnDone.visibility = View.GONE
                    btnStatus.visibility = View.GONE
                    detailIncidentViewModel.changeStatus(3, id)

                }
            }
        }
    }

    private fun getDateTime(s: String?): String? {
        lateinit var dates: String
        try {
            val f: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ")
            val d: Date = f.parse(s)
            val date: DateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id"))
            val time: DateFormat = SimpleDateFormat("hh:mm a")
            dates = "${time.format(d)} - ${date.format(d)}"
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return dates
    }
}