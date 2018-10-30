package com.example.prabh.stackelab.dagger.submodule

import android.arch.lifecycle.ViewModelProviders
import com.example.prabh.stackelab.dagger.scope.MainActivityScope
import com.example.prabh.stackelab.mvvm.application.StackElabApplication
import com.example.prabh.stackelab.mvvm.activity.mainactivity.MainActivityViewModel
import com.example.prabh.stackelab.mvvm.activity.mainactivity.MainActivityViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @MainActivityScope
    fun provideMainActivityViewModelFactory(): MainActivityViewModelFactory = MainActivityViewModelFactory()

    @Provides
    @MainActivityScope
    fun provideMainActivityViewModel(application: StackElabApplication, mainActivityViewModelFactory: MainActivityViewModelFactory): MainActivityViewModel =
        ViewModelProviders.of(application,mainActivityViewModelFactory).get(MainActivityViewModel::class.java)
}