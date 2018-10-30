package com.example.prabh.stackelab.dagger.submodule

import android.arch.lifecycle.ViewModelProviders
import com.example.prabh.stackelab.dagger.scope.AddThreadActivityScope
import com.example.prabh.stackelab.mvvm.activity.addthreadactivity.AddThreadActivityViewModel
import com.example.prabh.stackelab.mvvm.activity.addthreadactivity.AddThreadActivityViewModelFactory
import com.example.prabh.stackelab.mvvm.application.StackElabApplication
import dagger.Module
import dagger.Provides

@Module
class AddThreadActivityModule
{
    @Provides
    @AddThreadActivityScope
    fun provideAddThreadActivityViewModelFactory(): AddThreadActivityViewModelFactory= AddThreadActivityViewModelFactory()

    @Provides
    @AddThreadActivityScope
    fun provideAddThreadActivityViewModel(application: StackElabApplication, addThreadActivityViewModelFactory: AddThreadActivityViewModelFactory): AddThreadActivityViewModel=
        ViewModelProviders.of(application,addThreadActivityViewModelFactory).get(AddThreadActivityViewModel::class.java)
}