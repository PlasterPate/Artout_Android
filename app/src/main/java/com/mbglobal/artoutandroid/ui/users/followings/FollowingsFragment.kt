package com.mbglobal.artoutandroid.ui.users.followings

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
import com.mbglobal.artoutandroid.databinding.FragmentFollowingsBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import com.mbglobal.artoutandroid.ui.users.SocialViewModel
import com.mbglobal.artoutandroid.ui.users.adapter.OnUserItemClickListener
import com.mbglobal.artoutandroid.ui.users.adapter.UserAdapter
import com.mbglobal.artoutandroid.ui.users.adapter.listener.OnActionButtonClickListener
import com.mbglobal.data.UserState
import com.mbglobal.data.entity.user.UserEntity

class FollowingsFragment : BaseFragment(), OnUserItemClickListener {

    val socialViewModel: SocialViewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory)[SocialViewModel::class.java]
    }

    lateinit var binding: FragmentFollowingsBinding

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
        initializeObservers()
        socialViewModel.loadFollowings(null)
    }

    private fun initializeObservers() {
        socialViewModel.followings.observe(this, followingsObserver)
    }

    override fun onClicked(userEntity: UserEntity) {
        findNavController().navigate(FollowingsFragmentDirections.actionFollowingsFragmentToUserProfileFragment(userEntity.id.toString()))
    }

    private val followingsObserver: Observer<List<UserEntity>> = Observer {
        binding.rvFollowings.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = UserAdapter()
        }

        with(binding.rvFollowings.adapter as UserAdapter) {
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

            onUserItemClickListener = this@FollowingsFragment
        }
    }

}
