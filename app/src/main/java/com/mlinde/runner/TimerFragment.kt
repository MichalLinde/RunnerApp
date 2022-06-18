package com.mlinde.runner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mlinde.runner.databinding.FragmentTimerBinding
import java.util.concurrent.TimeUnit

private const val ARG_PARAM1 = "param1"


class TimerFragment : Fragment() {

    private var nextId: Int = 0


    private lateinit var databaseReference: DatabaseReference
    private lateinit var listener: ValueEventListener


    private lateinit var binding: FragmentTimerBinding

    private val viewModel: TimerViewModel by viewModels { TimerViewModel.Factory(arguments?.getSerializable(ARG_PARAM1) as? Trail) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimerBinding.inflate(inflater, container, false)
        binding.timerResetButton.isEnabled = false
        val trail = viewModel.trail

        if (viewModel.started){
            binding.timerStartButton.setImageResource(R.drawable.ic_baseline_stop_24)
        } else{
            binding.timerStartButton.setImageResource(R.drawable.play_arrow_24)
        }

        getStatsData()
        initStopWatch()

        viewModel.timer.observe(viewLifecycleOwner){
            binding.timerText.text = getFormattedStopWatch((it * 1000))
        }

        binding.timerStartButton.setOnClickListener {
            if (viewModel.started){
                viewModel.onStop()
                binding.timerStartButton.setImageResource(R.drawable.play_arrow_24)
                binding.timerResetButton.isEnabled = true
            } else {
                viewModel.onButtonClicked()
                binding.timerStartButton.setImageResource(R.drawable.ic_baseline_stop_24)
                binding.timerResetButton.isEnabled = false
            }

        }

        binding.timerResetButton.setOnClickListener {
            saveTime(trail!!.id!!)
            initStopWatch()
        }

        return binding.root
    }

    private fun initStopWatch() {
        binding.timerText.text = "00:00:00"
    }

    private fun getFormattedStopWatch(ms: Double): String {
        var milliseconds = ms.toLong()
        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
        milliseconds -= TimeUnit.HOURS.toMillis(hours)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
        milliseconds -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)

        return "${if (hours < 10) "0" else ""}$hours:" +
                "${if (minutes < 10) "0" else ""}$minutes:" +
                "${if (seconds < 10) "0" else ""}$seconds"
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: Trail?) =
            TimerFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }

    private fun getStatsData(){
        databaseReference = Firebase.database.getReferenceFromUrl("https://runner-app-2093e-default-rtdb.europe-west1.firebasedatabase.app/")
        listener = databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (historySnapshot in snapshot.child("history").children){
                    val stat= historySnapshot.getValue(Stat::class.java)
                    if (stat != null) {
                        if (stat.id!! >= nextId){
                            nextId = stat.id + 1
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun saveTime(trailId: Int){
        var timeToSave: Int = 0
        val timerState = binding.timerText.text
        val hours = (timerState[0].code - 48) * 10 + (timerState[1].code - 48)
        val minutes = (timerState[3].code - 48) * 10 + (timerState[4].code - 48)
        val seconds = (timerState[6].code - 48) * 10 + (timerState[7].code - 48)
        timeToSave += hours * 3600 + minutes * 60 + seconds

        val stat = Stat(nextId, timeToSave, trailId, 1)

        databaseReference.child("history").child(nextId.toString()).setValue(stat)
    }

    override fun onDetach() {
        super.onDetach()
        databaseReference.removeEventListener(listener)
    }
}