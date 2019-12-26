package com.mbglobal.artoutandroid.ui.discover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.users.UserState
import com.mbglobal.artoutandroid.ui.users.adapter.UserViewHolder
import com.mbglobal.data.entity.event.AddEventEntity
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.user.UserEntity

class SearchAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            USER_SEARCH_ITEM -> {
                (holder as UserSearchItemViewHolder).bind(users[position])
            }
            EVENT_SEARCH_ITEM -> {
                (holder as EventSearchItemViewHolder).bind(events[position - users.size])
            }
        }
    }

    class EventSearchItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(eventEntity: EventEntity) {

        }
    }

    class UserSearchItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(userEntity: UserEntity) {

        }
    }

    companion object {
        const val USER_SEARCH_ITEM: Int = 100
        const val EVENT_SEARCH_ITEM: Int = 200
    }
}