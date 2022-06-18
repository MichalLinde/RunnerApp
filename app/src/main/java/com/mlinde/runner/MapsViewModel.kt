package com.mlinde.runner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MapsViewModel(
    val trail: Trail?
) : ViewModel() {
    class Factory(val trail: Trail?) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MapsViewModel(trail) as T
        }
    }
}