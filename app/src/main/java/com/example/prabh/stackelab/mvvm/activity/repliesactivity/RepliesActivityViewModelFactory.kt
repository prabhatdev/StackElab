package com.example.prabh.stackelab.mvvm.activity.repliesactivity

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class RepliesActivityViewModelFactory:ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RepliesActivityViewModel::class.java))
            return RepliesActivityViewModel() as T
        throw IllegalArgumentException("Unknown View Model Class")
    }
}