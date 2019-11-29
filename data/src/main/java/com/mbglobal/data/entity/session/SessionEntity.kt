package com.mbglobal.data.entity.session

data class SessionEntity(
    val access: String,
    val refresh: String,
    val userId: String
)