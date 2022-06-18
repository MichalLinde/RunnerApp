package com.mlinde.runner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mlinde.runner.databinding.LocationListElementBinding
import com.squareup.picasso.Picasso

class LocationAdapter(val trails: List<Trail>, private val locationTrailListener: CityListFragment) : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {
    class ViewHolder (private val binding: LocationListElementBinding, locationTrailListener: LocationTrailListener, trails: List<Trail>) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                locationTrailListener.onItemClicked(trails[adapterPosition])
            }
        }
        fun bind(trail: Trail){
            binding.locationName.text = trail.name
            Picasso.get().load(trail.photo).into(binding.locationImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(LocationListElementBinding.inflate(inflater, parent, false), locationTrailListener, trails)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trail = trails[position]
        holder.bind(trail)
    }

    override fun getItemCount(): Int {
        return trails.size
    }

    interface LocationTrailListener{
        fun onItemClicked(trail: Trail)
    }
}