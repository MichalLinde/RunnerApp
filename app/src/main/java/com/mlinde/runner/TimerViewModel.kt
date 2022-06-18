package com.mlinde.runner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerViewModel(
    val trail: Trail?
) : ViewModel() {
    val timer = MutableLiveData<Double>(0.0)
    var job: Job? = null
    var started: Boolean = false
    fun onButtonClicked(){
        job = viewModelScope.launch {
            started = true
            timer.value = 0.0
            while (true){
                delay(100)
                timer.value = timer.value!! + 0.1
            }
        }
    }
    fun onStop(){
        started = false
        job?.cancel()
    }

    class Factory(val trail: Trail?) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TimerViewModel(trail) as T
        }
    }
}