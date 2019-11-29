package com.mbglobal.artoutandroid.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.profile.ProfileItem
import com.mbglobal.artoutandroid.ui.profile.listener.OnProfileItemClickListener
import com.mbglobal.data.entity.user.UserProfileEntity

class ProfileItemsAdapter(private val userProfileEntity: UserProfileEntity) : RecyclerView.Adapter<ProfileItemViewHolder>() {

    var listeners = mutableListOf<OnProfileItemClickListener>()

    private val items: List<ProfileItem> = listOf(
        ProfileItem(
            titleResource = R.string.suggestions,
            iconResource = R.drawable.ic_favorite_grey_24dp,
            count = userProfileEntity.suggestionCount,
            tag = ProfileItem.SUGGESTIONS
        ),
        ProfileItem(
            titleResource = R.string.checkins,
            iconResource = R.drawable.ic_check_ins_24dp,
            count = userProfileEntity.checkinCount,
            tag = ProfileItem.CHECKINS
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_profile_row, parent, false)
        return ProfileItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProfileItemViewHolder, position: Int) {
        holder.bind(items[position], findListenerByTag(items[position].tag))
    }

    private fun findListenerByTag(itemTag: String): OnProfileItemClickListener {
        return listeners.filter { it.itemTag == itemTag}[0]
    }
}