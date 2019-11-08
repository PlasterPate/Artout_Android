package com.mbglobal.artoutandroid.ui.eventdetails.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.data.entity.user.EventEntity
import com.squareup.picasso.Picasso

class CoverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val cover = itemView.findViewById<ImageView>(R.id.cover)

    fun bind(eventEntity: EventEntity) {
        Picasso.get().load(eventEntity.image).into(cover)
    }
}
