package com.example.eduexam

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var logoImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logoImageView = findViewById(R.id.logoImageView)

        val fadeIn: Animation =  AnimationUtils.loadAnimation(this, R.anim.fade_in)
        fadeIn.setAnimationListener(object: Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                logoImageView.visibility = View.VISIBLE

            }

            override fun onAnimationEnd(animation: Animation?) {
               Handler().postDelayed({
                val intent = Intent(this@MainActivity, TestActivity::class.java)
                startActivity(intent)
                finish()
               }, 1000)
            }

            override fun onAnimationRepeat(animation: Animation?) {
                TODO("Not yet implemented")
            }
        })
        logoImageView.startAnimation(fadeIn)

    }
}



