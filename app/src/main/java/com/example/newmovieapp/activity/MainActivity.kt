package com.example.newmovieapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newmovieapp.R
import com.example.newmovieapp.databinding.ActivityMainBinding
import com.example.newmovieapp.util.gone
import com.example.newmovieapp.util.makeStatusBarTransparent
import com.example.newmovieapp.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        makeStatusBarTransparent()
        binding.bottomNavView.setupWithNavController(findNavController(R.id.fragment))
    }

    fun hideBottomNavigation() {
        binding.bottomNavView.gone()
    }

    fun showBottomNavigation() {
        binding.bottomNavView.visible()
    }
}