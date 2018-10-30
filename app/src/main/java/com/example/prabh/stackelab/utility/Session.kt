package com.example.prabh.stackelab.utility

import android.content.Context
import android.content.SharedPreferences

class Session(val context: Context)
{
    private var editor: SharedPreferences.Editor?=null
    private var sharedPreferences: SharedPreferences
    init {
        sharedPreferences=context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setIsLogedIn(value:Boolean)
    {
        editor=sharedPreferences.edit()
        editor?.putBoolean(IS_LOGIN,value)
        editor?.apply()
    }

    fun saveLoginDetails(registerNo:String,password:String)
    {
        editor=sharedPreferences.edit()
        editor?.putString(REGISTER_NUMBER,registerNo)
        editor?.putString(PASSWORD,password)
        editor?.apply()

    }

    fun isLoggedIn():Boolean
    {
        return sharedPreferences.getBoolean(IS_LOGIN,false)
    }

    fun getStringValue(key:String,default: String?=null)=sharedPreferences.getString(key,default)


    companion object {
        const val PREF_NAME="StackElab"
        const val IS_LOGIN="IsLoggedIn"
        const val REGISTER_NUMBER="username"
        const val PASSWORD="password"
    }
}