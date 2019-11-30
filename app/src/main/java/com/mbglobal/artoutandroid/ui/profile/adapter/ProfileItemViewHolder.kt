package com.mbglobal.artoutandroid.ui.profile.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.profile.listener.OnProfileItemClickListener

class ProfileItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title by lazy {
        itemView.findViewById(R.id.tv_title) as TextView
    }

    private val count by lazy {
        itemView.findViewById(R.id.tv_count) as TextView
    }

    private val icon by lazy {
        itemView.findViewById(R.id.iv_icon) as ImageView
    }

    fun bind(profileItem: ProfileItem, onProfileItemClickListener: OnProfileItemClickListener) {
        title.text = itemView.resources.getText(profileItem.titleResource)
        count.text = profileItem.count.toString()
        icon.setImageResource(profileItem.iconResource)
        itemView.setOnClickListener {
            onProfileItemClickListener.onClicked(profileItem)
        }
    }

}