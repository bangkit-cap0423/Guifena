package com.twentythirty.guifena.ui.sensor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.twentythirty.guifena.R
import com.twentythirty.guifena.databinding.FragmentSensorBinding
import com.twentythirty.guifena.ui.sensor.dummyData.dummySensorData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SensorFragment : Fragment() {

    private lateinit var sensorViewModel: SensorViewModel
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
        lifecycleScope.launch(Dispatchers.Main) {
            delay(200L)
            sensorAdapter.setSensor(dummySensorData.setSensor())
            _binding.progressBar.visibility = View.GONE
        }

    }

}