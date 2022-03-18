package com.ar.sihproject

import android.animation.ValueAnimator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.hitomi.cmlibrary.CircleMenu

class VisitorAppUI : AppCompatActivity() {

    lateinit var cmMain : CircleMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visitor_app_ui)

        cmMain = findViewById(R.id.cmMain)
        cmMain.setMainMenu(Color.parseColor("#000000"), R.drawable.ic_menu, R.drawable.ic_cancel)

        cmMain.addSubMenu(Color.parseColor("#00AF91"), R.drawable.ic_home)
        cmMain.addSubMenu(Color.parseColor("#007965"), R.drawable.ic_favourite)
        cmMain.addSubMenu(Color.parseColor("#F58634"), R.drawable.ic_contact)
        cmMain.addSubMenu(Color.parseColor("#FFCC29"), R.drawable.ic_info)

        //adding click listeners to menu items
        cmMain.setOnMenuSelectedListener { it ->
            when (it) {
                0 -> Toast.makeText(this, "This is Home!", Toast.LENGTH_SHORT).show()

                1 -> Toast.makeText(this, "This is Favourite", Toast.LENGTH_SHORT).show()

                2 -> Toast.makeText(this, "This is Contact", Toast.LENGTH_SHORT).show()

                3 -> Toast.makeText(this, "This is About", Toast.LENGTH_SHORT).show()
            }
        }
    }
}