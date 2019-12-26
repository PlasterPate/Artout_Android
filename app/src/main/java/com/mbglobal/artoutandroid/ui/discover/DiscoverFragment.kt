package com.mbglobal.artoutandroid.ui.discover

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentDiscoverBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import com.mbglobal.artoutandroid.ui.users.UserState
import com.mbglobal.artoutandroid.ui.users.adapter.UserAdapter
import com.mbglobal.artoutandroid.ui.users.adapter.UserListItem
import com.mbglobal.artoutandroid.ui.users.adapter.listener.OnUserItemClickListener
import com.mbglobal.data.entity.user.UserEntity
import com.mbglobal.data.repository.MockUserFactory

class DiscoverFragment : BaseFragment() {

    val discoverViewModel : DiscoverViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[DiscoverViewModel::class.java]
    }

    lateinit var binding : FragmentDiscoverBinding

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

        binding.searchView.adapter = UserAdapter()

        (binding.searchView.adapter as UserAdapter).let {
            it.listeners.add(object : OnUserItemClickListener {

                override val stateTag: UserState
                    get() = UserState.FOLLOWING

                override fun onClicked(userEntity: UserEntity) {
                }
            })
            it.data = listOf<UserListItem>(
                UserListItem(MockUserFactory.SAULEH, UserState.FOLLOWING)
            ).toMutableList()
        }
        binding.searchView.onFilterComplete(3)
    }

    private fun initializeListeners(){
        binding.addEventFab.setOnClickListener{
            findNavController().navigate(DiscoverFragmentDirections.actionEventsFragmentToAddEventFragment())
        }
    }

}
