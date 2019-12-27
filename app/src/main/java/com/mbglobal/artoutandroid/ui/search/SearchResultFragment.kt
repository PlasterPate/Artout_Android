package com.mbglobal.artoutandroid.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentSearchResultBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import com.mbglobal.artoutandroid.ui.discover.DiscoverViewModel

class SearchResultFragment : BaseFragment() {

    lateinit var binding: FragmentSearchResultBinding

    val discoverViewModel : DiscoverViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[DiscoverViewModel::class.java]
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
}