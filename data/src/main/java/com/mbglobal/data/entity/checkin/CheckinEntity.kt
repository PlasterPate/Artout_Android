package com.mbglobal.data.entity.checkin

import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.user.UserEntity

data class CheckinEntity(
    val userEntity: UserEntity,
    val eventEntity: EventEntity
)