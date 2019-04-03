package com.example.prabh.stackelab.firebase

import android.arch.lifecycle.Observer
import android.util.Log
import android.widget.Toast
import com.example.prabh.stackelab.mvvm.activity.mainactivity.MainActivityViewModel
import com.example.prabh.stackelab.mvvm.application.StackElabApplication
import com.example.prabh.stackelab.utility.Session
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class FirebaseInstanceIdService: FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        val refreshedToken=FirebaseInstanceId.getInstance().token
                Log.d("FireBaseToken", "new token $refreshedToken")
            Toast.makeText(this,refreshedToken,Toast.LENGTH_SHORT).show()
    }

}