package com.ar.sihproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var login : Button
    lateinit var signUp : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login = findViewById(R.id.login)
        signUp = findViewById(R.id.signUp)

        login.setOnClickListener {
            val intent = Intent(this, LoginPage :: class.java)
            startActivity(intent)
        }

        signUp.setOnClickListener {
            val intent = Intent(this, SignupPage :: class.java)
            startActivity(intent)
        }


    }



}