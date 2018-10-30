package com.example.prabh.stackelab.mvvm.activity.registeractivity

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.prabh.stackelab.retrofit.model.AddGeneric
import com.example.prabh.stackelab.utility.ApiType
import com.example.prabh.stackelab.utility.Response
import com.example.prabh.stackelab.utility.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RegisterActivityViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val response: MutableLiveData<Response> = MutableLiveData()

    fun registerUser(name:String,registerNumber:String,department:String,password:String)
    {
        val mApiService= Utils.interfaceService
        compositeDisposable.add(mApiService.registerUser(name,registerNumber,department,password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                response.value = Response.loading(ApiType.REGISTER_USER)
            }
            .subscribe(
                {it:AddGeneric -> response.value= Response.success(ApiType.REGISTER_USER,it)},
                {throwable: Throwable? -> response.value= Response.error(ApiType.REGISTER_USER, throwable!!) }
            ))
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}