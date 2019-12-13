package com.mbglobal.artoutandroid.ui.users.adapter.listener

import com.mbglobal.data.entity.user.UserEntity

interface OnFollowRequestClickListener {

    fun onAcceptClicked(userEntity: UserEntity)

    fun onRejectClicked(userEntity: UserEntity)

}