package com.example.prabh.stackelab.mvvm.activity.registeractivity

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class RegisterActivityViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterActivityViewModel::class.java)) {
            return RegisterActivityViewModel() as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}