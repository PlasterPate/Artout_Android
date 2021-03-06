package com.mbglobal.remote.mappers

import com.mbglobal.data.UserState
import com.mbglobal.data.entity.checkin.CheckinEntity
import com.mbglobal.data.entity.event.AddEventEntity
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.event.EventSearchEntity
import com.mbglobal.data.entity.event.LocationEntity
import com.mbglobal.data.entity.event.*
import com.mbglobal.data.entity.user.*
import com.mbglobal.remote.dto.checkin.AddCheckinDto
import com.mbglobal.remote.dto.checkin.CheckinDto
import com.mbglobal.remote.dto.event.AddEventDto
import com.mbglobal.remote.dto.event.EventDto
import com.mbglobal.remote.dto.event.LocationDto
import com.mbglobal.remote.dto.event.*
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
        image = imageList.random(),
        description = description,
        startDate = getDateFromDateTime(startDateTime),
        startTime = getTimeFromDateTime(startDateTime),
        endDate = getDateFromDateTime(endDateTime),
        endTime = getTimeFromDateTime(endDateTime),
        category = category,
        eventOwner = owner,
        location = location?.toLocationEntity(),
        id = id,
        owner = owner,
        checkinState = isCheckedIn ?: false,
        checkinCount = checkinCount ?: 0
    )
}

fun EventEntity.toEventDto(): EventDto{
    return EventDto(
        id = id,
        title = title,
        pictureUrl = image!!,
        description = description,
        startDateTime = "$startDate $startTime",
        endDateTime = "$endDate $endTime",
        category = category,
        location = location?.toLocationDto(),
        owner = owner,
        isCheckedIn = checkinState,
        checkinCount = checkinCount
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
        owner = owner,
        picture_exists = image!!.isNotEmpty()
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
        owner = owner,
        picture_exists = image!!.isNotEmpty()
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
        userEntity = userEntity.toUserEntity(),
        eventEntity = eventEntity.toEventEntity()
    )
}

fun CheckinEntity.toCheckinDto(): CheckinDto {
    return CheckinDto(
        userEntity = userEntity.toUserDto(),
        eventEntity = eventEntity.toEventDto(),
        goTime = "",
        submittedTime = ""
    )
}

fun EventEntity.toAddCheckinDto(): AddCheckinDto{
    return AddCheckinDto(
        eventId = id.toString()
    )
}

fun AddEventResponseDto.toAddEventResponseEntity(): AddEventResponseEntity{
    return AddEventResponseEntity(
        event = EventEntity(
            id = id,
            endTime = endDateTime,
            startTime = startDateTime,
            owner = owner,
            title = title,
            category = category,
            checkinCount = 0,
            checkinState = false,
            image = "https://vignette.wikia.nocookie.net/friends/images/9/94/Central_Perk.jpg",
            location = location?.toLocationEntity(),
            description = description,
            endDate = endDateTime,
            startDate = startDateTime,
            eventOwner = owner),
        s3ResponseEntity = s3ResponseDto.toS3ResponseEntity()
    )
}

fun S3ResponseDto.toS3ResponseEntity(): S3ResponseEntity{
    return S3ResponseEntity(
        url = url,
        key = s3FieldsDto.key,
        awsAccessKeyId = s3FieldsDto.awsAccessKeyId,
        policy = s3FieldsDto.policy,
        signature = s3FieldsDto.signature
    )
}

fun S3ResponseEntity.toS3ResponseDto(): S3ResponseDto{
    return S3ResponseDto(
        url = url,
        s3FieldsDto = S3FieldsDto(
            key = key,
            policy = policy,
            awsAccessKeyId = awsAccessKeyId,
            signature = signature)
    )
}

val imageList = listOf<String>("https://upgradedpoints.com/wp-content/uploads/2018/06/Top-20-Amusement-Parks-in-North-America.jpg",
    "https://vignette.wikia.nocookie.net/friends/images/9/94/Central_Perk.jpg",
    "https://www.nickselway.com/images/xl/BEST.jpg",
    "https://cdn.getyourguide.com/img/tour_img-2420980-146.jpg",
    "https://afremov.com/images/product/image_2347.jpeg",
    "https://www.carredartistes.com/img/cms/Blog/2019-04-Street%20art/intro-article%20(1).jpg",
    "https://static.spin.com/files/2015/10/1501005-rock-band-640x426.jpg")

