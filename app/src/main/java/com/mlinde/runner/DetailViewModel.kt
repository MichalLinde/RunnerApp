package com.mlinde.runner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailViewModel(
    val trail: Trail?
) : ViewModel(){
    class Factory(val trail: Trail?) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetailViewModel(trail) as T
        }
    }
}