package com.mlinde.runner

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mlinde.runner.databinding.TrailListElementBinding

class TrailAdapter(val trails: List<Trail>, private val trailListener: TrailListener) : RecyclerView.Adapter<TrailAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(TrailListElementBinding.inflate(inflater, parent, false), trailListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trail = trails[position]
        holder.bind(trail)
    }

    override fun getItemCount(): Int {
        return trails.size
    }

    class ViewHolder(private val binding: TrailListElementBinding, trailListener: TrailListener):
            RecyclerView.ViewHolder(binding.root){

            init {
                itemView.setOnClickListener {
                    trailListener.onItemClicked(trails[adapterPosition])
                }
            }

            @SuppressLint("SetTextI18n")
            fun bind(trail: Trail) {
                binding.trailListName.text = trail.name
                val distance = trail.length
                if (distance < 1000){
                    binding.trailListDistance.text = "$distance m"
                } else{
                    var kmDistance = trail.length.toDouble()
                    kmDistance /= 1000
                    if (kmDistance % 1.0 == 0.0){
                        val cutDistance = kmDistance.toInt()
                        binding.trailListDistance.text = "$cutDistance km"
                    } else{
                        binding.trailListDistance.text = "$kmDistance km"
                    }
                }

            }
    }

    interface TrailListener{
        fun onItemClicked(trail: Trail)
    }
}