package com.mbglobal.artoutandroid.ui.users.followers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
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
        binding.rvFollowers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = UserAdapter()
        }

        with (binding.rvPendingFollowers.adapter as FollowRequestAdapter) {
            data = listOf(
                UserEntity(
                    "https://pbs.twimg.com/profile_images/959929674355765248/fk3ALoeH.jpg",
                    "Saulehhhhhh",
                    12,
                    "Eeti123456789",
                    "sauleh"
                ),
                UserEntity(
                    "https://pbs.twimg.com/profile_images/959929674355765248/fk3ALoeH.jpg",
                    "Saulehe",
                    13,
                    "Saulehi",
                    "sauleh2"
                )
            )
        }

        with (binding.rvFollowers.adapter as UserAdapter) {
            data = listOf(
                UserListItem(
                    UserEntity(
                        "https://pbs.twimg.com/profile_images/959929674355765248/fk3ALoeH.jpg",
                        "sAuLeh",
                        12,
                        "Supfam",
                        "sauleh1"
                    ),
                    UserState.FOLLOWING
                ),
                UserListItem(
                    UserEntity(
                        "https://pbs.twimg.com/profile_images/959929674355765248/fk3ALoeH.jpg",
                        "My man",
                        12,
                        "sauleh",
                        "sauleh1"
                    ),
                    UserState.NOT_FOLLOWING
                ),
                UserListItem(
                    UserEntity(
                        "https://pbs.twimg.com/profile_images/959929674355765248/fk3ALoeH.jpg",
                        "XXXsAuLehXXX",
                        12,
                        "BiIiIiIG",
                        "sauleh1"
                    ),
                    UserState.FOLLOWING
                ),UserListItem(
                    UserEntity(
                        "https://pbs.twimg.com/profile_images/959929674355765248/fk3ALoeH.jpg",
                        "KING_sAuLehhhhhhh",
                        12,
                        "GOD",
                        "sauleh1"
                    ),
                    UserState.NOT_FOLLOWING
                )
            )
            listeners.add(object : OnUserItemClickListener {
                override val stateTag: UserState
                    get() = UserState.NOT_FOLLOWING

                override fun onClicked(userEntity: UserEntity) {
                    Toast.makeText(requireContext(), "Follow that guy", Toast.LENGTH_LONG).show()
                }

            })

            listeners.add(object : OnUserItemClickListener {
                override val stateTag: UserState
                    get() = UserState.FOLLOWING

                override fun onClicked(userEntity: UserEntity) {
                    Toast.makeText(requireContext(), "Fuck that guy, lets unfollow", Toast.LENGTH_LONG).show()
                }

            })
        }

    }

}