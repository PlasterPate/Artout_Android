package com.mbglobal.remote.mappers

import com.mbglobal.data.entity.user.*
import com.mbglobal.remote.dto.user.*

fun UserLoginItemEntity.toUserLoginItemDto() : UserLoginItemDto {
    return UserLoginItemDto(
        username = username,
        password = password
    )
}

fun UserRegisterItemEntity.toUserRegisterItemDto() : UserRegisterItemDto {
    return UserRegisterItemDto(
        avatar = avatar,
        password = password,
        email = email,
        firstName = firstName,
        lastName = lastName,
        username = username
    )
}

fun UserLoginResponseDto.toUserLoginResponseEntity() : UserLoginResponseEntity {
    return UserLoginResponseEntity(
        access = access,
        id = id,
        refresh = refresh
    )
}

fun UserRegisterResponseDto.toUserRegisterResponseEntity() : UserRegisterResponseEntity {
    return UserRegisterResponseEntity(
        id = id
    )
}

fun UserResponseDto.toUserEntity() : UserEntity {
    return UserEntity(
        firstName = firstName,
        lastName = lastName,
        avatar = avatar,
        username = username,
        id = id
    )
}
