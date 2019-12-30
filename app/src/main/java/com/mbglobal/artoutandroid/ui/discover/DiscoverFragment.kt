package com.mbglobal.artoutandroid.ui.discover

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.lapism.searchview.Search

import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentDiscoverBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import timber.log.Timber



class DiscoverFragment : BaseFragment(), Search.OnQueryTextListener, Search.OnOpenCloseListener {

    override fun onOpen() {
    }

    override fun onClose() {
        binding.searchView.setText("")
    }

    val discoverViewModel : DiscoverViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory)[DiscoverViewModel::class.java]
    }

    lateinit var binding : FragmentDiscoverBinding

    private val adapter: SearchAdapter by lazy {
        SearchAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val contextThemeWrapper = ContextThemeWrapper(activity, R.style.AppThemeLight)
        val localInflater = inflater.cloneInContext(contextThemeWrapper)

        binding = DataBindingUtil.inflate(
            localInflater, R.layout.fragment_discover, container, false
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
        discoverViewModel.previewSearchResults.observe(this, Observer { previewResults ->
            adapter.events = previewResults.first
            adapter.users = previewResults.second
            binding.searchView.onFilterComplete(10)
        })
    }

    override fun onQueryTextSubmit(query: CharSequence?): Boolean {
        findNavController().navigate(DiscoverFragmentDirections.actionNavigationDiscoverToSearchResultFragment())
        discoverViewModel.submitSearch(query.toString())
        return true
    }

    override fun onQueryTextChange(newText: CharSequence?) {
        discoverViewModel.queryChange(newText.toString())
    }

    private fun initializeListeners(){
        binding.addEventFab.setOnClickListener{
            findNavController().navigate(DiscoverFragmentDirections.actionNavigationDiscoverToAddEventFragment())
        }
    }

}
