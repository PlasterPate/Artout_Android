package com.mbglobal.artoutandroid.ui.events

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentEventsBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment

class EventsFragment : BaseFragment() {

    val eventsViewModel : EventsViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory)[EventsViewModel::class.java]
    }

    lateinit var binding : FragmentEventsBinding

    private lateinit var viewModel: EventsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_events, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
