package com.mlinde.runner

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivityViewModel(
    var fragment: Fragment
) : ViewModel() {
    class Factory(val fragment: Fragment) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainActivityViewModel(fragment) as T
        }
    }
}