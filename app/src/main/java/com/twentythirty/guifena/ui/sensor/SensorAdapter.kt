package com.twentythirty.guifena.ui.sensor

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.twentythirty.guifena.databinding.SensorItemRowBinding
import com.twentythirty.guifena.ui.sensor.dummyData.dummySensorEntity

class SensorAdapter : RecyclerView.Adapter<SensorAdapter.SensorViewHolder>() {
    private var listSensor = ArrayList<dummySensorEntity>()

    fun setSensor(sensorEntity: List<dummySensorEntity>?) {
        if (sensorEntity == null) return
        this.listSensor.clear()
        this.listSensor.addAll(sensorEntity)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorViewHolder {
        val itemBinding = SensorItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SensorViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SensorViewHolder, position: Int) {
        val sensor = listSensor[position]
        holder.bind(sensor)
    }

    override fun getItemCount(): Int = listSensor.size


    class SensorViewHolder(private val binding: SensorItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sensorEntity: dummySensorEntity) {
            with(binding) {
                tvSensorName.text = sensorEntity.sensorName
                tvStatus.text = sensorEntity.status
                if (sensorEntity.status == "Online"){
                    tvStatus.setTextColor(Color.parseColor("#FF228B22"))
                }else{
                    tvStatus.setTextColor(Color.RED)
                }
                tvPointName.text = sensorEntity.pointName
                tvCoordinate.text = sensorEntity.coordinate.toString()
            }
        }
    }
}