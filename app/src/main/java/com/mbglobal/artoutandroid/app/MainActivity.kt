package com.mbglobal.artoutandroid.app

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.discover.DiscoverFragmentDirections
import com.mbglobal.artoutandroid.ui.navigation.NavigationManager
import com.mbglobal.artoutandroid.ui.profile.ProfileFragmentDirections
import com.mbglobal.artoutandroid.ui.timeline.TimelineFragmentDirections
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation_view.setOnNavigationItemSelectedListener { item ->
            val currentDestinationId = findNavController(R.id.nav_host_fragment).currentDestination?.id

            when(item.itemId){
                R.id.navigation_timeline -> {
                    if (currentDestinationId == R.id.eventsFragment) {
                        findNavController(R.id.nav_host_fragment).navigate(DiscoverFragmentDirections.actionEventsFragmentToTimelineFragment())
                    } else if (currentDestinationId == R.id.profileFragment) {
                        findNavController(R.id.nav_host_fragment).navigate(ProfileFragmentDirections.actionProfileFragmentToTimelineFragment())
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_events -> {
                    if (currentDestinationId == R.id.timelineFragment) {
                        findNavController(R.id.nav_host_fragment).navigate(TimelineFragmentDirections.actionTimelineFragmentToEventsFragment())
                    } else if (currentDestinationId == R.id.profileFragment) {
                        findNavController(R.id.nav_host_fragment).navigate(ProfileFragmentDirections.actionProfileFragmentToEventsFragment())
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    val bundle = Bundle()
                    if (currentDestinationId == R.id.timelineFragment) {
                        findNavController(R.id.nav_host_fragment).navigate(TimelineFragmentDirections.actionTimelineFragmentToProfileFragment(null))
                    } else if (currentDestinationId == R.id.eventsFragment) {
                        findNavController(R.id.nav_host_fragment).navigate(DiscoverFragmentDirections.actionEventsFragmentToProfileFragment(null))
                    }
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

    private fun initializeNavigationController() {
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
        if (NavigationManager.tabbedFragmentIds.contains(selectedItemId) && bottom_navigation_view.visibility == View.VISIBLE) {
            bottom_navigation_view.selectedItemId = R.id.navigation_timeline
        } else {
            super.onBackPressed()
        }
    }
}