package com.example.prabh.stackelab.mvvm.activity.recentThreadsActivity

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.prabh.stackelab.mvvm.activity.mainactivity.MainActivityViewModel
import java.lang.IllegalArgumentException

class RecentThreadsActivityViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecentThreadsActivityViewModel::class.java)) {
            return RecentThreadsActivityViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}