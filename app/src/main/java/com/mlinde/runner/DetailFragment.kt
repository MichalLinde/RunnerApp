package com.mlinde.runner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mlinde.runner.databinding.FragmentDetailBinding

private const val ARG_PARAM1 = "trail"
class DetailFragment : Fragment() {

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.to_bottom_anim) }

    private val viewModel: DetailViewModel by viewModels { DetailViewModel.Factory(arguments?.getSerializable(ARG_PARAM1) as? Trail) }

    private var clicked: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        val trail: Trail? = viewModel.trail

        binding.detailName.text = trail?.name
        val distance = trail?.distance
        val builder = StringBuilder()
        if (distance != null) {
            if (distance < 1000){
                builder.append(distance)
                builder.append(" ")
                builder.append("m")
                binding.detailDistance.text = builder.toString()
            } else{
                var kmDistance = trail.distance.toDouble()
                kmDistance /= 1000
                if (kmDistance % 1.0 == 0.0){
                    val cutDistance = kmDistance.toInt()
                    builder.append(cutDistance)
                    builder.append(" ")
                    builder.append("km")
                    binding.detailDistance.text = builder.toString()
                } else{
                    builder.append(kmDistance)
                    builder.append(" ")
                    builder.append("km")
                    binding.detailDistance.text = builder.toString()
                }
            }
        }
        binding.detailDescription.text = trail?.description

//        val mapsFragment = trail?.let { MapsFragment.newInstance(it.lat_start!!.toDouble(), trail.lng_start!!.toDouble(), trail.lat_end!!.toDouble(), trail.lng_end!!.toDouble()) }
//        childFragmentManager.beginTransaction().apply {
//            if (mapsFragment != null) {
//                replace(R.id.llMap, mapsFragment)
//                commit()
//            }
//        }
        val mapsFragment = MapsFragment.newInstance(trail)
        childFragmentManager.beginTransaction().apply {
            replace(R.id.llMap, mapsFragment)
            commit()
        }

        binding.detailFAB.setOnClickListener {
            onRunnerButtonClicked(binding)
        }

        binding.detailStatsFAB.setOnClickListener {
            val statsFragment = StatsFragment.newInstance(trail)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fll, statsFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.detailTimerFAB.setOnClickListener {
            val timerFragment = TimerFragment.newInstance(trail)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fll, timerFragment)
                .addToBackStack(null)
                .commit()
        }

        return binding.root
    }

    private fun onRunnerButtonClicked(binding: FragmentDetailBinding) {
        setVisibility(clicked, binding)
        setAnimation(clicked, binding)
        setClickable(clicked, binding)
        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean, binding: FragmentDetailBinding) {
        if (!clicked){
            binding.detailTimerFAB.startAnimation(fromBottom)
            binding.detailStatsFAB.startAnimation(fromBottom)
            binding.detailFAB.startAnimation(rotateOpen)
        } else{
            binding.detailTimerFAB.startAnimation(toBottom)
            binding.detailStatsFAB.startAnimation(toBottom)
            binding.detailFAB.startAnimation(rotateClose)
        }
    }

    private fun setVisibility(clicked: Boolean, binding: FragmentDetailBinding) {
        if (!clicked){
            binding.detailTimerFAB.visibility = View.VISIBLE
            binding.detailStatsFAB.visibility = View.VISIBLE
        } else{
            binding.detailTimerFAB.visibility = View.INVISIBLE
            binding.detailStatsFAB.visibility = View.INVISIBLE
        }
    }

    private fun setClickable(clicked: Boolean, binding: FragmentDetailBinding){
        if (!clicked){
            binding.detailTimerFAB.isClickable = true
            binding.detailStatsFAB.isClickable = true
        } else{
            binding.detailTimerFAB.isClickable = false
            binding.detailStatsFAB.isClickable = false
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: Trail?) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }



}