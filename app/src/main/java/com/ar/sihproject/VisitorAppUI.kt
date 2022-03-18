package com.ar.sihproject

import android.animation.ValueAnimator
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

class VisitorAppUI : AppCompatActivity() {

    lateinit var menuButton : ImageButton
    lateinit var circleTile : ConstraintLayout
    lateinit var menu1 : LinearLayout
    lateinit var menu2 : LinearLayout
    lateinit var menu3 : LinearLayout

    var viewList = mutableListOf<LinearLayout>()
    var menuTitleList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visitor_app_ui)

        menuTitleList.add("Maps")
        menuTitleList.add("Favourites")
        menuTitleList.add("Scan and View")

        findView()
        setListener()

    }

    private fun findView(){
        menuButton = findViewById(R.id.menuButton)
        circleTile = findViewById(R.id.circleTile)
        menu1 = findViewById(R.id.menu1)
        menu2 = findViewById(R.id.menu2)
        menu3 = findViewById(R.id.menu3)

        viewList.add(menu1)
        viewList.add(menu2)
        viewList.add(menu3)

    }

    private fun setListener(){
        menuButton.setOnClickListener{
            toggleCircleMenu()
        }
        val listener = createTouchListener()
        circleTile.setOnTouchListener(listener)
        for(view in viewList){
            view.setOnTouchListener(listener)
        }
    }

    private fun createTouchListener() : View.OnTouchListener{
        return object : View.OnTouchListener{

            var x : Float = 0f
            var isTouched = false

            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                when(event?.action){
                    MotionEvent.ACTION_DOWN ->{
                        event.let {
                            x = it.x
                        }
                        return if(v is LinearLayout || v is ConstraintLayout){
                            isTouched = true
                            true
                        }
                        else{
                            isTouched = false
                            false
                        }
                    }
                    MotionEvent.ACTION_MOVE->{
                        return true
                    }
                    MotionEvent.ACTION_UP->{
                        val result = event.x - x
                        when{
                            result > 100 ->{
                                Log.d("???", "Flip Right")
                                for(linearLayout in viewList){
                                    startRotate(linearLayout, false)
                                }
                            }

                            result < -100 ->{
                                Log.d("???", "Flip left")
                                for(linearLayout in viewList){
                                    startRotate(linearLayout, true)
                                }
                            }

                            else ->{
                                Log.d("???", "Just Touch")
                                if(v is LinearLayout)
                                toggleClick(v)

                            }
                        }
                        return false
                    }
                    else ->{
                        return false
                    }
                }
                return false
            }

        }
    }

    private fun startRotate(currentView : LinearLayout, isLeft : Boolean){
        val layoutParams = currentView.layoutParams as ConstraintLayout.LayoutParams
        val currentAngle = layoutParams.circleAngle
        val targetAngle = currentAngle + if(isLeft){
            -45
        }
        else{
            45
        }
        val angleAnimator = ValueAnimator.ofFloat(currentAngle, targetAngle)
        angleAnimator.addUpdateListener {
            layoutParams.circleAngle = it.animatedValue as Float
            currentView.layoutParams = layoutParams
        }
        angleAnimator.duration = 400
        angleAnimator.interpolator = AnticipateOvershootInterpolator()
        angleAnimator.start()
    }

    private fun toggleClick(currentView : LinearLayout){
        for(i in 0 until viewList.size){
            if(currentView.id == viewList[i].id){
                Toast.makeText(this, "${menuTitleList[i]}", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun toggleCircleMenu(){
        if(circleTile.visibility == View.INVISIBLE){
            circleTile.visibility == View.GONE
        }
        else{
            circleTile.visibility == View.VISIBLE
        }
    }

}