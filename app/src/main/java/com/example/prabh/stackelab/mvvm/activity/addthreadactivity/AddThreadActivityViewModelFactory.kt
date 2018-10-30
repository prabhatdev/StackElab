package com.example.prabh.stackelab.mvvm.activity.addthreadactivity

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class AddThreadActivityViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddThreadActivityViewModel::class.java)) {
            return AddThreadActivityViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}