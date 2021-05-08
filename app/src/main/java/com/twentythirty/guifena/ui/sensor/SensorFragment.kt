package com.twentythirty.guifena.ui.sensor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.twentythirty.guifena.R
import com.twentythirty.guifena.databinding.FragmentSensorBinding
import com.twentythirty.guifena.ui.sensor.dummyData.dummySensorData

class SensorFragment : Fragment() {

    private lateinit var sensorViewModel: SensorViewModel
    private var _binding: FragmentSensorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSensorBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            sensorViewModel = ViewModelProvider(this).get(SensorViewModel::class.java)
            val sensorAdapter = SensorAdapter()
            sensorAdapter.setSensor(dummySensorData.setSensor())
            with(binding.rvSensor) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = sensorAdapter
            }
            (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(
                resources.getDrawable(
                    R.drawable.actionbar_layer_list
                )
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}