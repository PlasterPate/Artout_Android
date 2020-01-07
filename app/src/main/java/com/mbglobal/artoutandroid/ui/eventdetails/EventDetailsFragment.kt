package com.mbglobal.artoutandroid.ui.eventdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentEventDetailsBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import com.mbglobal.data.entity.event.EventEntity

class EventDetailsFragment : BaseFragment() {

    private var eventId: Int? = null

    private val eventDetailsViewModel: EventDetailsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[EventDetailsViewModel::class.java]
    }

    lateinit var binding: FragmentEventDetailsBinding

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

        eventDetailsViewModel.loadEvent(eventId ?: 0)

        binding.rvDetails.apply {
            layoutManager = LinearLayoutManager(view.context)
            val divider = DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL)
            divider.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!)
            addItemDecoration(divider)
        }


        initializeObservers()
        initializeListeners()
    }

    private fun initializeListeners() {
        binding.toolbarEventDetail.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.toolbarEventDetail.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.edit_menu_item -> {
                    findNavController().navigate(
                        EventDetailsFragmentDirections
                            .actionEventDetailsFragmentToEditEventFragment(eventId!!)
                    )
                    return@setOnMenuItemClickListener true
                }

                R.id.checkin_menu_item -> {
                    eventDetailsViewModel.checkin()
                }
            }
            false
        }
    }

    private fun initializeObservers() {

        eventDetailsViewModel.eventEntity.observe(this, Observer {
            binding.rvDetails.adapter = EventDetailsAdapter(it,
                object : OnCheckinListItemClickListener{
                    override fun onClicked(eventEntity: EventEntity) {
                        findNavController().navigate(EventDetailsFragmentDirections
                            .actionEventDetailsFragmentToEventCheckinListFragment(eventEntity.id.toString()))
                    }
                })
        })

        eventDetailsViewModel.checkinStatus.observe(this, Observer {message ->
            Snackbar.make(requireView(), message.getContentIfNotHandled()!!, Snackbar.LENGTH_LONG).show()
        })

        eventDetailsViewModel.checkinStateTemp.observe(this, Observer {
            if (it){
                binding.toolbarEventDetail.menu.findItem(R.id.checkin_menu_item).title = "Check In"
            }
            else{
                binding.toolbarEventDetail.menu.findItem(R.id.checkin_menu_item).title = "Check Out"
            }
        })
    }
}