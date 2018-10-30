package com.example.prabh.stackelab.dagger.subcomponent

import com.example.prabh.stackelab.dagger.scope.RepliesActivityScope
import com.example.prabh.stackelab.dagger.submodule.RepliesActivityModule
import com.example.prabh.stackelab.mvvm.activity.repliesactivity.RepliesActivity
import dagger.Subcomponent


@RepliesActivityScope
@Subcomponent(modules = [RepliesActivityModule::class])
interface RepliesActivityComponent{
    fun inject(target: RepliesActivity)
}
