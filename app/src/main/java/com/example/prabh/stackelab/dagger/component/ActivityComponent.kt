package com.example.prabh.stackelab.dagger.component

import com.example.prabh.stackelab.dagger.module.ActivityModule
import com.example.prabh.stackelab.dagger.subcomponent.*
import com.example.prabh.stackelab.dagger.submodule.*
import com.example.prabh.stackelab.mvvm.application.StackElabApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(target: StackElabApplication)

    fun plusMainActivityComponent(mainActivityModule: MainActivityModule): MainActivityComponent

    fun plusRegisterActivityComponent(registerActivityModule: RegisterActivityModule): RegisterActivityComponent

    fun plusRecentThreadActivityComponent(recentThreadsActivityModule: RecentThreadsActivityModule): RecentThreadActivityComponent

    fun plusAddThreadActivityComponent(addThreadActivityModule: AddThreadActivityModule): AddThreadActivityComponent

    fun plusRepliesActivityComponent(repliesActivityModule: RepliesActivityModule):RepliesActivityComponent
}
