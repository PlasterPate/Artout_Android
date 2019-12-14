package com.mbglobal.artoutandroid.ui.eventdetails.viewholder

import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.data.entity.event.EventEntity
import com.squareup.picasso.Picasso

class CoverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val cover = itemView.findViewById<ImageView>(R.id.cover)

    fun bind(eventEntity: EventEntity) {
        try {
            Picasso.get().load(eventEntity.image?.toUri()).into(cover)
        }catch (e: SecurityException){

        }
    }
}
