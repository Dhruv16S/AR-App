package com.ar.sihproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.mbms.StreamingServiceInfo
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignupPage : AppCompatActivity() {

    lateinit var userName : TextInputEditText
    lateinit var userEmail : TextInputEditText
    lateinit var password : TextInputEditText
    lateinit var confirmPassword : TextInputEditText
    lateinit var signUp : Button
    lateinit var userNameLayout : TextInputLayout
    lateinit var userEmailLayout : TextInputLayout
    lateinit var passwordLayout : TextInputLayout
    lateinit var confirmPasswordLayout : TextInputLayout
    lateinit var progressBar: ProgressBar
    lateinit var checkBox: CheckBox
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_page)

        userName = findViewById(R.id.userName)
        userEmail = findViewById(R.id.userEmail)
        password = findViewById(R.id.userPassword)
        confirmPassword = findViewById(R.id.confirmPassword)
        signUp = findViewById(R.id.signUpButton)
        userNameLayout = findViewById(R.id.eventTitleCard)
        userEmailLayout = findViewById(R.id.eventTitleCardEmail)
        passwordLayout = findViewById(R.id.passwordInputLayout)
        confirmPasswordLayout = findViewById(R.id.confirmPasswordInputLayout)
        progressBar = findViewById(R.id.progressBar)
        checkBox = findViewById(R.id.checkBox)
        auth = Firebase.auth


        userName.setOnClickListener {
            userNameLayout.error = null
        }

        userNameLayout.setOnClickListener {
            userNameLayout.error = null
        }

        userEmail.setOnClickListener {
            userEmailLayout.error = null
        }

        userEmailLayout.setOnClickListener {
            userEmailLayout.error = null
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

        var status : String = "Visitor"

        checkBox.setOnClickListener {
            if(checkBox.isChecked)
                status = "Admin"
        }



        signUp.setOnClickListener { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)

            val checkFields: Boolean = userAuthentication()

            if(checkFields) {
                progressBar.visibility = View.VISIBLE
                auth.createUserWithEmailAndPassword(
                    userEmail.text.toString(),
                    password.text.toString()
                ).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d("Message","$status")
                        progressBar.visibility = View.GONE
                        Toast.makeText(baseContext, "Authentication Successful.", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this@SignupPage, VisitorAppUI :: class.java)
                        startActivity(intent)
                    }
                    else {
                        progressBar.visibility = View.GONE
                        try {
                            throw task.exception!!
                        }
                        catch(error : FirebaseAuthUserCollisionException){
                            Toast.makeText(this, "This Account Already Exists", Toast.LENGTH_SHORT).show()
                        }
                        finally {
                            Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        }
    }

    private fun userAuthentication() : Boolean{
        if(userName.length() == 0){
            userNameLayout.error = "Enter A Valid Username"
            userName.requestFocus()
            return false
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail.text.toString()).matches()){
            userEmailLayout.error = "Enter Valid Email Id"
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
