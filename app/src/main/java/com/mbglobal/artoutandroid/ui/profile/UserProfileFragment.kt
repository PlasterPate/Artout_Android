package com.mbglobal.artoutandroid.ui.profile

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
import com.mbglobal.artoutandroid.databinding.FragmentUserProfileBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import com.mbglobal.artoutandroid.ui.profile.adapter.ProfileItem
import com.mbglobal.artoutandroid.ui.profile.adapter.ProfileItemsAdapter
import com.mbglobal.artoutandroid.ui.profile.listener.OnProfileItemClickListener
import com.mbglobal.artoutandroid.ui.users.SocialViewModel
import com.mbglobal.data.UserState

class UserProfileFragment : BaseFragment() {


    lateinit var binding: FragmentUserProfileBinding
    val adapter: ProfileItemsAdapter by lazy {
        ProfileItemsAdapter()
    }

    private val profileViewModel: ProfileViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[ProfileViewModel::class.java]
    }

    private val socialViewModel: SocialViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[SocialViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel.setUserId(UserProfileFragmentArgs.fromBundle(arguments!!).userId)
        profileViewModel.getUserProfile()
        adapter.items = listOf(
            ProfileItem(
                titleResource = R.string.suggestions,
                iconResource = R.drawable.ic_favorite_grey_24dp,
                count = 45,
                tag = ProfileItem.SUGGESTIONS
            ),
            ProfileItem(
                titleResource = R.string.checkins,
                iconResource = R.drawable.ic_check_ins_24dp,
                count = 32,
                tag = ProfileItem.CHECKINS
            )
        )

        adapter.listeners.apply {
            add(object : OnProfileItemClickListener {

                override val itemTag: String = ProfileItem.SUGGESTIONS

                override fun onClicked(profileItem: ProfileItem) {

                }

            })
            add(object : OnProfileItemClickListener {

                override val itemTag: String = ProfileItem.CHECKINS

                override fun onClicked(profileItem: ProfileItem) {

                }

            })
        }

        binding.rvProfileItems.layoutManager = LinearLayoutManager(view.context)
        binding.rvProfileItems.adapter = adapter

        initializeObservers()
        initializeListeners()
    }

    private fun initializeListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnAction.apply {
            btnFollow.setOnClickListener {
                profileViewModel.changeUserState(UserState.REQUESTED)
                socialViewModel.followUser(profileViewModel.profileId!!)
            }

            btnFollowing.setOnClickListener {
                profileViewModel.changeUserState(UserState.NOT_FOLLOWING)
                socialViewModel.unfollowUser(profileViewModel.profileId!!)
            }

            btnRequested.setOnClickListener {
                profileViewModel.changeUserState(UserState.NOT_FOLLOWING)
                socialViewModel.cancelFollowRequest(profileViewModel.profileId!!)
            }
        }
    }

    private fun initializeObservers() {
        profileViewModel.userProfile.observe(this, Observer {
            binding.tvFullName.text = it.user.firstName.plus(" ").plus(it.user.lastName)
            println(it.user.state)
            when (it.user.state) {
                UserState.FOLLOWING -> binding.btnAction.btnFollowing.visibility = View.VISIBLE
                UserState.REQUESTED -> binding.btnAction.btnRequested.visibility = View.VISIBLE
                UserState.NOT_FOLLOWING -> {
                    binding.btnAction.btnFollow.visibility = View.VISIBLE
                    binding.btnAction.btnRequested.visibility = View.GONE
                }
            }
            binding.tvFollowCount.text = it.followerCount
            binding.tvFollowingCount.text = it.followingCount
        })
    }
}