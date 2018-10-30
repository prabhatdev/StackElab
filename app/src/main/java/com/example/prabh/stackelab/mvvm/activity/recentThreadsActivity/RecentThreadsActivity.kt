package com.example.prabh.stackelab.mvvm.activity.recentThreadsActivity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.example.prabh.stackelab.R
import com.example.prabh.stackelab.mvvm.activity.addthreadactivity.AddThreadActivity
import com.example.prabh.stackelab.mvvm.activity.mainactivity.MainActivity
import com.example.prabh.stackelab.mvvm.application.StackElabApplication
import com.example.prabh.stackelab.retrofit.model.RecentThreads
import com.example.prabh.stackelab.retrofit.model.Thread
import com.example.prabh.stackelab.utility.Response
import com.example.prabh.stackelab.utility.Status
import kotlinx.android.synthetic.main.activity_recent_threads.*
import javax.inject.Inject

class RecentThreadsActivity : StackElabApplication(), SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {
        getAllThreads()
    }

    @Inject
    lateinit var recentThreadsActivityViewModel: RecentThreadsActivityViewModel

    lateinit var recentThreads: RecentThreads

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_threads)
        recentThreadActivityComponent.inject(this)
        initialise()
    }

    private fun initialise() {
        swipeRefresh?.let {
            swipeRefresh?.setOnRefreshListener(this)
        }
        add_thread.setOnClickListener {
            startActivity(Intent(this, AddThreadActivity::class.java))
        }

        log_out.setOnClickListener {
            session.setIsLogedIn(false)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                setAdapter(p0)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        observeResponse()
        getAllThreads()
    }

    private fun setAdapter(p0: Editable?) {
        if (!p0.toString().isEmpty()) {
            val searchedThread = ArrayList<Thread>()
            for (thread in recentThreads.result) {
                when {
                    thread.created_at.contains(p0.toString(), ignoreCase = true) -> {
                        searchedThread.add(thread)
                    }

                    thread.query.contains(p0.toString(), ignoreCase = true) -> {
                        searchedThread.add(thread)
                    }

                    thread.dept.contains(p0.toString(), ignoreCase = true) -> {
                        searchedThread.add(thread)
                    }

                    thread.question_no.toString().contains(p0.toString(), ignoreCase = true) -> {
                        searchedThread.add(thread)
                    }

                    thread.register_no.contains(p0.toString(), ignoreCase = true) -> {
                        searchedThread.add(thread)
                    }

                    thread.subject.contains(p0.toString(), ignoreCase = true) -> {
                        searchedThread.add(thread)
                    }
                }
            }
            recent_threads.layoutManager = LinearLayoutManager(this)
            recent_threads.adapter = RecentsThreadsAdapter(searchedThread, this@RecentThreadsActivity)
        } else {
            recent_threads.layoutManager = LinearLayoutManager(this)
            recent_threads.adapter =
                    RecentsThreadsAdapter(recentThreads.result as ArrayList<Thread>, this@RecentThreadsActivity)

        }
    }

    private fun getAllThreads() {

        recentThreadsActivityViewModel.viewAllThreads()
    }

    private fun observeResponse() {
        recentThreadsActivityViewModel.response.observe(this, Observer { it ->
            processResponse(it)
        })
    }

    private fun processResponse(response: Response?) {
        when (response!!.status) {
            Status.SUCCESS -> {
                Log.v("Recent Thread", "API call successful")
                processResult(response)
                swipeRefresh.isRefreshing = false

            }
            Status.ERROR -> {
                Log.v("Recent Threads", "API Error")
                swipeRefresh.isRefreshing = false
            }
            Status.LOADING -> {
                Log.v("Recent Threads", "API Loading")
            }
        }

    }

    private fun processResult(response: Response) {
        recentThreads = response.result as RecentThreads
        if (recentThreads.status == "ok") {
            recent_threads.layoutManager = LinearLayoutManager(this)
            recent_threads.adapter =
                    RecentsThreadsAdapter(recentThreads.result as ArrayList<Thread>, this@RecentThreadsActivity)
        } else {
            showToast(recentThreads.error)
        }
    }

    override fun onResume() {
        super.onResume()
        onRefresh()
    }
}
