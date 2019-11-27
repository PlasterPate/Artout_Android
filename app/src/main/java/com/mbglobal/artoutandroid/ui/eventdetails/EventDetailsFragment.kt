package com.mbglobal.artoutandroid.ui.eventdetails

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.app.MainActivity
import com.mbglobal.artoutandroid.databinding.FragmentEventDetailsBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_event_details.*

class EventDetailsFragment : BaseFragment() {

    private var eventId: Int? = null

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

        eventDetailsViewModel.loadEvent(eventId?:0)

        binding.rvDetails.let {
            it.layoutManager = LinearLayoutManager(view.context)
        }

        binding.toolbarEventDetail.setOnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.edit_menu_item -> {
                    findNavController().navigate(
                        EventDetailsFragmentDirections
                            .actionEventDetailsFragmentToEditEventFragment(eventId!!)
                    )
                    return@setOnMenuItemClickListener true
                }
            }
            false
        }

        initializeObservers()
        initializeListeners()
    }

    private fun initializeListeners() {
        binding.toolbarEventDetail.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initializeObservers() {

        eventDetailsViewModel.eventEntity.observe(this, Observer {
            binding.rvDetails.adapter = EventDetailsAdapter(it)
        })
    }
}