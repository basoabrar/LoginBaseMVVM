package com.example.mogawesubmission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.mogawesubmission.data.UserPreference
import com.example.mogawesubmission.ui.auth.AuthActivity
import com.example.mogawesubmission.ui.home.HomeActivity
import com.example.mogawesubmission.ui.starNewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreference = UserPreference (this)
        
        userPreference.authToken.asLiveData().observe(this, Observer {
            val activity = if(it == null) AuthActivity::class.java else HomeActivity::class.java
            starNewActivity(activity)
        })






    }
}