package com.mbglobal.artoutandroid.ui.users.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.users.adapter.listener.OnActionButtonClickListener
import com.mbglobal.data.UserState
import com.mbglobal.data.entity.user.UserEntity
import com.squareup.picasso.Picasso

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name by lazy {
        itemView.findViewById(R.id.tv_name) as TextView
    }

    private val profilePicture by lazy {
        itemView.findViewById(R.id.iv_profile_image) as ImageView
    }

    private val followButton by lazy {
        itemView.findViewById(R.id.btn_follow) as Button
    }

    private val followingButton by lazy {
        itemView.findViewById(R.id.btn_following) as Button
    }

    private val requestedButton by lazy {
        itemView.findViewById(R.id.btn_requested) as Button
    }

    fun bind(userEntity: UserEntity, onUserItemClickListener: OnUserItemClickListener, onActionButtonClickListener: OnActionButtonClickListener) {
        val fullName = "${userEntity.firstName} ${userEntity.lastName}"
        name.text = fullName
        if (userEntity.avatar.isNotEmpty())
            Picasso.get().load(userEntity.avatar).into(profilePicture)
        //TODO
//        if (userListItem.state == UserState.FOLLOWING) {
//            followingButton.visibility = View.VISIBLE
//        }

        when (userEntity.state) {
            UserState.FOLLOWING -> followingButton.visibility = View.VISIBLE
            UserState.REQUESTED -> requestedButton.visibility = View.VISIBLE
            UserState.NOT_FOLLOWING -> followButton.visibility = View.VISIBLE
            UserState.OWNER -> {
                followButton.visibility = View.GONE
                requestedButton.visibility = View.GONE
                followingButton.visibility = View.GONE
            }
        }

        when (onActionButtonClickListener.stateTag) {
            UserState.FOLLOWING -> followingButton.setOnClickListener {
                onActionButtonClickListener.onClicked(userEntity)
            }
            UserState.NOT_FOLLOWING -> followButton.setOnClickListener {
                onActionButtonClickListener.onClicked(userEntity)
            }
            UserState.REQUESTED -> requestedButton.setOnClickListener {
                onActionButtonClickListener.onClicked(userEntity)
            }
            UserState.OWNER -> {}
        }

        itemView.setOnClickListener {
            onUserItemClickListener.onClicked(userEntity)
        }
    }

}