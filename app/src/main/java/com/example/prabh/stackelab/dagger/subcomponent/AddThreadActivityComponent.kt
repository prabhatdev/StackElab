package com.example.prabh.stackelab.dagger.subcomponent

import com.example.prabh.stackelab.dagger.scope.AddThreadActivityScope
import com.example.prabh.stackelab.dagger.submodule.AddThreadActivityModule
import com.example.prabh.stackelab.mvvm.activity.addthreadactivity.AddThreadActivity
import dagger.Subcomponent


@AddThreadActivityScope
@Subcomponent(modules = [AddThreadActivityModule::class])
interface AddThreadActivityComponent{
    fun inject(target: AddThreadActivity)
}