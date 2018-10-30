package com.example.prabh.stackelab.mvvm.activity.registeractivity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.prabh.stackelab.R
import com.example.prabh.stackelab.mvvm.activity.mainactivity.MainActivity
import com.example.prabh.stackelab.mvvm.application.StackElabApplication
import com.example.prabh.stackelab.retrofit.model.AddGeneric
import com.example.prabh.stackelab.utility.Response
import com.example.prabh.stackelab.utility.Status
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject

class RegisterActivity : StackElabApplication() {


    private var deparment = null.toString()

    @Inject
    lateinit var registerActivityViewModel: RegisterActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerActivityComponent.inject(this)
        initialise()
    }

    private fun initialise() {
        request_login.setOnClickListener {
            registerUser()
        }
        dept.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.swe -> deparment = "Software"
                R.id.cse -> deparment = "Computer Science"
                R.id.it -> deparment = "Information Technology"
            }
        }
        observeResponse()
    }

    private fun observeResponse() {
        registerActivityViewModel.response.observe(this, Observer { it ->
            processResponse(it)
        })
    }

    private fun processResponse(response: Response?) {
        when (response!!.status) {
            Status.SUCCESS -> {
                Log.v("Register", "API call successful")
                processResult(response)
            }
            Status.ERROR -> {
                Log.v("Register", "API Error")
            }
            Status.LOADING -> {
                Log.v("Register", "API Loading")
            }
        }

    }

    private fun processResult(response: Response) {
        val registerUser = response.result as AddGeneric
        if (registerUser.status == "ok") {
            if (registerUser.result!!) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        } else {
            showToast(registerUser.error!!)
        }
    }


    private fun registerUser() {

        if (password.text.toString() == confirm_password.text.toString()) {
            if (deparment != "null") {
                if (reg_no.text.toString().length == 15) {
                    registerActivityViewModel.registerUser(
                        name.text.toString(),
                        reg_no.text.toString(),
                        deparment,
                        password.text.toString()
                    )
                } else {
                    showToast("Incorrect Register Number.")
                }
            } else {
                showToast("Please Select Department.")
            }
        } else {
            showToast("Passwords Do Not Match.")
        }
    }
}
