package com.example.prabh.stackelab.mvvm.activity.addthreadactivity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.prabh.stackelab.R
import com.example.prabh.stackelab.mvvm.application.StackElabApplication
import com.example.prabh.stackelab.retrofit.model.AddGeneric
import com.example.prabh.stackelab.utility.Response
import com.example.prabh.stackelab.utility.Session
import com.example.prabh.stackelab.utility.Status
import kotlinx.android.synthetic.main.activity_add_thread.*
import kotlinx.android.synthetic.main.activity_recent_threads.*
import java.util.*
import javax.inject.Inject

class AddThreadActivity : StackElabApplication() {

    @Inject
    lateinit var addThreadActivityViewModel: AddThreadActivityViewModel
    var subjectSelected = ""
    var typeSelected = "thread"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_thread)
        addThreadActivityComponent.inject(this)
        initialise()
    }

    private fun initialise() {
        observeResponse()
        setSpinner()
        setRadioButton()
        submit.setOnClickListener {
            addThread()
        }
        back_button.setOnClickListener {
            this.onBackPressed()
        }
    }

    private fun addThread() {
        if (!question_no.text.toString().isEmpty() && !description.text.toString().isEmpty()) {

            addThreadActivityViewModel.addThread(
                question_no.text.toString(),
                description.text.toString(),
                session.getStringValue(Session.REGISTER_NUMBER),
                typeSelected,
                subjectSelected
            )
        }
        else{
            showToast("Please enter all the details.")
        }
    }

    private fun setRadioButton() {
        guide.isChecked=true
        type.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.guide -> typeSelected = "guide"
                R.id.testcase -> typeSelected = "testcase"
                R.id.thread -> typeSelected = "thread"
            }
        }

    }

    private fun setSpinner() {
        val data = ArrayList<String>()
        data.add("Python")
        data.add("Operating System")
        data.add("Data Structure")
        data.add("C")
        data.add("C++")
        data.add("Java")

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, data)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        subject.adapter = arrayAdapter
        subject.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                subjectSelected = data[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                subjectSelected = data[0]
            }
        }
    }

    private fun observeResponse() {
        addThreadActivityViewModel.response.observe(this, Observer { it ->
            processResponse(it)
        })
    }

    private fun processResponse(response: Response?) {
        when (response!!.status) {
            Status.SUCCESS -> {
                Log.v("Add Thread", "API call successful")
                processResult(response)
                swipeRefresh.isRefreshing = false
            }
            Status.ERROR -> {
                Log.v("Add Threads", "API Error")
                swipeRefresh.isRefreshing = false
            }
            Status.LOADING -> {
                Log.v("Add Threads", "API Loading")
            }
        }

    }

    private fun processResult(response: Response) {
        val addThread = response.result as AddGeneric
        if (addThread.status == "ok") {
            showToast("Thread Successfully Added!")
            this.onBackPressed()
        } else {
            showToast(addThread.error!!)
        }
    }
}
