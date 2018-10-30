package com.example.prabh.stackelab.dagger.submodule

import android.arch.lifecycle.ViewModelProviders
import com.example.prabh.stackelab.dagger.scope.RepliesActivityScope
import com.example.prabh.stackelab.mvvm.activity.repliesactivity.RepliesActivityViewModel
import com.example.prabh.stackelab.mvvm.activity.repliesactivity.RepliesActivityViewModelFactory
import com.example.prabh.stackelab.mvvm.application.StackElabApplication
import dagger.Module
import dagger.Provides

@Module
class RepliesActivityModule{

    @Provides
    @RepliesActivityScope
    fun provideRepliesActivityViewModelFactory(): RepliesActivityViewModelFactory= RepliesActivityViewModelFactory()

    @Provides
    @RepliesActivityScope
    fun provideRepliesActivityViewModel(application: StackElabApplication, repliesActivityViewModelFactory: RepliesActivityViewModelFactory): RepliesActivityViewModel=
        ViewModelProviders.of(application,repliesActivityViewModelFactory).get(RepliesActivityViewModel::class.java)

}