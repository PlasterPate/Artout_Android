package com.mbglobal.artoutandroid.app

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mbglobal.artoutandroid.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
    }

    val bottomNavigationFragments = listOf<Int>(
        R.id.navigation_timeline,
        R.id.navigation_profile,
        R.id.navigation_discover
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            navView.visibility =
                if (destination.id in bottomNavigationFragments)
                    View.VISIBLE
                else
                    View.GONE
        }

        initializeObservers()
        if (navController.currentDestination?.id == R.id.loginFragment) {
            mainViewModel.onCreate()
        }
    }

    private fun initializeObservers() {
        mainViewModel.loginStatus.observe(this, Observer { status ->
            status.getContentIfNotHandled()?.let {
                findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_timeline)
            }
        })
    }

}