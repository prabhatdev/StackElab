package com.example.prabh.stackelab.mvvm.application

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.widget.Toast
import com.example.prabh.stackelab.dagger.component.ActivityComponent
import com.example.prabh.stackelab.dagger.component.DaggerActivityComponent
import com.example.prabh.stackelab.dagger.module.ActivityModule
import com.example.prabh.stackelab.dagger.subcomponent.*
import com.example.prabh.stackelab.dagger.submodule.*
import com.example.prabh.stackelab.utility.Session
import com.example.prabh.stackelab.utility.Utils
import javax.inject.Inject

open class StackElabApplication:AppCompatActivity(){

    private val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent
            .builder()
            .activityModule(ActivityModule(this))
            .build()
    }
    val mainActivityComponent: MainActivityComponent by lazy {
        activityComponent.plusMainActivityComponent(MainActivityModule())
    }

    val registerActivityComponent:RegisterActivityComponent by lazy {
        activityComponent.plusRegisterActivityComponent(RegisterActivityModule())
    }

    val recentThreadActivityComponent:RecentThreadActivityComponent by lazy {
        activityComponent.plusRecentThreadActivityComponent(RecentThreadsActivityModule())
    }

    val addThreadActivityComponent:AddThreadActivityComponent by lazy {
        activityComponent.plusAddThreadActivityComponent(AddThreadActivityModule())
    }

    val repliesActivityComponent:RepliesActivityComponent by lazy {
        activityComponent.plusRepliesActivityComponent(RepliesActivityModule())
    }

    fun showToast(message: String, length: Int = Toast.LENGTH_SHORT) = Utils.showToast(this, message, length)

    fun isConnected() = utils.isInternetAvailable(this)

    @Inject
    lateinit var session: Session

    @Inject
    lateinit var utils: Utils

    @Inject
    lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        activityComponent.inject(this)
    }

}