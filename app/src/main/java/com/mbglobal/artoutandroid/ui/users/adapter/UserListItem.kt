package com.mbglobal.artoutandroid.ui.users.adapter

import com.mbglobal.data.UserState
import com.mbglobal.data.entity.user.UserEntity

data class UserListItem(
    val userEntity: UserEntity,
    val state: UserState
)