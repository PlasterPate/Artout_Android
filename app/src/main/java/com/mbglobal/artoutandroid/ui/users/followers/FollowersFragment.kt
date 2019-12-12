package com.mbglobal.artoutandroid.ui.users.followers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentFollowersBinding
import com.mbglobal.artoutandroid.databinding.FragmentProfileBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import com.mbglobal.artoutandroid.ui.users.UserState
import com.mbglobal.artoutandroid.ui.users.adapter.FollowRequestAdapter
import com.mbglobal.artoutandroid.ui.users.adapter.UserAdapter
import com.mbglobal.artoutandroid.ui.users.adapter.UserListItem
import com.mbglobal.artoutandroid.ui.users.adapter.listener.OnFollowRequestClickListener
import com.mbglobal.artoutandroid.ui.users.adapter.listener.OnUserItemClickListener
import com.mbglobal.data.entity.user.FollowRequestEntity
import com.mbglobal.data.entity.user.UserEntity
import kotlinx.android.synthetic.main.fragment_followers.*
import okhttp3.internal.waitMillis

class FollowersFragment : BaseFragment() {

    val followersViewModel: FollowersViewModel by lazy {
        ViewModelProviders.of(this)[FollowersViewModel::class.java]
    }

    lateinit var binding: FragmentFollowersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_followers, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObservers()
        followersViewModel.loadFollowers(null)
        followersViewModel.loadPendingFollowRequests()
    }

    private fun initializeObservers() {
        followersViewModel.followers.observe(this, followersObserver)
        followersViewModel.followRequests.observe(this, followRequestsObserver)
    }

    private val followersObserver = Observer<List<UserEntity>> {
        binding.rvFollowers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = UserAdapter()
        }
        with(binding.rvFollowers.adapter as UserAdapter) {
            data = it.map {
                UserListItem(it, UserState.NOT_FOLLOWING)
            }
            listeners.add(object : OnUserItemClickListener {
                override val stateTag: UserState
                    get() = UserState.NOT_FOLLOWING

                override fun onClicked(userEntity: UserEntity) {
                    Toast.makeText(requireContext(), "Follow that guy", Toast.LENGTH_LONG)
                        .show()
                }

            })

            listeners.add(object : OnUserItemClickListener {

                override val stateTag: UserState
                    get() = UserState.FOLLOWING

                override fun onClicked(userEntity: UserEntity) {
                    Toast.makeText(
                        requireContext(),
                        "Fuck that guy, lets unfollow",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }

    private val followRequestsObserver = Observer<List<FollowRequestEntity>> {
        binding.rvPendingFollowers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = FollowRequestAdapter(object : OnFollowRequestClickListener {
                override fun onAcceptClicked(userEntity: UserEntity) {
                    Toast.makeText(requireContext(), "Accept request", Toast.LENGTH_LONG).show()
                }

                override fun onRejectClicked(userEntity: UserEntity) {
                    Toast.makeText(requireContext(), "Reject request", Toast.LENGTH_LONG).show()
                }

            })
        }

        with (binding.rvPendingFollowers.adapter as FollowRequestAdapter) {
            data = it.map {
                it.source
            }
        }
    }
}

