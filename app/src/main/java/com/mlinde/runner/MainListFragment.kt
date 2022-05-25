package com.mlinde.runner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mlinde.runner.databinding.FragmentMainListBinding


class MainListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMainListBinding.inflate(inflater, container, false)
            binding.mainList.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = TrailAdapter(trails)
            }
        return binding.root
    }

}