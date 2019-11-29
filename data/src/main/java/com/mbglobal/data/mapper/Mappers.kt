package com.mbglobal.data.mapper

import com.mbglobal.data.entity.session.SessionEntity
import com.mbglobal.data.entity.user.UserLoginItemEntity
import com.mbglobal.data.entity.user.UserLoginResponseEntity
import com.mbglobal.data.entity.user.UserRegisterItemEntity
import com.mbglobal.data.entity.user.UserRegisterResponseEntity

fun UserLoginResponseEntity.toUserRegisterResponseEntity(): UserRegisterResponseEntity? {
    return UserRegisterResponseEntity(
        id = id
    )
}

fun UserLoginResponseEntity.toSessionEntity(): SessionEntity {
    return SessionEntity(
        access = access,
        refresh = refresh,
        userId = id
    )
}

fun UserRegisterItemEntity.toUserLoginItemEntity(): UserLoginItemEntity {
    return UserLoginItemEntity(
        username = username,
        password = password
    )
}