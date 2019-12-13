package com.mbglobal.artoutandroid.ui.users.adapter.listener

import com.mbglobal.artoutandroid.ui.users.UserState
import com.mbglobal.data.entity.user.UserEntity

interface OnUserItemClickListener {

    val stateTag: UserState

    fun onClicked(userEntity: UserEntity)

}