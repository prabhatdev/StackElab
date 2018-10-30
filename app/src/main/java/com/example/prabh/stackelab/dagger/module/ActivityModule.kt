package com.example.prabh.stackelab.dagger.module

import android.app.ProgressDialog
import android.content.Context
import com.example.prabh.stackelab.mvvm.application.StackElabApplication
import com.example.prabh.stackelab.utility.Session
import com.example.prabh.stackelab.utility.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ActivityModule(val stackElabApplication: StackElabApplication)
{
    @Provides
    @Singleton
    fun provideContext() : Context = stackElabApplication

    @Provides
    @Singleton
    fun provideTeamPhiApplication() : StackElabApplication =stackElabApplication

    @Provides
    @Singleton
    fun provideUtil(context: Context): Utils = Utils.provideUtil(context)

    @Provides
    @Singleton
    fun provideSession(context: Context): Session =Session(context)

    @Provides
    @Singleton
    fun provideProgressDialog(context: Context): ProgressDialog =Utils.getProgressDialog(context)

}