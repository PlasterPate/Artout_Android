package com.mbglobal.artoutandroid.ui.profile.adapter

data class ProfileItem(
    val titleResource: Int,
    val iconResource: Int,
    val count: Int = 0,
    val tag: String
) {

    companion object {
        const val SUGGESTIONS = "SUGGESTIONS"
        const val CHECKINS = "CHECKINS"
    }
}