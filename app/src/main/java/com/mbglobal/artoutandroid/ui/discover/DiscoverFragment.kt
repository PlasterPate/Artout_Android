package com.mbglobal.artoutandroid.ui.discover

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.lapism.searchview.Search

import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentDiscoverBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import com.mbglobal.data.repository.MockEventFactory
import com.mbglobal.data.repository.MockUserFactory
import timber.log.Timber

class DiscoverFragment : BaseFragment(), Search.OnQueryTextListener, Search.OnOpenCloseListener {

    override fun onOpen() {
    }

    override fun onClose() {
        binding.searchView.setText("")
    }

    val discoverViewModel : DiscoverViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[DiscoverViewModel::class.java]
    }

    lateinit var binding : FragmentDiscoverBinding

    private val adapter: SearchAdapter by lazy {
        SearchAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_discover, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListeners()
        initializeObservers()

        binding.searchView.adapter = adapter

        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.setOnOpenCloseListener(this)
    }

    private fun initializeObservers() {
        discoverViewModel.users.observe(this, Observer { users ->
            Timber.v("Users are here $users")
            adapter.users = users
            binding.searchView.onFilterComplete(adapter.users.size + adapter.events.size)
        })

        discoverViewModel.events.observe(this, Observer { events ->
            Timber.v("Events are here $events")
            adapter.events = events
            binding.searchView.onFilterComplete(adapter.users.size + adapter.events.size)
        })
    }

    override fun onQueryTextSubmit(query: CharSequence?): Boolean {
        findNavController().navigate(DiscoverFragmentDirections.actionNavigationDiscoverToSearchResultFragment())
        return true
    }

    override fun onQueryTextChange(newText: CharSequence?) {
        discoverViewModel.onQueryChange(newText.toString())
    }

    private fun initializeListeners(){
        binding.addEventFab.setOnClickListener{
            findNavController().navigate(DiscoverFragmentDirections.actionNavigationDiscoverToAddEventFragment())
        }
    }

}
