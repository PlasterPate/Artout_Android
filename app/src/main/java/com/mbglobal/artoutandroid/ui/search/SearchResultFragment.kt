package com.mbglobal.artoutandroid.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentSearchResultBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import com.mbglobal.artoutandroid.ui.discover.DiscoverViewModel
import timber.log.Timber

class SearchResultFragment : BaseFragment() {

    lateinit var binding: FragmentSearchResultBinding

    val discoverViewModel : DiscoverViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory)[DiscoverViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_search_result, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObservers()
    }

    private fun initializeObservers() {
        discoverViewModel.showSearchResultsLoading.observe(this, Observer {
            Timber.v("Show Progress $it")
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        })
        discoverViewModel.searchResults.observe(this, Observer { searchResults ->
            Toast.makeText(activity!!, searchResults.toString(), Toast.LENGTH_LONG).show()
        })
    }
}