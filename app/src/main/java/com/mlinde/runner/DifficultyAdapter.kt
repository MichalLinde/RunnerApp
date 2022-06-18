package com.mlinde.runner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mlinde.runner.databinding.DifficultyListElementBinding
import com.mlinde.runner.databinding.LocationListElementBinding
import com.squareup.picasso.Picasso

class DifficultyAdapter(val trails: List<Trail>, private val difficultyTrailListener: DifficultyTrailListener) : RecyclerView.Adapter<DifficultyAdapter.ViewHolder>() {
    class ViewHolder (private val binding: DifficultyListElementBinding, difficultyTrailListener: DifficultyTrailListener, trails: List<Trail>) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                difficultyTrailListener.onItemClicked(trails[adapterPosition])
            }
        }
        fun bind(trail: Trail){
            binding.difficultyName.text = trail.name
            Picasso.get().load(trail.photo).into(binding.difficultyImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(DifficultyListElementBinding.inflate(inflater, parent, false), difficultyTrailListener, trails)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trail = trails[position]
        holder.bind(trail)
    }

    override fun getItemCount(): Int {
        return trails.size
    }

    interface DifficultyTrailListener{
        fun onItemClicked(trail: Trail)
    }



}