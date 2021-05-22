package com.twentythirty.guifena.ui.home

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.twentythirty.guifena.R
import com.twentythirty.guifena.data.IncidentEntity
import com.twentythirty.guifena.databinding.IncidentItemRowBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.IncidentViewHolder>() {
    private var listIncident = ArrayList<IncidentEntity>()
    var onItemClick: ((IncidentEntity) -> Unit)? = null

    fun setIncident(incidentEntity: List<IncidentEntity>?) {
        if (incidentEntity == null) return
        this.listIncident.clear()
        this.listIncident.addAll(incidentEntity)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        IncidentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.incident_item_row, parent, false))


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: IncidentViewHolder, position: Int) {
        val incident = listIncident[position]
        holder.bind(incident)
    }

    override fun getItemCount(): Int = listIncident.size

    inner class IncidentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = IncidentItemRowBinding.bind(itemView)
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(incident: IncidentEntity) {
            with(binding) {
                when (incident.status) {
                    1 -> {
                        //set incident status to Handled
                        tvStatus.text = itemView.context.getString(R.string.handled)
                        tvStatus.setTextColor(itemView.context.getColor(R.color.yellow))
                    }
                    0 -> {
                        //Initial status not Handled
                        tvStatus.text = itemView.context.getString(R.string.not_handled)
                        tvStatus.setTextColor(Color.RED)
                    }
                    else -> {
                        //Incident resolved
                        //Remove item from recycleView
                    }
                }
                tvIncidentNumber.text = itemView.context.getString(R.string.incident_number, incident.id)
                tvPointName.text = incident.sensorName
                tvCoordinate.text = incident.sensorLocation
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listIncident[adapterPosition])
            }
        }
    }
}