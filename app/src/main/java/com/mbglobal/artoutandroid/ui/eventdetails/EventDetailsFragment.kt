package com.mbglobal.artoutandroid.ui.eventdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentEventDetailsBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_event_details.*

class EventDetailsFragment : BaseFragment() {

    private var eventId: String? = null

    private val eventDetailsViewModel : EventDetailsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[EventDetailsViewModel::class.java]
    }

    lateinit var binding : FragmentEventDetailsBinding

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

        eventId = EventDetailsFragmentArgs.fromBundle(arguments!!).eventId

        eventDetailsViewModel.loadEvent(eventId?:"")

        binding.rvDetails.let {
            it.layoutManager = LinearLayoutManager(view.context)
        }

        initializeObservers()
    }

    private fun initializeObservers() {

        eventDetailsViewModel.eventEntity.observe(this, Observer {
            binding.rvDetails.adapter = EventDetailsAdapter(it)
        })

    }
}