package com.mbglobal.artoutandroid.ui.users.followings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentFollowingsBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import com.mbglobal.artoutandroid.ui.users.UserState
import com.mbglobal.artoutandroid.ui.users.adapter.UserAdapter
import com.mbglobal.artoutandroid.ui.users.adapter.UserListItem
import com.mbglobal.artoutandroid.ui.users.adapter.listener.OnUserItemClickListener
import com.mbglobal.data.entity.user.UserEntity

class FollowingsFragment : BaseFragment() {

    val followingsViewModel: FollowingsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[FollowingsViewModel::class.java]
    }

    lateinit var binding : FragmentFollowingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_followings, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFollowings.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = UserAdapter()
        }

        with(binding.rvFollowings.adapter as UserAdapter){
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
                        "XXXsAuLehXXX",
                        12,
                        "BIG",
                        "sauleh1"
                    ),
                    UserState.FOLLOWING
                ), UserListItem(
                    UserEntity(
                        "https://pbs.twimg.com/profile_images/959929674355765248/fk3ALoeH.jpg",
                        "KING_sAuLeh",
                        12,
                        "GOD",
                        "sauleh1"
                    ),
                    UserState.FOLLOWING
                ), UserListItem(
                    UserEntity(
                        "https://pbs.twimg.com/profile_images/959929674355765248/fk3ALoeH.jpg",
                        "SeAtUeLmEaHdi",
                        12,
                        "original",
                        "sauleh1"
                    ),
                    UserState.FOLLOWING
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