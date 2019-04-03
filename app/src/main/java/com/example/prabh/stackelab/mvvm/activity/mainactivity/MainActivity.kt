package com.example.prabh.stackelab.mvvm.activity.mainactivity

import android.content.Intent
import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.prabh.stackelab.R
import com.example.prabh.stackelab.mvvm.activity.recentThreadsActivity.RecentThreadsActivity
import com.example.prabh.stackelab.mvvm.activity.registeractivity.RegisterActivity
import com.example.prabh.stackelab.mvvm.application.StackElabApplication
import com.example.prabh.stackelab.retrofit.model.AddGeneric
import com.example.prabh.stackelab.utility.Response
import com.example.prabh.stackelab.utility.Session
import com.example.prabh.stackelab.utility.Status
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_recent_threads.*
import javax.inject.Inject

class MainActivity : StackElabApplication() {

    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityComponent.inject(this)
        initialise()
    }

    private fun initialise() {
        observeResponse()
        register.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
        if(session.isLoggedIn()){
            register_no.setText(session.getStringValue(Session.REGISTER_NUMBER))
            password.setText(session.getStringValue(Session.PASSWORD))
            checkLogin()
        }
        login.setOnClickListener {
            checkLogin()
        }
    }



    private fun checkLogin() {
        if(!register_no.text.toString().isEmpty() && !password.text.toString().isEmpty())
        {
        mainActivityViewModel.checkLogin(register_no.text.toString(),password.text.toString())}
        else
        {
            showToast("Please fill all the details.")
        }
    }

    private fun observeResponse() {
        mainActivityViewModel.response.observe(this, Observer { it ->
            processResponse(it)
        })
    }

    private fun processResponse(response: Response?) {
        when (response!!.status) {
            Status.SUCCESS -> {
                Log.v("Login", "API call successful")
                processResult(response)
                progressDialog.dismiss()
            }
            Status.ERROR -> {
                Log.v("Login", "API Error")
                progressDialog.dismiss()
            }
            Status.LOADING -> {
                Log.v("Login", "API Loading")
                progressDialog.setMessage("Logging in..")
                progressDialog.setCancelable(false)
                progressDialog.show()
            }
        }

    }

    private fun processResult(response: Response) {
        val loginUser = response.result as AddGeneric
        if(loginUser.status=="ok")
        {
            if(loginUser.result!!){
                session.saveLoginDetails(register_no.text.toString(),password.text.toString())
                session.setIsLogedIn(true)
                startActivity(Intent(this,RecentThreadsActivity::class.java))
                finish()
            }
            else{
                showToast("Incorrect Credentials")
            }
        }
        else{
            showToast(loginUser.error!!)
        }
    }
}
