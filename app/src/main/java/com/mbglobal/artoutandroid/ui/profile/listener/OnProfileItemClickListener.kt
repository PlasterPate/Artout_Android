package com.mbglobal.artoutandroid.ui.profile.listener

import com.mbglobal.artoutandroid.ui.profile.adapter.ProfileItem

interface OnProfileItemClickListener {

    val itemTag: String

    fun onClicked(profileItem: ProfileItem)

}