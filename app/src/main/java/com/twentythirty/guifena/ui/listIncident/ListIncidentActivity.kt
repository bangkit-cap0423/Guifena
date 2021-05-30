package com.twentythirty.guifena.ui.listIncident

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.twentythirty.guifena.databinding.ActivityListIncidentBinding
import com.twentythirty.guifena.ui.detailIncident.DetailIncident
import com.twentythirty.guifena.ui.home.HomeAdapter
import com.twentythirty.guifena.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListIncidentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListIncidentBinding
    private val homeAdapter = HomeAdapter()
    private val listIncidentViewModel: ListIncidentViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListIncidentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding.rvIncident) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = homeAdapter
        }
        homeAdapter.onItemClick = { data ->
            val intent = Intent(this, DetailIncident::class.java)
            intent.putExtra(DetailIncident.EXTRA_DATA, data)
            startActivity(intent)
        }
        setObservers()
    }

    override fun onResume() {
        listIncidentViewModel.fetchIncidents()
        super.onResume()
    }

    private fun setObservers() {
        listIncidentViewModel.fetchIncidents()
        listIncidentViewModel.incidents.observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { sensor ->
                            sensor
                            if (sensor.isNotEmpty()) {
                                homeAdapter.setIncident(sensor)
                                binding.progressBar2.visibility = View.GONE
                                binding.rvIncident.visibility = View.VISIBLE
                            } else {
                                binding.progressBar2.visibility = View.GONE
                                binding.rvIncident.visibility = View.GONE
                            }

                        }
                    }
                }
            }
        })
    }
}