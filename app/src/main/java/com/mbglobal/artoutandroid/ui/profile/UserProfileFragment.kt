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
import com.mbglobal.artoutandroid.ui.users.adapter.UserAdapter
import com.mbglobal.artoutandroid.ui.users.adapter.UserListItem
import com.mbglobal.artoutandroid.ui.users.adapter.listener.OnUserItemClickListener
import com.mbglobal.data.UserState
import com.mbglobal.data.entity.user.UserEntity

class UserProfileFragment: BaseFragment() {


    lateinit var userId: String
    lateinit var binding: FragmentUserProfileBinding
    val adapter: ProfileItemsAdapter by lazy {
        ProfileItemsAdapter()
    }

    private val profileViewModel: ProfileViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[ProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userId = UserProfileFragmentArgs.fromBundle(arguments!!).userId
        profileViewModel.getUserProfile(userId)
        binding.rvProfileItems.layoutManager = LinearLayoutManager(view.context)
        binding.rvProfileItems.adapter = adapter
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
        initializeObservers()
        initializeListeners()
    }

    private fun initializeListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun initializeObservers() {
        profileViewModel.userProfile.observe(this, Observer {
            adapter.items = listOf(
                ProfileItem(
                    titleResource = R.string.suggestions,
                    iconResource = R.drawable.ic_favorite_grey_24dp,
                    count = it.suggestionCount,
                    tag = ProfileItem.SUGGESTIONS
                ),
                ProfileItem(
                    titleResource = R.string.checkins,
                    iconResource = R.drawable.ic_check_ins_24dp,
                    count = it.checkinCount,
                    tag = ProfileItem.CHECKINS
                )
            )
        })
    }
}