package com.mbglobal.data

enum class UserState(val value: Int) {
    OWNER(0),
    FOLLOWING(1),
    REQUESTED(2),
    NOT_FOLLOWING(3);

    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
    }
}