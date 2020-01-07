package com.mbglobal.remote.mappers

import com.mbglobal.data.UserState
import com.mbglobal.data.entity.checkin.CheckinEntity
import com.mbglobal.data.entity.event.AddEventEntity
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.event.EventSearchEntity
import com.mbglobal.data.entity.event.LocationEntity
import com.mbglobal.data.entity.user.*
import com.mbglobal.remote.dto.CheckinDto
import com.mbglobal.remote.dto.event.AddEventDto
import com.mbglobal.remote.dto.event.EventDto
import com.mbglobal.remote.dto.event.LocationDto
import com.mbglobal.remote.dto.user.*
import com.mbglobal.remote.getDateFromDateTime
import com.mbglobal.remote.getTimeFromDateTime

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
        image = pictureUrl,
        description = description,
        startDate = getDateFromDateTime(startDateTime),
        startTime = getTimeFromDateTime(startDateTime),
        endDate = getDateFromDateTime(endDateTime),
        endTime = getTimeFromDateTime(endDateTime),
        category = category,
        eventOwner = owner,
        location = location?.toLocationEntity(),
        id = id,
        owner = owner
    )
}

fun EventEntity.toAddEventDto(): AddEventDto {
    return AddEventDto(
        title = title,
        image = image,
        description = description,
        startDateTime = "$startDate $startTime",
        endDateTime = "$endDate $endTime",
        category = category,
        location = location?.toLocationDto(),
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
        startDateTime = "$startDate $startTime",
        endDateTime = "$endDate $endTime",
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

fun UserDto.toFollowRequestEntity(): FollowRequestEntity {
    return FollowRequestEntity(
        source = this.toUserEntity(),
        destination = this.toUserEntity(),
        id = this.id
    )
}

fun UserProfileEntity.toUserProfileDto(): UserProfileDto {
    return UserProfileDto(
        followerCount = followerCount.toInt(),
        followingCount = followingCount.toInt(),
        checkinCount = checkinCount.toInt(),
        suggestionCount = suggestionCount.toInt(),
        state = state,
        id = user.id,
        firstName = user.firstName,
        lastName = user.lastName,
        username = user.username,
        avatar = user.avatar
    )
}

fun UserProfileDto.toUserProfileEntity(): UserProfileEntity {
    return UserProfileEntity(
        followerCount = followerCount.toString(),
        followingCount = followingCount.toString(),
        checkinCount = checkinCount.toString(),
        suggestionCount = suggestionCount.toString(),
        state = state,
        user = UserEntity(
            id,
            avatar,
            firstName,
            lastName,
            username,
            UserState.fromInt(state)
        )
    )
}

fun EventSearchEntity.toQueryMap(): Map<String, String> {
    val query = HashMap<String, String>()
    query["title__icontains"] = this.title
    this.category?.let {
        query["category"] = it
    }
    return query
}

fun CheckinDto.toCheckinEntity(): CheckinEntity {
    return CheckinEntity(
        userEntity = userEntity,
        eventEntity = eventEntity
    )
}

fun CheckinEntity.toCheckinDto(): CheckinDto {
    return CheckinDto(
        userEntity = userEntity,
        eventEntity = eventEntity,
        goTime = "",
        submittedTime = ""
    )
}