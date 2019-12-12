package com.mbglobal.data.repository

import com.mbglobal.data.entity.user.FollowRequestEntity
import com.mbglobal.data.entity.user.UserEntity

object MockUserFactory {

    fun getFollowers(): List<UserEntity> {
        return listOf(SAULEH.withId(1), SAULEH.withId(2), SAULEH.withId(3), SAULEH.withId(4))
    }

    fun getFollowings(): List<UserEntity> {
        return mutableListOf(MOVAHED, MAMAD, SARAH, MOBIN)
    }

    fun getFollowRequests(): List<FollowRequestEntity> {
        return getFollowings().zip(getFollowers()) { a, b ->
            FollowRequestEntity(a, b, 1)
        }
    }

    fun UserEntity.withId(id: Int): UserEntity {
        return UserEntity(
            this.avatar,
            this.firstName,
            id,
            this.lastName,
            this.username
        )
    }
    val SAULEH = UserEntity(
        "https://pbs.twimg.com/profile_images/959929674355765248/fk3ALoeH.jpg",
        "Sauleh",
        12,
        "Eeetemadi",
        "sauleh1"
    )

    val POOYA = UserEntity(
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/colorful-of-dahlia-pink-flower-in-beautiful-garden-royalty-free-image-825886130-1554743243.jpg",
        "Pooya",
        12,
        "Kabiri",
        "sauleh1"
    )

    val MOVAHED = UserEntity(
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/colorful-of-dahlia-pink-flower-in-beautiful-garden-royalty-free-image-825886130-1554743243.jpg",
        "Alimohammad",
        12,
        "Movahedian",
        "sauleh1"
    )

    val MAMAD = UserEntity(
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/colorful-of-dahlia-pink-flower-in-beautiful-garden-royalty-free-image-825886130-1554743243.jpg",
        "Mamad",
        12,
        "YN",
        "sauleh1"
    )

    val ALIREZA = UserEntity(
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/colorful-of-dahlia-pink-flower-in-beautiful-garden-royalty-free-image-825886130-1554743243.jpg",
        "Alireza",
        12,
        "Moradi",
        "sauleh1"
    )

    val SARAH = UserEntity(
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/colorful-of-dahlia-pink-flower-in-beautiful-garden-royalty-free-image-825886130-1554743243.jpg",
        "Sarah",
        12,
        "Codeiry",
        "sauleh1"
    )

    val MOBIN = UserEntity(
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/colorful-of-dahlia-pink-flower-in-beautiful-garden-royalty-free-image-825886130-1554743243.jpg",
        "Mobin",
        12,
        "Dariush",
        "sauleh1"
    )
}