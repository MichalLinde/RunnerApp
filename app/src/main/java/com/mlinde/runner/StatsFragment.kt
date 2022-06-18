package com.mlinde.runner

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mlinde.runner.databinding.FragmentStatsBinding

private const val ARG_PARAM1 = "param1"

class StatsFragment : Fragment() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var statsList: ArrayList<Stat>
    private lateinit var statsRunCount: TextView
    private lateinit var statsBestTime: TextView
    private lateinit var listener: ValueEventListener

    lateinit var binding: FragmentStatsBinding

    private val statsViewModel: StatsViewModel by viewModels { StatsViewModel.Factory(arguments?.getSerializable(ARG_PARAM1) as? Trail) }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val trail = statsViewModel.trail
        val trailId = trail!!.id
        Log.d("ID ID ID ID ID", "$trailId")
        binding = FragmentStatsBinding.inflate(inflater, container, false)

        statsList = arrayListOf<Stat>()
        statsRunCount = binding.statsRunCount
        statsBestTime = binding.statsBestTime
        getStatData(trailId)

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Trail?) =
            StatsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }

    private fun getStatData(trailId: Int?){
        databaseReference = Firebase.database.getReferenceFromUrl("https://runner-app-2093e-default-rtdb.europe-west1.firebasedatabase.app/")

        listener = databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    for (historySnapshot in snapshot.child("history").children){
                        val stat = historySnapshot.getValue(Stat::class.java)
                        if (stat != null){
                            if (stat.trail_id == trailId){
                                statsList.add(stat)
                            }
                            Log.d("CHECKCHECKCHECKCHECK", "${stat.time}")
                        }
                    }
                }
                binding.statsList.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = StatsAdapter(statsList)
                }

                statsRunCount.text = statsList.size.toString()

                var minTime = 0
                for (i in statsList.indices){
                    if (i == 0){
                        minTime = statsList[i].time!!
                    } else{
                        if (statsList[i].time!! < minTime){
                            minTime = statsList[i].time!!
                        }
                    }
                }

                val hours = minTime / 3600
                val minutes = (minTime - (3600*hours)) / 60
                val seconds = (minTime - (3600*hours) - (60 * minutes))
                statsBestTime.text = "$hours h $minutes min $seconds s"




            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onDetach() {
        super.onDetach()
        databaseReference.removeEventListener(listener)
    }

}