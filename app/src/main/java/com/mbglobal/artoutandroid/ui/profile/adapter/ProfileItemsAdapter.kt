package com.mbglobal.artoutandroid.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.profile.listener.OnProfileItemClickListener
import com.mbglobal.data.entity.user.UserProfileEntity

class ProfileItemsAdapter : RecyclerView.Adapter<ProfileItemViewHolder>() {

    var listeners = mutableListOf<OnProfileItemClickListener>()

    var items = listOf<ProfileItem>()



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