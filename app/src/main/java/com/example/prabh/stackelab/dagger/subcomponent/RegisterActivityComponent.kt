package com.example.prabh.stackelab.dagger.subcomponent

import com.example.prabh.stackelab.dagger.scope.RegisterActivityScope
import com.example.prabh.stackelab.dagger.submodule.RegisterActivityModule
import com.example.prabh.stackelab.mvvm.activity.registeractivity.RegisterActivity
import dagger.Subcomponent

@RegisterActivityScope
@Subcomponent(modules = [RegisterActivityModule::class])
interface RegisterActivityComponent
{
    fun inject(target: RegisterActivity)
}