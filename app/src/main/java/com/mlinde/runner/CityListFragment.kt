package com.mlinde.runner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mlinde.runner.databinding.FragmentCityListBinding
import kotlin.math.log

class CityListFragment : Fragment(), LocationAdapter.LocationTrailListener {

    private lateinit var binding: FragmentCityListBinding
    private lateinit var trailList: ArrayList<Trail>
    private lateinit var shorelineTrailList: ArrayList<Trail>
    private lateinit var parkTrailList: ArrayList<Trail>
    private lateinit var cityTrailList: ArrayList<Trail>
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCityListBinding.inflate(inflater, container, false)

        trailList = arrayListOf<Trail>()
        shorelineTrailList = arrayListOf<Trail>()
        parkTrailList = arrayListOf<Trail>()
        cityTrailList = arrayListOf<Trail>()

        getTrailData()

        return binding.root
    }

    private fun getTrailData(){
        databaseReference = Firebase.database.getReferenceFromUrl("https://runner-app-2093e-default-rtdb.europe-west1.firebasedatabase.app/")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    for (trailSnapshot in snapshot.child("trails").children){
                        val trail = trailSnapshot.getValue(Trail::class.java)
                        var tempArray: ArrayList<Int> = arrayListOf<Int>()
                        for (t in trailList){
                            tempArray.add(t.id!!)
                        }
                        if (!tempArray.contains(trail!!.id)){
                            trailList.add(trail)
                            when{
                                trail.city.equals("Shoreline") -> shorelineTrailList.add(trail)
                                trail.city.equals("Park") -> parkTrailList.add(trail)
                                trail.city.equals("City") -> cityTrailList.add(trail)
                            }
                        }
                    }
                    binding.shorelineRecyclerView.apply {
                        if (isAdded){
                            layoutManager = GridLayoutManager(requireContext(), 2)
                            adapter = LocationAdapter(shorelineTrailList, this@CityListFragment)
                        }
                    }
                    binding.parkRecyclerView.apply {
                        if (isAdded){
                            layoutManager = GridLayoutManager(requireContext(), 2)
                            adapter = LocationAdapter(parkTrailList, this@CityListFragment)
                        }
                    }
                    binding.cityRecyclerView.apply {
                        if (isAdded){
                            layoutManager = GridLayoutManager(requireContext(), 2)
                            adapter = LocationAdapter(cityTrailList, this@CityListFragment)
                        }
                    }
                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onItemClicked(trail: Trail) {
        val intent = Intent(requireContext(), DetailActivity::class.java).apply{
            putExtra("trail", trail)
        }
        startActivity(intent)
    }

}