package com.example.prabh.stackelab.retrofit.model

data class RecentThreads(
    val result: List<Thread>,
    val status: String,
    val error: String
)

data class Thread(
    val created_at: String,
    val dept: String,
    val query: String,
    val question_no: Int,
    val register_no: String,
    val subject: String,
    val thread_id: Int,
    val type: String
)