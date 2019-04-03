package com.example.prabh.stackelab.retrofit.model

data class SendReply(
    val result: SendReplyResult,
    val status: String
)

data class SendReplyResult(
    val created_at: String,
    val dept: String,
    val name:String,
    val register_no: String,
    val reply_text: String
)