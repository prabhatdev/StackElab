package com.example.prabh.stackelab.mvvm.activity.addthreadactivity

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.prabh.stackelab.retrofit.model.AddGeneric
import com.example.prabh.stackelab.utility.ApiType
import com.example.prabh.stackelab.utility.Response
import com.example.prabh.stackelab.utility.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AddThreadActivityViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val response: MutableLiveData<Response> = MutableLiveData()


    fun addThread(questionNo: String, query: String, registerNo: String, threadType: String, subject: String) {
        val mApiService = Utils.interfaceService
        compositeDisposable.add(mApiService.addThread(questionNo, query, registerNo, threadType, subject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                response.value = Response.loading(ApiType.GET_ALL_THREADS)
            }
            .subscribe(
                { it: AddGeneric -> response.value = Response.success(ApiType.GET_ALL_THREADS, it) },
                { throwable: Throwable? -> response.value = Response.error(ApiType.GET_ALL_THREADS, throwable!!) }
            ))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}