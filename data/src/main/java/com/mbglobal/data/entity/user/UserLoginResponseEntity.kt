package com.mbglobal.data.entity.user

data class UserLoginResponseEntity(
    val access: String,
    val refresh: String,
    val id: String
)