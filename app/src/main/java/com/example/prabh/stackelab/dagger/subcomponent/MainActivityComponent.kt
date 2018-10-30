package com.example.prabh.stackelab.dagger.subcomponent

import com.example.prabh.stackelab.dagger.scope.MainActivityScope
import com.example.prabh.stackelab.dagger.submodule.MainActivityModule
import com.example.prabh.stackelab.mvvm.activity.mainactivity.MainActivity
import dagger.Subcomponent

@MainActivityScope
@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent{
    fun inject(target: MainActivity)
}