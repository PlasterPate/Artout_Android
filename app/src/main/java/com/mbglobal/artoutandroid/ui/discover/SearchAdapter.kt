package com.mbglobal.artoutandroid.ui.discover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.eventlist.adapter.OnEventItemClickListener
import com.mbglobal.artoutandroid.ui.users.adapter.OnUserItemClickListener
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.user.UserEntity
import com.squareup.picasso.Picasso

class SearchAdapter(
    private val onEventItemClickListener: OnEventItemClickListener,
    private val onUserItemClickListener: OnUserItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var users = listOf<UserEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var events = listOf<EventEntity>()
        set(value) {
            field = value
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflate : (Int) -> View = {
            LayoutInflater.from(parent.context).inflate(it, parent, false)
        }

        return when (viewType) {
            USER_SEARCH_ITEM -> UserSearchItemViewHolder(inflate(R.layout.item_search_user))
            EVENT_SEARCH_ITEM -> EventSearchItemViewHolder(inflate(R.layout.item_search_event))
            else -> throw Exception("Invalid User Item")
        }
    }

    override fun getItemCount(): Int {
        return users.size + events.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            USER_SEARCH_ITEM -> {
                (holder as UserSearchItemViewHolder).bind(users[position], onUserItemClickListener)
            }
            EVENT_SEARCH_ITEM -> {
                (holder as EventSearchItemViewHolder).bind(events[position - users.size], onEventItemClickListener)
            }
        }
    }

    class EventSearchItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val eventTitle: TextView by lazy {
            itemView.findViewById<TextView>(R.id.tv_event_title)
        }

        private val eventIcon: ImageView by lazy {
            itemView.findViewById<ImageView>(R.id.iv_event_icon)
        }

        fun bind(
            eventEntity: EventEntity,
            onEventItemClickListener: OnEventItemClickListener
        ) {
            eventTitle.text = eventEntity.title
            //Picasso.get().load(eventEntity.image).into(eventIcon)
            itemView.setOnClickListener {
                onEventItemClickListener.onClicked(eventEntity)
            }
        }
    }

    class UserSearchItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val fullName: TextView by lazy {
            itemView.findViewById<TextView>(R.id.tv_full_name)
        }

        private val userIcon: ImageView by lazy {
            itemView.findViewById<ImageView>(R.id.iv_profile_icon)
        }

        fun bind(
            userEntity: UserEntity,
            onUserItemClickListener: OnUserItemClickListener
        ) {
            fullName.text = userEntity.firstName + " " + userEntity.lastName
            itemView.setOnClickListener {
                onUserItemClickListener.onClicked(userEntity)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            in users.indices -> USER_SEARCH_ITEM
            else -> EVENT_SEARCH_ITEM
        }
    }

    companion object {
        const val USER_SEARCH_ITEM: Int = 100
        const val EVENT_SEARCH_ITEM: Int = 200
    }
}