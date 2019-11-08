package com.mbglobal.artoutandroid.ui.eventdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentEventsBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment

class EventDetailsFragment : BaseFragment() {

    private val eventDetailsViewModel : EventDetailsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[EventDetailsViewModel::class.java]
    }

    lateinit var binding : FragmentEventsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_event_details, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}