package com.mlinde.runner

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mlinde.runner.databinding.FragmentMainListBinding


class MainListFragment : Fragment(), TrailAdapter.TrailListener{

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMainListBinding.inflate(inflater, container, false)
            binding.mainList.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = TrailAdapter(trails, this@MainListFragment)
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onItemClicked(trail: Trail) {
        Log.d("TAG", "onItemClicked: ")
    }

}