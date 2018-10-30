package com.example.prabh.stackelab.mvvm.activity.recentThreadsActivity

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.prabh.stackelab.retrofit.model.AddGeneric
import com.example.prabh.stackelab.retrofit.model.RecentThreads
import com.example.prabh.stackelab.utility.ApiType
import com.example.prabh.stackelab.utility.Response
import com.example.prabh.stackelab.utility.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RecentThreadsActivityViewModel:ViewModel(){
    val compositeDisposable = CompositeDisposable()
    val response: MutableLiveData<Response> = MutableLiveData()


    fun viewAllThreads() {
        val mApiService = Utils.interfaceService
        compositeDisposable.add(mApiService.viewAllThreads()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                response.value = Response.loading(ApiType.GET_ALL_THREADS)
            }
            .subscribe(
                { it: RecentThreads -> response.value = Response.success(ApiType.GET_ALL_THREADS, it) },
                { throwable: Throwable? -> response.value = Response.error(ApiType.GET_ALL_THREADS, throwable!!) }
            ))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}