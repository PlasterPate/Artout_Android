package com.mbglobal.remote.mappers

import com.mbglobal.data.UserState
import com.mbglobal.data.entity.event.AddEventEntity
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.event.LocationEntity
import com.mbglobal.data.entity.user.*
import com.mbglobal.remote.dto.event.AddEventDto
import com.mbglobal.remote.dto.event.EventDto
import com.mbglobal.remote.dto.event.LocationDto
import com.mbglobal.remote.dto.user.*
import kotlin.math.absoluteValue

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

fun EventDto.toEventEntity(): EventEntity {
    return EventEntity(
        title = title,
        image = image,
        description = description,
        startDate = startDate,
        endDate = endDate,
        category = category,
        eventOwner = owner,
        location = location.toLocationEntity(),
        id = id,
        owner = owner
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
        location = location.toLocationDto(),
        owner = owner
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
        location = location.toLocationDto(),
        owner = owner
    )
}

fun UserEntity.toUserDto(): UserDto {
    return UserDto(
        avatar = avatar,
        firstName = firstName,
        lastName = lastName,
        username = username,
        id = id,
        state = state.value
    )
}

fun UserDto.toUserEntity(): UserEntity {
    return UserEntity(
        avatar = avatar,
        firstName = firstName,
        lastName = lastName,
        username = username,
        id = id,
        state = UserState.fromInt(state)
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

fun UserDto.toFollowRequestEntity(): FollowRequestEntity{
    return FollowRequestEntity(
        source = this.toUserEntity(),
        destination = this.toUserEntity(),
        id = this.id
    )
}

fun UserProfileEntity.toUserProfileDto(): UserProfileDto{
    return UserProfileDto(
        followerCount = followerCount,
        followingCount = followingCount,
        checkinCount = checkinCount,
        suggestionCount = suggestionCount,
        state = state,
        user = user
    )
}

fun UserProfileDto.toUserProfileEntity(): UserProfileEntity{
    return UserProfileEntity(
        followerCount = followerCount,
        followingCount = followingCount,
        checkinCount = checkinCount,
        suggestionCount = suggestionCount,
        state = state,
        user = user
    )
}