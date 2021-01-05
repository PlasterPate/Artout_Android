package com.mbglobal.data.repository

import com.mbglobal.data.UserState
import com.mbglobal.data.entity.user.FollowRequestEntity
import com.mbglobal.data.entity.user.UserEntity
import io.reactivex.Single

object MockUserFactory {

    val SAULEH_AVATAR = "https://pbs.twimg.com/profile_images/959929674355765248/fk3ALoeH.jpg"
    val MOVAHED_AVATAR = "https://avatars1.githubusercontent.com/u/34604456?s=400&v=4"
    val POOYA_AVATAR = "https://steamuserimages-a.akamaihd.net/ugc/155776388735849447/740ABEFBF4677B98FD99BB072FED9780C673510D/"
    val MORTY_AVATAR = "https://p.favim.com/orig/2019/02/01/profile-picture-morty-tv-show-Favim.com-6858053.jpg"
    val TODD_AVATAR = "https://avatars1.githubusercontent.com/u/59721339?s=460&u=c2ff578b2e543a4f6a85077ad763ae79da33002e&v=4"
    val EDNA_AVATAR = "https://i.pinimg.com/originals/dc/4c/61/dc4c617aa4e65b0a65c951e18f26beaf.jpg"
    val SHELDON_AVATAR = "https://www.aviationanalysis.net/wp-content/uploads/2020/09/Jim-Parsons-from-The-Big-Bang-Theory-reveals-he-has.jpeg"

    val SAULEH = UserEntity(
        1,
        SAULEH_AVATAR,
        "Sauleh",
        "Etemadi",
        "sauleh1",
        state = UserState.FOLLOWING
    )

    val POOYA = UserEntity(
        2,
        POOYA_AVATAR,
        "Pooya",
        "Kabiri",
        "sauleh1",
        state = UserState.FOLLOWING
    )

    val MOVAHED = UserEntity(
        3,
        MOVAHED_AVATAR,
        "Alimohammad",
        "Movahedian",
        "sauleh1",
        state = UserState.FOLLOWING
    )

    val MAMAD = UserEntity(
        4,
        TODD_AVATAR,
        "Mamad",
        "YN",
        "sauleh1",
        state = UserState.FOLLOWING
    )

    val ALIREZA = UserEntity(
        5,
        MORTY_AVATAR,
        "Alireza",
        "Moradi",
        "sauleh1",
        state = UserState.NOT_FOLLOWING
    )

    val SARAH = UserEntity(
        6,
        EDNA_AVATAR,
        "Sarah",
        "Codeiry",
        "sauleh1",
        state = UserState.NOT_FOLLOWING
    )

    val MOBIN = UserEntity(
        7,
        SHELDON_AVATAR,
        "Mobin",
        "Dariush",
        "sauleh1",
        state = UserState.FOLLOWING
    )

    val FOLLOWERS = listOf(MOBIN, SARAH, ALIREZA)

    val FOLLOWINGS = mutableListOf(MAMAD, POOYA, SAULEH, MOBIN)

    val FOLLOW_REQUESTS = FOLLOWERS.zip(FOLLOWINGS) { a, b ->
        FollowRequestEntity(b, a, 1)
    }

    val FOLLOW_PENDINGS = FOLLOWERS.zip(FOLLOWINGS) { a, b ->
        FollowRequestEntity(a, b, 1)
    }

    fun getFollowers(): Single<List<UserEntity>> {
        return Single.fromCallable{ FOLLOWERS}
    }

    fun getFollowings(): Single<List<UserEntity>> {
        return Single.fromCallable{ FOLLOWINGS}
    }

    fun getFollowRequests(): Single<List<FollowRequestEntity>> {
        return Single.fromCallable{ FOLLOW_REQUESTS}
    }

    fun getFollowPendings(): Single<List<FollowRequestEntity>> {
        return Single.fromCallable{ FOLLOW_PENDINGS}
    }


}