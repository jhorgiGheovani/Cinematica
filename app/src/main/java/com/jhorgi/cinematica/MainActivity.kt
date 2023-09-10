package com.jhorgi.cinematica

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.jhorgi.cinematica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navController = findNavController(R.id.nav_host_fragment_container)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as  NavHostFragment
        val navController = navHostFragment.navController

        AppBarConfiguration.Builder(
            R.id.navigation_home, R.id.navigation_search, R.id.navigation_favorite
        ).build()

        binding.bottomNav.setupWithNavController(navController)

    }
}