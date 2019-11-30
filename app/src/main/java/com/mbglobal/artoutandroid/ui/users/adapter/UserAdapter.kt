package com.mbglobal.artoutandroid.ui.users.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.users.UserState
import com.mbglobal.artoutandroid.ui.users.adapter.listener.OnUserItemClickListener
import com.mbglobal.artoutandroid.ui.users.adapter.listener.OnFollowRequestClickListener

class UserAdapter: RecyclerView.Adapter<UserViewHolder>() {

    var listeners = mutableListOf<OnUserItemClickListener>()

    var onFollowRequestClickListener: OnFollowRequestClickListener? = null

    var data = listOf<UserListItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val inflate : (Int) -> View = {
            LayoutInflater.from(parent.context).inflate(it, parent, false)
        }

        return when (viewType) {
            UserState.FOLLOWING.value,
            UserState.NOT_FOLLOWING.value,
            UserState.REQUEST_SENT.value -> UserViewHolder(inflate(R.layout.item_user))
            else -> throw Exception("Invalid User Item")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].state.value
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(data[position], findListenerByCurrentState(data[position].state))
    }

    private fun findListenerByCurrentState(userState: UserState): OnUserItemClickListener {
        return listeners.filter { it.stateTag == userState}[0]
    }
}