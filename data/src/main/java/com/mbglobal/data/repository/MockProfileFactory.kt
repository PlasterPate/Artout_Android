package com.mbglobal.data.repository

import com.mbglobal.data.entity.user.UserProfileEntity
import io.reactivex.Single

object MockProfileFactory {
    val profile = UserProfileEntity(
        "3",
        "4",
        "23",
        "41",
        2,
        MockUserFactory.MOVAHED
    )

    fun getProfile() : Single<UserProfileEntity>{
        return Single.fromCallable { profile}
    }
}