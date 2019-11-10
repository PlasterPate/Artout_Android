package com.mbglobal.artoutandroid.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentProfileBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbglobal.artoutandroid.ui.profile.Adapter.EventAdapter
import com.mbglobal.artoutandroid.ui.profile.Adapter.onEventItemClickListener
import com.mbglobal.data.entity.event.EventEntity

class ProfileFragment : BaseFragment(), onEventItemClickListener{

    lateinit var slug: String
    lateinit var binding : FragmentProfileBinding
    lateinit var adapter : EventAdapter

    private val profileViewModel : ProfileViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[ProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = EventAdapter(this)
        binding.myEventsList.adapter = adapter
        initializeListeners()
        initializeObservers()

        binding.myEventsList.let { recyclerView ->
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(view.context)
        }

        val userId = ProfileFragmentArgs.fromBundle(arguments!!).userId
        profileViewModel.getUserEvents(userId)
        profileViewModel.userEvents.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.refreshData(it)
            }
        })


    }

    private fun initializeObservers() {

        profileViewModel.logoutStatus.observe(this, Observer {
            if (it == true) {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())
            }
        })

        profileViewModel.logoutError.observe(this, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

    }

    private fun initializeListeners() {
        binding.btnLogout.setOnClickListener {
            profileViewModel.clickLogout()
        }
    }

    override fun onClicked(eventEntity: EventEntity) {
        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToEventDetailsFragment(eventEntity.id))
    }
}
