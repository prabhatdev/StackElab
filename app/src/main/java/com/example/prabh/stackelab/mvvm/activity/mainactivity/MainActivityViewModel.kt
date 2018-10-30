package com.example.prabh.stackelab.mvvm.activity.mainactivity

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.prabh.stackelab.retrofit.model.AddGeneric
import com.example.prabh.stackelab.utility.ApiType
import com.example.prabh.stackelab.utility.Response
import com.example.prabh.stackelab.utility.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainActivityViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    val response: MutableLiveData<Response> = MutableLiveData()


    fun checkLogin(registerNumber: String, password: String) {
        val mApiService = Utils.interfaceService
        compositeDisposable.add(mApiService.checkLoginUser(registerNumber, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                response.value = Response.loading(ApiType.CHECK_LOGIN)
            }
            .subscribe(
                { it: AddGeneric -> response.value = Response.success(ApiType.CHECK_LOGIN, it) },
                { throwable: Throwable? -> response.value = Response.error(ApiType.CHECK_LOGIN, throwable!!) }
            ))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
