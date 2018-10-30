package com.example.prabh.stackelab.retrofit.handler

import android.support.annotation.Keep
import com.example.prabh.stackelab.retrofit.model.AddGeneric
import com.example.prabh.stackelab.retrofit.model.RecentThreads
import com.example.prabh.stackelab.retrofit.model.Replies
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

@Keep
interface ApiInterface {

    @FormUrlEncoded
    @POST("registerUser")
    fun registerUser(@Field("name") userName: String, @Field("register_no") registerNo: String, @Field("dept") department: String, @Field("password") password: String): Observable<AddGeneric>

    @POST("login")
    fun checkLoginUser(@Header("register_no") registerNo: String, @Header("password") password: String): Observable<AddGeneric>

    @POST("viewAllThread")
    fun viewAllThreads(): Observable<RecentThreads>

    @FormUrlEncoded
    @POST("addThread")
    fun addThread(@Field("questionNo") questionNo:String,@Field("query") query:String,@Field("register_no") registerNo: String,@Field("type") type:String,@Field("subject") subject:String): Observable<AddGeneric>

    @FormUrlEncoded
    @POST("listAllReply")
    fun getReplies(@Field("thread_id") threadId:String,@Field("register_no") registerNo:String): Observable<Replies>



}