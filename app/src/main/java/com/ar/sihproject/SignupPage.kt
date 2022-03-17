package com.ar.sihproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignupPage : AppCompatActivity() {

    lateinit var userName : TextInputEditText
    lateinit var password : TextInputEditText
    lateinit var confirmPassword : TextInputEditText
    lateinit var signUp : Button
    lateinit var userNameLayout : TextInputLayout
    lateinit var passwordLayout : TextInputLayout
    lateinit var confirmPasswordLayout : TextInputLayout
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_page)

        userName = findViewById(R.id.userName)
        password = findViewById(R.id.userPassword)
        confirmPassword = findViewById(R.id.confirmPassword)
        signUp = findViewById(R.id.signUpButton)
        userNameLayout = findViewById(R.id.eventTitleCard)
        passwordLayout = findViewById(R.id.passwordInputLayout)
        confirmPasswordLayout = findViewById(R.id.confirmPasswordInputLayout)
        progressBar = findViewById(R.id.progressBar)


        userName.setOnClickListener {
            userNameLayout.error = null
        }

        userNameLayout.setOnClickListener {
            userNameLayout.error = null
        }

        password.setOnClickListener {
            passwordLayout.error = null
        }

        passwordLayout.setOnClickListener {
            passwordLayout.error = null
        }

        confirmPassword.setOnClickListener {
            confirmPasswordLayout.error = null
        }

        confirmPasswordLayout.setOnClickListener {
            confirmPasswordLayout.error = null
        }

        signUp.setOnClickListener { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)

            val checkFields: Boolean = userAuthentication()
        }
    }

    private fun userAuthentication() : Boolean{
        if(userName.length() == 0){
            userNameLayout.error = "Enter A Valid Username"
            userName.requestFocus()
            return false
        }
        if(password.length() == 0 || password.length() < 6){
            passwordLayout.error = "A Minimum Of 6 Characters Are Required"
            password.requestFocus()
            return false
        }
        if(password.text.toString() != confirmPassword.text.toString()){
            confirmPasswordLayout.error = "Re Enter The Password"
            confirmPassword.requestFocus()
            return false
        }
        return true
    }

}
