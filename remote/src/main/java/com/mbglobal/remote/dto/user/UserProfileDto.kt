package com.mbglobal.remote.dto.user

import com.google.gson.annotations.SerializedName
import com.mbglobal.data.entity.user.UserEntity

data class UserProfileDto (
    @SerializedName("follower_count")
    val followerCount: Int,
    @SerializedName("following_count")
    val followingCount: Int,
    @SerializedName("suggestion_count")
    val suggestionCount: Int,
    @SerializedName("checkin_count")
    val checkinCount: Int,
    @SerializedName("state")
    val state: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("avatar")
    val avatar: String


)