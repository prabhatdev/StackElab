package com.example.prabh.stackelab.dagger.submodule

import android.arch.lifecycle.ViewModelProviders
import com.example.prabh.stackelab.dagger.scope.RecentThreadsActivityScope
import com.example.prabh.stackelab.dagger.scope.RegisterActivityScope
import com.example.prabh.stackelab.mvvm.activity.recentThreadsActivity.RecentThreadsActivityViewModel
import com.example.prabh.stackelab.mvvm.activity.recentThreadsActivity.RecentThreadsActivityViewModelFactory
import com.example.prabh.stackelab.mvvm.application.StackElabApplication
import dagger.Module
import dagger.Provides

@Module
class RecentThreadsActivityModule
{
    @Provides
    @RecentThreadsActivityScope
    fun provideRecentThreadActivityViewModelFactory(): RecentThreadsActivityViewModelFactory= RecentThreadsActivityViewModelFactory()

    @Provides
    @RecentThreadsActivityScope
    fun provideRecentThreadActivityViewModel(application: StackElabApplication, recentThreadsActivityViewModelFactory: RecentThreadsActivityViewModelFactory): RecentThreadsActivityViewModel=
        ViewModelProviders.of(application,recentThreadsActivityViewModelFactory).get(RecentThreadsActivityViewModel::class.java)
}