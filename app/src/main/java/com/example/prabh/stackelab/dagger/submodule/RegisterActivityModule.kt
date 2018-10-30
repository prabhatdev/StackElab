package com.example.prabh.stackelab.dagger.submodule

import android.arch.lifecycle.ViewModelProviders
import com.example.prabh.stackelab.dagger.scope.RegisterActivityScope
import com.example.prabh.stackelab.mvvm.activity.registeractivity.RegisterActivityViewModel
import com.example.prabh.stackelab.mvvm.activity.registeractivity.RegisterActivityViewModelFactory
import com.example.prabh.stackelab.mvvm.application.StackElabApplication
import dagger.Module
import dagger.Provides

@Module
class RegisterActivityModule
{
    @Provides
    @RegisterActivityScope
    fun provideRegisterActivityViewModelFactory(): RegisterActivityViewModelFactory = RegisterActivityViewModelFactory()

    @Provides
    @RegisterActivityScope
    fun provideRegisterActivityViewModel(application: StackElabApplication,registerActivityViewModelFactory: RegisterActivityViewModelFactory): RegisterActivityViewModel=
        ViewModelProviders.of(application,registerActivityViewModelFactory).get(RegisterActivityViewModel::class.java)
}