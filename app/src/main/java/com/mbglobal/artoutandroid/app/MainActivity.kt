package com.mbglobal.artoutandroid.app

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.login.LoginFragment
import com.mbglobal.artoutandroid.ui.login.LoginViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation_view.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.navigation_timeline -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.timelineFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_events -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.eventsFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.profileFragment)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

        initializeNavigationController()
        initializeObservers()
        mainViewModel.onCreate()
    }

    private fun initializeObservers() {
        mainViewModel.loginStatus.observe(this, Observer { status ->
            if (status) {
                findNavController(R.id.nav_host_fragment).navigate(R.id.timelineFragment)
            }
        })
    }

    fun initializeNavigationController() {
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.timelineFragment, R.id.eventsFragment, R.id.profileFragment -> {
                    bottom_navigation_view.visibility = View.VISIBLE
                }
                else -> {
                    bottom_navigation_view.visibility = View.GONE
                }
            }
        }
    }

    override fun onBackPressed() {
        val selectedItemId = bottom_navigation_view.selectedItemId
        if (selectedItemId != R.id.navigation_timeline) {
            bottom_navigation_view.selectedItemId = R.id.navigation_timeline
        } else {
            super.onBackPressed()
        }
    }
}