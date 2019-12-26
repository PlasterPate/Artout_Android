package com.mbglobal.artoutandroid.ui.users.followers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentFollowersBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import com.mbglobal.artoutandroid.ui.users.SocialViewModel
import com.mbglobal.artoutandroid.ui.users.adapter.FollowRequestAdapter
import com.mbglobal.artoutandroid.ui.users.adapter.UserAdapter
import com.mbglobal.artoutandroid.ui.users.adapter.UserListItem
import com.mbglobal.artoutandroid.ui.users.adapter.listener.OnFollowRequestClickListener
import com.mbglobal.artoutandroid.ui.users.adapter.listener.OnUserItemClickListener
import com.mbglobal.data.UserState
import com.mbglobal.data.entity.user.FollowRequestEntity
import com.mbglobal.data.entity.user.UserEntity
import kotlinx.android.synthetic.main.fragment_followers.*

class FollowersFragment : BaseFragment() {

    val socialViewModel: SocialViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[SocialViewModel::class.java]
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
        initializeViews()
        socialViewModel.loadFollowers(null)
        socialViewModel.loadPendingFollowRequests()
    }

    private fun initializeViews() {
        binding.rvPendingFollowers.itemAnimator = object : DefaultItemAnimator() {
            override fun onAnimationFinished(viewHolder: RecyclerView.ViewHolder) {
                if ((binding.rvPendingFollowers.adapter as FollowRequestAdapter).data.size == 0) {
                    hidePendingGroup()
                }
            }
        }
    }

    private fun initializeObservers() {
        socialViewModel.followers.observe(this, followersObserver)
        socialViewModel.followRequests.observe(this, followRequestsObserver)
    }

    private val followersObserver = Observer<List<UserEntity>> {
        binding.rvFollowers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = UserAdapter()
        }
        with(binding.rvFollowers.adapter as UserAdapter) {
            data = it.map {
                UserListItem(it, it.state)
            }.toMutableList()
            listeners.add(object : OnUserItemClickListener {
                override val stateTag: UserState
                    get() = UserState.NOT_FOLLOWING

                override fun onClicked(userEntity: UserEntity) {
                    this@with.updateUserState(userEntity, UserState.FOLLOWING)
                    socialViewModel.followUser(userEntity)
                }

            })

            listeners.add(object : OnUserItemClickListener {

                override val stateTag: UserState
                    get() = UserState.FOLLOWING

                override fun onClicked(userEntity: UserEntity) {
                    this@with.updateUserState(userEntity, UserState.NOT_FOLLOWING)
                    socialViewModel.unfollowUser(userEntity)
                }
            })
        }
    }

    private val followRequestsObserver = Observer<List<FollowRequestEntity>> { requests ->
        if (requests.isEmpty()) hidePendingGroup()
        else showPendingGroup()

        binding.rvPendingFollowers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = FollowRequestAdapter(object : OnFollowRequestClickListener {
                override fun onAcceptClicked(userEntity: UserEntity) {
                    (adapter as FollowRequestAdapter).data.apply {
                        remove(userEntity)
                    }
                    (binding.rvFollowers.adapter as UserAdapter).data.add(
                        0,
                        UserListItem(
                            userEntity, UserState.NOT_FOLLOWING
                        )
                    )
                    binding.rvFollowers.scrollToPosition(0)
                    socialViewModel.acceptRequest(userEntity)
                }

                override fun onRejectClicked(userEntity: UserEntity) {
                    (adapter as FollowRequestAdapter).data.apply {

                        remove(userEntity)
                    }
                    socialViewModel.rejectRequest(userEntity)
                }

            })
        }

        with(binding.rvPendingFollowers.adapter as FollowRequestAdapter) {
            data = requests.map {
                it.source
            }.toMutableList()
        }
    }

    private fun hidePendingGroup() {
        binding.pendingGroup.visibility = View.GONE
    }

    private fun showPendingGroup() {
        binding.pendingGroup.visibility = View.VISIBLE
    }
}

