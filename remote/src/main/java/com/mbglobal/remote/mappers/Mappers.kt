package com.mbglobal.remote.mappers

import com.mbglobal.data.entity.event.AddEventEntity
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.event.LocationEntity
import com.mbglobal.data.entity.user.*
import com.mbglobal.remote.dto.event.AddEventDto
import com.mbglobal.remote.dto.event.EventDto
import com.mbglobal.remote.dto.event.LocationDto
import com.mbglobal.remote.dto.user.*

fun UserLoginItemEntity.toUserLoginItemDto(): UserLoginItemDto {
    return UserLoginItemDto(
        username = username,
        password = password
    )
}

fun UserRegisterItemEntity.toUserRegisterItemDto(): UserRegisterItemDto {
    return UserRegisterItemDto(
        avatar = avatar,
        password = password,
        email = email,
        firstName = firstName,
        lastName = lastName,
        username = username
    )
}

fun UserLoginResponseDto.toUserLoginResponseEntity(): UserLoginResponseEntity {
    return UserLoginResponseEntity(
        access = access,
        id = id,
        refresh = refresh
    )
}

fun UserRegisterResponseDto.toUserRegisterResponseEntity(): UserRegisterResponseEntity {
    return UserRegisterResponseEntity(
        id = id
    )
}

fun UserResponseDto.toUserEntity(): UserEntity {
    return UserEntity(
        firstName = firstName,
        lastName = lastName,
        avatar = avatar,
        username = username,
        id = id
    )
}

fun EventDto.toEventEntity(): EventEntity {
    return EventEntity(
        title = title,
        image = image,
        description = description,
        startDate = startDate,
        endDate = endDate,
        category = category,
        eventOwner = eventOwner,
        location = location.toLocationEntity(),
        id = id
    )
}

fun EventEntity.toAddEventDto(): AddEventDto {
    return AddEventDto(
        title = title,
        image = image,
        description = description,
        startDate = startDate,
        endDate = endDate,
        category = category,
        eventOwner = eventOwner,
        location = location.toLocationDto()
    )
}

fun LocationDto.toLocationEntity(): LocationEntity {
    return LocationEntity(
        longitude = longitude,
        latitude = latitude
    )
}

fun LocationEntity.toLocationDto(): LocationDto {
    return LocationDto(
        latitude = latitude,
        longitude = longitude
    )
}

fun AddEventEntity.toAddEventDto(): AddEventDto {
    return AddEventDto(
        title = title,
        image = image,
        description = description,
        startDate = startDate,
        endDate = endDate,
        category = category,
        eventOwner = eventOwner,
        location = location.toLocationDto()
    )
}

fun UserEntity.toUserDto(): UserDto {
    return UserDto(
        avatar = avatar,
        firstName = firstName,
        lastName = lastName,
        username = username,
        id = id
    )
}

fun UserDto.toUserEntity(): UserEntity {
    return UserEntity(
        avatar = avatar,
        firstName = firstName,
        lastName = lastName,
        username = username,
        id = id
    )
}

fun FollowRequestEntity.toFollowRequestDto(): FollowRequestDto {
    return FollowRequestDto(
        source = source,
        destination = destination,
        id = id
    )
}

fun FollowRequestDto.toFollowRequsetEntity(): FollowRequestEntity {
    return FollowRequestEntity(
        source = source,
        destination = destination,
        id = id
    )
}

fun FollowItemEntity.toFollowItemDto(): FollowItemDto{
    return FollowItemDto(
        id = id,
        firstName = firstName,
        lastName = lastName,
        username = username,
        avatar = avatar
    )
}

fun FollowItemDto.toFollowItemEntity(): FollowItemEntity{
    return FollowItemEntity(
        id = id,
        firstName = firstName,
        lastName = lastName,
        username = username,
        avatar = avatar
    )
}
