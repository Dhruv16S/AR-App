package com.ar.sihproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginPage : AppCompatActivity() {

    lateinit var userName : TextInputEditText
    lateinit var userPassword : TextInputEditText
    lateinit var loginButton : Button
    lateinit var gotoSignUp : TextView
    lateinit var userInputLayout : TextInputLayout
    lateinit var passwordInputLayout : TextInputLayout
    lateinit var progressBar : ProgressBar
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        userName = findViewById(R.id.userName)
        userPassword = findViewById(R.id.userPassword)
        loginButton = findViewById(R.id.signUpButton)
        gotoSignUp = findViewById(R.id.gotoSignUp)
        userInputLayout = findViewById(R.id.eventTitleCard)
        passwordInputLayout = findViewById(R.id.passwordInputLayout)
        gotoSignUp = findViewById(R.id.gotoSignUp)
        progressBar = findViewById(R.id.progressBar)
        auth = Firebase.auth

        userName.setOnClickListener {
            userInputLayout.error = null
        }

        userInputLayout.setOnClickListener {
            userInputLayout.error = null
        }

        userPassword.setOnClickListener {
            passwordInputLayout.error = null
        }

        passwordInputLayout.setOnClickListener {
            passwordInputLayout.error = null
        }

        gotoSignUp.setOnClickListener {
            val intent = Intent(this@LoginPage, SignupPage :: class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)

            val checkFields : Boolean = userAuthentication()
            if(checkFields){
                progressBar.visibility = View.VISIBLE
                auth.signInWithEmailAndPassword(userName.text.toString(), userPassword.text.toString())
                    .addOnCompleteListener(this){task->
                        if(task.isSuccessful){
                            progressBar.visibility = View.GONE
                            val intent = Intent(this@LoginPage, VisitorAppUI :: class.java)
                            startActivity(intent)
                        }
                        else{
                            progressBar.visibility = View.GONE
                            Toast.makeText(this, task.exception!!.message, Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
    private fun userAuthentication(): Boolean{
        if(userName.length() == 0){
            userInputLayout.error = "Enter Username"
            userName.requestFocus()
            return false
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(userName.text.toString()).matches()){
            userInputLayout.error = "Enter Valid Email Id"
            userName.requestFocus()
            return false
        }

        if(userPassword.length() == 0) {
            passwordInputLayout.error = "Enter Password"
            userPassword.requestFocus()
            return false
        }
        return true
    }
}
