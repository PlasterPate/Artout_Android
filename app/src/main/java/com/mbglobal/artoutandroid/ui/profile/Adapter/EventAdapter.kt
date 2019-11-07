package com.mbglobal.artoutandroid.ui.profile.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.data.entity.user.EventEntity
import com.squareup.picasso.Picasso

class EventAdapter : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    var data = listOf<EventEntity>()

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_event, parent, false)

        return EventViewHolder(view)
    }


    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val item = data[position]
        Picasso.get().load(item.images[0]).into(holder.eventImage)
        holder.eventTitle.text = item.title
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val eventImage = itemView.findViewById<ImageView>(R.id.image_item)
        val eventTitle = itemView.findViewById<TextView>(R.id.title_item)
    }
}