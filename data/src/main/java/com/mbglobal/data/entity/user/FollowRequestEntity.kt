package com.mbglobal.data.entity.user

data class FollowRequestEntity(
    val source: UserEntity,
    val destination: UserEntity,
    val id: Int
    )