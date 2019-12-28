package com.mbglobal.artoutandroid.ui.users.adapter

import com.mbglobal.data.entity.user.UserEntity

interface OnUserItemClickListener {

    fun onClicked(userEntity: UserEntity)
}
