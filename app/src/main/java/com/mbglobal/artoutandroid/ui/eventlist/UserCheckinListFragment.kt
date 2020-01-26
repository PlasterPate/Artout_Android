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
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentCheckinListBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import com.mbglobal.artoutandroid.ui.eventlist.adapter.EventListAdapter
import com.mbglobal.artoutandroid.ui.eventlist.adapter.OnEventItemClickListener
import com.mbglobal.data.entity.event.EventEntity

class UserCheckinListFragment : BaseFragment(), OnEventItemClickListener {

    private val userId: String? by lazy {
        EventListFragmentArgs.fromBundle(arguments!!).userId
    }
    var adapter: EventListAdapter? = null

    lateinit var binding: FragmentCheckinListBinding

    private val eventListViewModel: EventListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[EventListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checkin_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.checkinList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = EventListAdapter(this@UserCheckinListFragment)
        }

        adapter = binding.checkinList.adapter as EventListAdapter

        eventListViewModel.getUserCheckins(userId)

        initializeObservers()
    }

    private fun initializeObservers() {
        eventListViewModel.userEvents.observe(this, Observer { events ->
            adapter?.refreshData(events)
            binding.progress.visibility = View.GONE
        })
    }

    override fun onClicked(eventEntity: EventEntity) {
        findNavController().navigate(UserCheckinListFragmentDirections.actionCheckinListFragmentToEventDetailsFragment(eventEntity.id))
    }
}