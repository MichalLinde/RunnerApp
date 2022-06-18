package com.mlinde.runner

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mlinde.runner.databinding.HistoryListElementBinding
import com.mlinde.runner.databinding.TrailListElementBinding
import kotlin.math.floor

class StatsAdapter(val stats: List<Stat>) : RecyclerView.Adapter<StatsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(HistoryListElementBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stat = stats[position]
        holder.bind(stat)
    }

    override fun getItemCount(): Int {
        return stats.size
    }

    class ViewHolder(private val binding: HistoryListElementBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(stat: Stat) {
            val time = stat.time?.toInt()
            if (time != null) {
                val hours = time?.div(3600)
                val minutes = (time - (3600 * hours)) / 60
                val seconds = (time - (3600 * hours) - (60 * minutes))
                binding.trailListDistance.text = "$hours h $minutes min $seconds s"
            }
        }
    }
}