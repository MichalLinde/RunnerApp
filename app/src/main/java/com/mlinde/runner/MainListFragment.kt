package com.mlinde.runner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mlinde.runner.databinding.FragmentMainListBinding



class MainListFragment : Fragment(), TrailAdapter.TrailListener{

    private lateinit var trailList: ArrayList<Trail>
    private lateinit var databaseReference: DatabaseReference
    private lateinit var binding: FragmentMainListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainListBinding.inflate(inflater, container, false)
//            binding.mainList.apply {
//                layoutManager = LinearLayoutManager(requireContext())
//                adapter = TrailAdapter(trails, this@MainListFragment)
//            }
        trailList = arrayListOf<Trail>()
        getTraildata()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onItemClicked(trail: Trail) {
        val intent = Intent(requireContext(), DetailActivity::class.java).apply{
            putExtra("trail", trail)
        }
        startActivity(intent)
    }

    private fun getTraildata(){
        databaseReference = Firebase.database.getReferenceFromUrl(
            "https://runner-app-2093e-default-rtdb.europe-west1.firebasedatabase.app/")

        databaseReference.addValueEventListener(object : ValueEventListener{
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
                        }
                    }
                }

                binding.mainList.apply {
                    if (isAdded){
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = TrailAdapter(trailList, this@MainListFragment)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }



}