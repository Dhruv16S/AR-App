package com.ar.sihproject

import android.graphics.drawable.Icon
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.ktx.Firebase

class AccountDetails : AppCompatActivity() {

    lateinit var titleTextView: TextView
    lateinit var userNameTextView: TextView
    lateinit var emailTextView: TextView
    lateinit var passwordTextView: TextView
    lateinit var personIcon: ImageView
    lateinit var emailIcon: ImageView
    lateinit var passwordIcon: ImageView
    lateinit var userAvatar:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_details)

        userNameTextView = findViewById(R.id.UserNameTextView)
        titleTextView = findViewById(R.id.titleTextView)
        emailTextView = findViewById(R.id.emailTextView)
        passwordTextView = findViewById(R.id.passwordTextView)
        personIcon = findViewById(R.id.personIcon)
        emailIcon = findViewById(R.id.emailIcon)
        passwordIcon = findViewById(R.id.passwordIcon)
        userAvatar = findViewById(R.id.UserAvatarImageView)


    }
}