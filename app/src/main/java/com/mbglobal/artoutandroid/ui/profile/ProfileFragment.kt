package com.mbglobal.artoutandroid.ui.profile

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentProfileBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import com.mbglobal.artoutandroid.ui.profile.adapter.ProfileItem
import com.mbglobal.artoutandroid.ui.profile.adapter.ProfileItemsAdapter
import com.mbglobal.artoutandroid.ui.profile.listener.OnProfileItemClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_add_friend.*

class ProfileFragment : BaseFragment() {

    //private var userId: String? = null
    lateinit var followDialog: AlertDialog
    lateinit var binding: FragmentProfileBinding
    private val adapter: ProfileItemsAdapter by lazy {
        ProfileItemsAdapter()
    }

    private val profileViewModel: ProfileViewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory)[ProfileViewModel::class.java]
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
        binding.rvProfileItems.layoutManager = LinearLayoutManager(view.context)
        adapter.items = listOf(
            ProfileItem(
                titleResource = R.string.events,
                iconResource = R.drawable.ic_favorite_grey_24dp,
                count = 6,
                tag = ProfileItem.EVENTS
            ),
            ProfileItem(
                titleResource = R.string.checkins,
                iconResource = R.drawable.ic_check_ins_24dp,
                count = 21,
                tag = ProfileItem.CHECKINS
            )
        )
        binding.rvProfileItems.adapter = adapter


        adapter.listeners.apply {
            add(object : OnProfileItemClickListener {

                override val itemTag: String = ProfileItem.EVENTS

                override fun onClicked(profileItem: ProfileItem) {
                    findNavController().navigate(
                        ProfileFragmentDirections.actionProfileFragmentToEventListFragment(
                            null
                        )
                    )
                }

            })
            add(object : OnProfileItemClickListener {

                override val itemTag: String = ProfileItem.CHECKINS

                override fun onClicked(profileItem: ProfileItem) {
                    findNavController().navigate(
                        ProfileFragmentDirections.actionNavigationProfileToCheckinListFragment(
                            null
                        )
                    )
                }
            })
        }
        profileViewModel.getUserProfile()
        initializeListeners()
        initializeObservers()
    }

    private fun initializeListeners() {

        binding.btnAddFriend.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            followDialog = builder.setView(R.layout.dialog_add_friend).create()
            followDialog.show()

            followDialog.dialog_btn_add.setOnClickListener {
                profileViewModel.sendFollowRequest(
                    followDialog.findViewById<EditText>(
                        R.id
                            .dialog_edit_text
                    ).text.toString()
                )
            }

            followDialog.dialog_btn_cancel.setOnClickListener {
                followDialog.hide()
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

        profileViewModel.userProfile.observe(this, Observer {
            binding.tvFullName.text = it.user.firstName.plus(" ").plus(it.user.lastName)
            binding.tvFollowCount.text = it.followerCount
            binding.tvFollowingCount.text = it.followingCount
            Picasso.get().load(it.user.avatar)
                .into(binding.ivProfileImage)
        })

        profileViewModel.logoutStatus.observe(this, Observer {
            if (it.getContentIfNotHandled() == true) {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())
            }
        })

        profileViewModel.logoutError.observe(this, Observer {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
        })

        profileViewModel.followStatus.observe(this, Observer {
            if (it.getContentIfNotHandled() == true) {
                followDialog.hide()
                Snackbar.make(requireView(), "Follow request Sent", Snackbar.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.listeners.clear()
    }

}
