package com.mbglobal.data.entity.user

data class UserProfileEntity(
    val followerCount: String,
    val followingCount: String,
    val suggestionCount: String,
    val checkinCount: String,
    val state: Int,
    val user: UserEntity
)