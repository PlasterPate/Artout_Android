package com.mbglobal.artoutandroid.ui.eventlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentEventListBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import com.mbglobal.artoutandroid.ui.eventlist.adapter.EventListAdapter
import com.mbglobal.artoutandroid.ui.eventlist.adapter.OnEventItemClickListener
import com.mbglobal.artoutandroid.ui.profile.ProfileFragmentDirections
import com.mbglobal.data.entity.event.EventEntity

class EventListFragment : BaseFragment(), OnEventItemClickListener {

    var userId: String? = null
    var adapter: EventListAdapter? = null

    lateinit var binding: FragmentEventListBinding

    private val eventListViewModel: EventListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[EventListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObservers()

        arguments?.let {
            userId = EventListFragmentArgs.fromBundle(it).userId
        }


        binding.eventsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = EventListAdapter(this@EventListFragment)
        }

        adapter = binding.eventsList.adapter as EventListAdapter

        eventListViewModel.getUserEvents(userId)

    }

    private fun initializeObservers() {
        eventListViewModel.userEvents.observe(this, Observer { events ->
            adapter?.refreshData(events)
            binding.progress.visibility = View.GONE
        })
    }

    override fun onClicked(eventEntity: EventEntity) {
        findNavController().navigate(EventListFragmentDirections.actionEventListFragmentToEventDetailsFragment(eventEntity.id))
    }

}