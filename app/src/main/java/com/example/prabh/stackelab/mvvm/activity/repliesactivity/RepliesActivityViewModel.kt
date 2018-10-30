package com.example.prabh.stackelab.mvvm.activity.repliesactivity

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.prabh.stackelab.retrofit.model.Replies
import com.example.prabh.stackelab.utility.ApiType
import com.example.prabh.stackelab.utility.Response
import com.example.prabh.stackelab.utility.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepliesActivityViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val response: MutableLiveData<Response> = MutableLiveData()

    fun getReplies(registerNumber: String, threadId: String) {
        val mApiService = Utils.interfaceService
        compositeDisposable.add(mApiService.getReplies(threadId, registerNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                response.value = Response.loading(ApiType.GET_ALL_REPLY)
            }
            .subscribe(
                { it: Replies -> response.value = Response.success(ApiType.GET_ALL_REPLY, it) },
                { throwable: Throwable? -> response.value = Response.error(ApiType.GET_ALL_REPLY, throwable!!) }
            ))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}