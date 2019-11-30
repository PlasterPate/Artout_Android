package com.mbglobal.artoutandroid.ui.users.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.users.adapter.listener.OnFollowRequestClickListener
import com.mbglobal.data.entity.user.UserEntity
import com.squareup.picasso.Picasso

class FollowRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name by lazy {
        itemView.findViewById(R.id.tv_name) as TextView
    }

    private val profilePicture by lazy {
        itemView.findViewById(R.id.iv_profile_image) as ImageView
    }

    private val acceptButton by lazy {
        itemView.findViewById(R.id.btn_accept) as Button
    }

    private val rejectButton by lazy {
        itemView.findViewById(R.id.btn_reject) as Button
    }

    fun bind(userEntity: UserEntity, onFollowRequestClickListener: OnFollowRequestClickListener) {
        val fullName = "${userEntity.firstName} ${userEntity.lastName}"
        name.text = fullName
        Picasso.get().load(userEntity.avatar).into(profilePicture)
        //TODO
        acceptButton.setOnClickListener {
            onFollowRequestClickListener.onAcceptClicked(userEntity)
        }
        rejectButton.setOnClickListener {
            onFollowRequestClickListener.onRejectClicked(userEntity)
        }
    }
}
