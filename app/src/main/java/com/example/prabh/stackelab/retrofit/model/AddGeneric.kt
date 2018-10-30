package com.example.prabh.stackelab.retrofit.model

import com.google.gson.annotations.SerializedName

data class AddGeneric(
    @SerializedName("status")
    var status: String? = null,

    @SerializedName("result")
    var result: Boolean?=null,

    @SerializedName("error")
    var error: String? = null
    )