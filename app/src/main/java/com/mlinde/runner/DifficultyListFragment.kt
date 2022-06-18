package com.mlinde.runner

import android.content.Intent
import android.os.Bundle
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
import com.mlinde.runner.databinding.FragmentDifficultyListBinding

class DifficultyListFragment : Fragment(), DifficultyAdapter.DifficultyTrailListener {
    private lateinit var binding: FragmentDifficultyListBinding
    private lateinit var trailList: ArrayList<Trail>
    private lateinit var easyTrailList: ArrayList<Trail>
    private lateinit var mediumTrailList: ArrayList<Trail>
    private lateinit var hardTrailList: ArrayList<Trail>
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDifficultyListBinding.inflate(inflater, container, false)

        trailList = arrayListOf<Trail>()
        easyTrailList = arrayListOf<Trail>()
        mediumTrailList = arrayListOf<Trail>()
        hardTrailList = arrayListOf<Trail>()

        getTrailData()

        return binding.root
    }

    override fun onItemClicked(trail: Trail) {
        val intent = Intent(requireContext(), DetailActivity::class.java).apply{
            putExtra("trail", trail)
        }
        startActivity(intent)
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
                                trail.difficulty.equals("Easy") -> easyTrailList.add(trail)
                                trail.difficulty.equals("Medium") -> mediumTrailList.add(trail)
                                trail.difficulty.equals("Hard") -> hardTrailList.add(trail)
                            }
                        }
                    }
                    binding.easyRecyclerView.apply {
                        if (isAdded){
                            layoutManager = GridLayoutManager(requireContext(), 2)
                            adapter = DifficultyAdapter(easyTrailList, this@DifficultyListFragment)
                        }
                    }
                    binding.mediumRecyclerView.apply {
                        if (isAdded){
                            layoutManager = GridLayoutManager(requireContext(), 2)
                            adapter = DifficultyAdapter(mediumTrailList, this@DifficultyListFragment)
                        }
                    }
                    binding.hardRecyclerView.apply {
                        if (isAdded){
                            layoutManager = GridLayoutManager(requireContext(), 2)
                            adapter = DifficultyAdapter(hardTrailList, this@DifficultyListFragment)
                        }
                    }
                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}