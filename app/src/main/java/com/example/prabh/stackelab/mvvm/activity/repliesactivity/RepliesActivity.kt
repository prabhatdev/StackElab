package com.example.prabh.stackelab.mvvm.activity.repliesactivity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.prabh.stackelab.R
import com.example.prabh.stackelab.mvvm.application.StackElabApplication
import com.example.prabh.stackelab.retrofit.model.Replies
import com.example.prabh.stackelab.retrofit.model.Reply
import com.example.prabh.stackelab.utility.Response
import com.example.prabh.stackelab.utility.Session
import com.example.prabh.stackelab.utility.Status
import kotlinx.android.synthetic.main.activity_replies.*
import javax.inject.Inject

class RepliesActivity : StackElabApplication() {

    @Inject
    lateinit var repliesActivityViewModel: RepliesActivityViewModel

    var threadId = null.toString()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_replies)
        repliesActivityComponent.inject(this)
        initialise()
    }

    private fun initialise() {
        val intent=getIntent()
        threadId = intent.getIntExtra("thread_id",-1).toString()
        observeResponse()
        getReplies()
    }

    private fun getReplies() {
        repliesActivityViewModel.getReplies(session.getStringValue(Session.REGISTER_NUMBER), threadId)
    }

    private fun observeResponse() {
        repliesActivityViewModel.response.observe(this, Observer { it ->
            processResponse(it)
        })
    }

    private fun processResponse(response: Response?) {
        when (response!!.status) {
            Status.SUCCESS -> {
                Log.v("Replies", "API call successful")
                processResult(response)
            }
            Status.ERROR -> {
                Log.v("Replies", "API Error")
            }
            Status.LOADING -> {
                Log.v("Replies", "API Loading")
            }
        }
    }

    private fun processResult(response: Response) {
        val replies = response.result as Replies
        if (replies.status == "ok") {
            question_no.text = replies.result.thread.question_no.toString()
            subject.text = replies.result.thread.subject
            thread.text = replies.result.thread.query
            date.text = replies.result.thread.created_at.subSequence(0, 10)
            time.text = replies.result.thread.created_at.subSequence(11, 16)
            all_replies.layoutManager=LinearLayoutManager(this)
            all_replies.adapter=RepliesAdapter(replies.result.replies as ArrayList<Reply>,this@RepliesActivity)
        } else {
            showToast(replies.error)
        }
    }
}
