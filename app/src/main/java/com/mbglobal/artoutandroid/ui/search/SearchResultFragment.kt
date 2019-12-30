package com.mbglobal.artoutandroid.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentSearchResultBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import com.mbglobal.artoutandroid.ui.discover.DiscoverViewModel
import com.mbglobal.artoutandroid.ui.eventlist.adapter.OnEventItemClickListener
import com.mbglobal.artoutandroid.ui.users.adapter.OnUserItemClickListener
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.user.UserEntity
import timber.log.Timber

class SearchResultFragment : BaseFragment(), OnUserItemClickListener, OnEventItemClickListener {

    lateinit var binding: FragmentSearchResultBinding
    val discoverViewModel : DiscoverViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory)[DiscoverViewModel::class.java]
    }

    private val adapter: SearchResultAdapter by lazy {
        SearchResultAdapter(this, this)
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

    override fun onClicked(eventEntity: EventEntity) {
        findNavController().navigate(
            SearchResultFragmentDirections.actionSearchResultFragmentToEventDetailsFragment(
                eventEntity.id
            )
        )
    }

    override fun onClicked(userEntity: UserEntity) {
        findNavController().navigate(
            SearchResultFragmentDirections.actionSearchResultFragmentToUserProfileFragment(
                userEntity.id.toString()
            )
        )
    }

    private fun initializeObservers() {
        discoverViewModel.showSearchResultsLoading.observe(this, Observer {
            Timber.v("Show Progress $it")
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
            binding.rvSearchResults.visibility = if (it) View.GONE else View.VISIBLE
        })
        discoverViewModel.searchResults.observe(this, Observer { searchResults ->
            binding.rvSearchResults.layoutManager = LinearLayoutManager(requireContext())
            binding.rvSearchResults.adapter = adapter.apply {
                events = searchResults.first.toMutableList()
                users = searchResults.second.toMutableList()
            }
        })
        discoverViewModel.searchQuery.observe(this, Observer { searchQuery ->
            binding.tvTitle.text = resources.getString(R.string.search_query_prefix) + " " + searchQuery
        })
    }
}
