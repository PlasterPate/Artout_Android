package com.mbglobal.artoutandroid.ui.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentTimelineBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import com.mbglobal.artoutandroid.ui.eventlist.adapter.OnEventItemClickListener
import com.mbglobal.data.entity.event.EventEntity
import timber.log.Timber

class TimelineFragment : BaseFragment(), OnEventItemClickListener {

    override fun onClicked(eventEntity: EventEntity) {
        findNavController().navigate(
            TimelineFragmentDirections.actionNavigationTimelineToEventDetailsFragment(
                eventEntity.id
            )
        )
    }

    lateinit var binding : FragmentTimelineBinding

    private val timelineAdapter: TimelineAdapter by lazy {
        TimelineAdapter(this)
    }

    private val timelineViewModel: TimelineViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory)[TimelineViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contextThemeWrapper = ContextThemeWrapper(requireContext(), R.style.AppThemeDiscover)
        val localInflater = inflater.cloneInContext(contextThemeWrapper)

        binding = DataBindingUtil.inflate(
            localInflater, R.layout.fragment_timeline, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObservers()
        initializeViews()

        Timber.v("DBug -> Load timeline");

        timelineViewModel.initializeTimeline()

    }

    private fun initializeViews() {
        binding.rvTimelineItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = timelineAdapter
            setOnScrollChangeListener { _, _, _, _, _ ->
                (this.layoutManager as LinearLayoutManager).let {
                    if (it.findLastCompletelyVisibleItemPosition() == timelineAdapter.items.size - 1) {
                        timelineViewModel.loadTimeline()
                    }
                }
            }
        }
    }

    private fun initializeObservers() {
        timelineViewModel.timelineItems.observe(this, Observer {
            timelineAdapter.setData(it)
            binding.progress.visibility = View.GONE
        })
        timelineViewModel.showProgress.observe(this, Observer {
            if (it) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        })
    }

}