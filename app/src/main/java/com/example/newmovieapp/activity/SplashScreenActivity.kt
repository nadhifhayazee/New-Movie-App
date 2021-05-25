package com.example.newmovieapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.newmovieapp.R
import com.example.newmovieapp.util.makeStatusBarTransparent

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splace_screen)
        makeStatusBarTransparent()
        Handler(Looper.getMainLooper()).postDelayed({
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }, 2000)
    }
}