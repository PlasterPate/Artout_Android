package com.mbglobal.data.entity.user

import com.mbglobal.data.UserState

data class UserEntity(
    val id: Int,
    val avatar: String,
    val firstName: String,
    val lastName: String,
    val username: String,
    val state: UserState
)