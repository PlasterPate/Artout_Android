package com.mbglobal.artoutandroid.ui.discover

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.lapism.searchview.Search

import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentDiscoverBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import com.mbglobal.data.repository.MockEventFactory
import com.mbglobal.data.repository.MockUserFactory

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

        binding.searchView.adapter = adapter
        adapter.users = listOf(MockUserFactory.MOBIN, MockUserFactory.SAULEH, MockUserFactory.SARAH)
        adapter.events = listOf(MockEventFactory.COLDPLAY_CONCERT)

        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.setOnOpenCloseListener(this)
    }

    override fun onQueryTextSubmit(query: CharSequence?): Boolean {
        findNavController().navigate(DiscoverFragmentDirections.actionEventsFragmentToAddEventFragment())
        return true
    }

    override fun onQueryTextChange(newText: CharSequence?) {
        discoverViewModel.onQueryChange(newText.toString())
    }

    private fun initializeListeners(){
        binding.addEventFab.setOnClickListener{
            findNavController().navigate(DiscoverFragmentDirections.actionEventsFragmentToAddEventFragment())
        }
    }

}
