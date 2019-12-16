package com.mbglobal.artoutandroid.ui.profile

import android.app.AlertDialog
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
import com.mbglobal.artoutandroid.ui.profile.adapter.ProfileItem
import com.mbglobal.artoutandroid.ui.profile.adapter.ProfileItemsAdapter
import com.mbglobal.artoutandroid.ui.profile.listener.OnProfileItemClickListener
import com.mbglobal.data.entity.user.UserProfileEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_add_friend.*

class ProfileFragment : BaseFragment() {

    private var userId: String? = null
    lateinit var binding: FragmentProfileBinding
    var adapter: ProfileItemsAdapter? = null

    private val profileViewModel: ProfileViewModel by lazy {
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
        userId = ProfileFragmentArgs.fromBundle(arguments!!).userId
        if (userId == "0") userId = null
        binding.rvProfileItems.layoutManager = LinearLayoutManager(view.context)
        binding.rvProfileItems.adapter = ProfileItemsAdapter(
            UserProfileEntity(
                followerCount = 21,
                followingCount = 20,
                suggestionCount = 48,
                checkinCount = 30
            )
        )
        adapter = binding.rvProfileItems.adapter as ProfileItemsAdapter

        Picasso.get().load("https://pbs.twimg.com/profile_images/959929674355765248/fk3ALoeH.jpg")
            .into(binding.ivProfileImage)

        adapter!!.listeners.apply {
            add(object: OnProfileItemClickListener {

                override val itemTag: String = ProfileItem.SUGGESTIONS

                override fun onClicked(profileItem: ProfileItem) {
                    findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToEventListFragment(userId))
                }

            })
            add(object: OnProfileItemClickListener {

                override val itemTag: String = ProfileItem.CHECKINS

                override fun onClicked(profileItem: ProfileItem) {
                    findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToEventListFragment(userId))
                }

            })
        }

        initializeListeners()
        initializeObservers()
    }

    private fun initializeListeners() {

        binding.btnAddFriend.setOnClickListener{
            val builder = AlertDialog.Builder(requireContext())
            val dialog : AlertDialog = builder.setView(R.layout.dialog_add_friend).create()
            dialog.show()

            dialog.dialog_btn_add.setOnClickListener{
                profileViewModel.sendFollowRequest(dialog.)
            }

            dialog.dialog_btn_cancel.setOnClickListener{
                dialog.hide()
            }
        }

        binding.btnLogout.setOnClickListener {
            profileViewModel.clickLogout()
        }

        binding.containerFollowers.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToFollowersFragment())
        }

        binding.containerFollowings.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToFollowingsFragment())
        }
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

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }

}
