package com.mbglobal.data.repository

import com.mbglobal.data.UserState
import com.mbglobal.data.entity.user.FollowRequestEntity
import com.mbglobal.data.entity.user.UserEntity

object MockUserFactory {

    fun getFollowers(): List<UserEntity> {
        return listOf(SAULEH.copy(id = 1), SAULEH.copy(id = 2), SAULEH.copy(id = 3), SAULEH.copy(id = 4))
    }

    fun getFollowings(): List<UserEntity> {
        return mutableListOf(MOVAHED, MAMAD, SARAH, MOBIN)
    }

    fun getFollowRequests(): List<FollowRequestEntity> {
        return getFollowings().zip(getFollowers()) { a, b ->
            FollowRequestEntity(a, b, 1)
        }
    }

    val SAULEH = UserEntity(
        12,
        "https://pbs.twimg.com/profile_images/959929674355765248/fk3ALoeH.jpg",
        "Sauleh",
        "Eeetemadi",
        "sauleh1",
        state = UserState.OWNER
    )

    val POOYA = UserEntity(
        12,
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/colorful-of-dahlia-pink-flower-in-beautiful-garden-royalty-free-image-825886130-1554743243.jpg",
        "Pooya",
        "Kabiri",
        "sauleh1",
        state = UserState.OWNER
    )

    val MOVAHED = UserEntity(
        12,
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/colorful-of-dahlia-pink-flower-in-beautiful-garden-royalty-free-image-825886130-1554743243.jpg",
        "Alimohammad",
        "Movahedian",
        "sauleh1",
        state = UserState.OWNER
    )

    val MAMAD = UserEntity(
        12,
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/colorful-of-dahlia-pink-flower-in-beautiful-garden-royalty-free-image-825886130-1554743243.jpg",
        "Mamad",
        "YN",
        "sauleh1",
        state = UserState.OWNER
    )

    val ALIREZA = UserEntity(
        12,
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/colorful-of-dahlia-pink-flower-in-beautiful-garden-royalty-free-image-825886130-1554743243.jpg",
        "Alireza",
        "Moradi",
        "sauleh1",
        state = UserState.OWNER
    )

    val SARAH = UserEntity(
        12,
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/colorful-of-dahlia-pink-flower-in-beautiful-garden-royalty-free-image-825886130-1554743243.jpg",
        "Sarah",
        "Codeiry",
        "sauleh1",
        state = UserState.OWNER
    )

    val MOBIN = UserEntity(
        12,
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/colorful-of-dahlia-pink-flower-in-beautiful-garden-royalty-free-image-825886130-1554743243.jpg",
        "Mobin",
        "Dariush",
        "sauleh1",
        state = UserState.OWNER
    )
}