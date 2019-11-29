package com.mbglobal.data.entity.user

data class UserProfileEntity(
    val followerCount: Int,
    val followingCount: Int,
    val suggestionCount: Int,
    val checkinCount: Int
)