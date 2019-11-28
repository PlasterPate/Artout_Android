package com.mbglobal.data.mapper

import com.mbglobal.data.entity.user.UserLoginResponseEntity
import com.mbglobal.data.entity.user.UserRegisterResponseEntity

fun UserLoginResponseEntity.toUserRegisterResponseEntity(): UserRegisterResponseEntity? {
    return UserRegisterResponseEntity(
        id = id
    )
}