package com.mbglobal.artoutandroid.ui.eventcheckinlist

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
import com.mbglobal.artoutandroid.databinding.FragmentEventCheckinListBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import com.mbglobal.artoutandroid.ui.users.SocialViewModel
import com.mbglobal.artoutandroid.ui.users.adapter.OnUserItemClickListener
import com.mbglobal.artoutandroid.ui.users.adapter.UserAdapter
import com.mbglobal.artoutandroid.ui.users.adapter.listener.OnActionButtonClickListener
import com.mbglobal.data.UserState
import com.mbglobal.data.entity.user.UserEntity

class EventCheckinListFragment : BaseFragment(), OnUserItemClickListener {

    private val eventCheckinListViewModel: EventCheckinListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[EventCheckinListViewModel::class.java]
    }

    private val socialViewModel: SocialViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[SocialViewModel::class.java]
    }

    lateinit var adapter: EventCheckinListAdapter
    lateinit var binding: FragmentEventCheckinListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_event_checkin_list,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventCheckinListViewModel.setEventId(EventCheckinListFragmentArgs.fromBundle(arguments!!).eventId)
        eventCheckinListViewModel.loadCheckedInUsers()
        initializeObservers()

    }

    fun initializeObservers(){
        eventCheckinListViewModel.users.observe(this, userObserver)
    }

    override fun onClicked(userEntity: UserEntity) {
        findNavController().navigate(EventCheckinListFragmentDirections.
            actionEventCheckinListFragmentToUserProfileFragment(userEntity.id.toString()))
    }

    private val userObserver: Observer<List<UserEntity>> = Observer {
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = UserAdapter()
        }
        binding.progress.visibility = View.GONE

        with(binding.rvUsers.adapter as UserAdapter) {
            data = it.toMutableList()
            actionButtonListeners.add(object : OnActionButtonClickListener {
                override val stateTag: UserState
                    get() = UserState.NOT_FOLLOWING

                override fun onClicked(userEntity: UserEntity) {
                    this@with.updateUserState(userEntity, UserState.REQUESTED)
                    socialViewModel.followUser(userEntity.id.toString())
                }

            })

            actionButtonListeners.add(object : OnActionButtonClickListener {
                override val stateTag: UserState
                    get() = UserState.FOLLOWING

                override fun onClicked(userEntity: UserEntity) {
                    this@with.updateUserState(userEntity, UserState.NOT_FOLLOWING)
                    socialViewModel.unfollowUser(userEntity.id.toString())
                }

            })

            actionButtonListeners.add(object : OnActionButtonClickListener {
                override val stateTag: UserState
                    get() = UserState.REQUESTED

                override fun onClicked(userEntity: UserEntity) {
                    this@with.updateUserState(userEntity, UserState.NOT_FOLLOWING)
                    socialViewModel.cancelFollowRequest(userEntity.id.toString())
                }

            })

            onUserItemClickListener = this@EventCheckinListFragment
        }
    }
}