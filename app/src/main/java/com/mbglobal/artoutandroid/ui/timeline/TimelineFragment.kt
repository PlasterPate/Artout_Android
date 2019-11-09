package com.mbglobal.artoutandroid.ui.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentTimelineBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment

class TimelineFragment : BaseFragment() {

    lateinit var binding : FragmentTimelineBinding

    @Inject
    lateinit var viewModel: TimelineViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_timeline, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}