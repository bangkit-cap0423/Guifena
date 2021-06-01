package com.twentythirty.guifena.ui.sensor

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.twentythirty.guifena.R
import com.twentythirty.guifena.data.SensorEntity
import com.twentythirty.guifena.databinding.SensorItemRowBinding

class SensorAdapter : RecyclerView.Adapter<SensorAdapter.SensorViewHolder>() {
    private var listSensor = ArrayList<SensorEntity>()

    fun setSensor(sensorEntity: List<SensorEntity>?) {
        if (sensorEntity == null) return
        this.listSensor.clear()
        this.listSensor.addAll(sensorEntity)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorViewHolder {
        val itemBinding = SensorItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SensorViewHolder(itemBinding)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: SensorViewHolder, position: Int) {
        val sensor = listSensor[position]
        holder.bind(sensor)
    }

    override fun getItemCount(): Int = listSensor.size


    class SensorViewHolder(private val binding: SensorItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(sensorEntity: SensorEntity) {
            with(binding) {
                tvSensorName.text = itemView.context.getString(R.string.sensor_name, sensorEntity.id)
                if (sensorEntity.status == 1) {
                    tvStatus.text = itemView.context.getString(R.string.online_status)
                    tvStatus.setTextColor(itemView.context.getColor(R.color.green))
                } else {
                    tvStatus.text =itemView.context.getString(R.string.offline_status)
                    tvStatus.setTextColor(Color.RED)
                }
                tvPointName.text = sensorEntity.nama
                tvCoordinate.text = sensorEntity.location
            }
        }
    }
}