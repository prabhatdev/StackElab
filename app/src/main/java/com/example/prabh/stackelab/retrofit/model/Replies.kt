package com.example.prabh.stackelab.retrofit.model

data class Replies(
    val result: Result,
    val status: String,
    val error: String
)

data class Result(
    val replies: List<Reply>,
    val thread: ThreadReply
)

data class ThreadReply(
    val created_at: String,
    val dept: String,
    val query: String,
    val question_no: Int,
    val register_no: String,
    val subject: String,
    val thread_id: Int,
    val type: String
)

data class Reply(
    val created_at: String,
    val dept: String,
    val name: String,
    val register_no: String,
    val reply_id: Int,
    val reply_text: String,
    val thread_id: String
)