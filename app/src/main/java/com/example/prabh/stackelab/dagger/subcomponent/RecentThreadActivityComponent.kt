package com.example.prabh.stackelab.dagger.subcomponent

import com.example.prabh.stackelab.dagger.scope.RecentThreadsActivityScope
import com.example.prabh.stackelab.dagger.submodule.RecentThreadsActivityModule
import com.example.prabh.stackelab.mvvm.activity.recentThreadsActivity.RecentThreadsActivity
import dagger.Subcomponent


@RecentThreadsActivityScope
@Subcomponent(modules = [RecentThreadsActivityModule::class])
interface RecentThreadActivityComponent{
    fun inject(target: RecentThreadsActivity)
}