package com.twentythirty.guifena.ui.sensor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.twentythirty.guifena.R
import com.twentythirty.guifena.data.SensorEntity
import com.twentythirty.guifena.databinding.FragmentSensorBinding
import com.twentythirty.guifena.utils.Status
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SensorFragment : Fragment() {

    private val sensorViewModel: SensorViewModel by sharedViewModel()
    private lateinit var _binding: FragmentSensorBinding
    private val sensorAdapter = SensorAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSensorBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            with(_binding.rvSensor) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = sensorAdapter
            }
            (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(
                resources.getDrawable(
                    R.drawable.actionbar_layer_list
                )
            )
            loadDummyData()
        }
    }

    private fun loadDummyData() {
        sensorViewModel.getSensors()
        sensorViewModel.sensors.observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        _binding.progressBar.visibility = View.GONE
                        resource.data?.let { sensor ->
                            sensor as List<SensorEntity>
                            sensorAdapter.setSensor(sensor)
                        }
                    }
                }

            }
        })
    }

}