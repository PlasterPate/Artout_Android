package com.mbglobal.artoutandroid.ui.users.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.users.adapter.listener.OnActionButtonClickListener
import com.mbglobal.artoutandroid.ui.users.adapter.listener.OnFollowRequestClickListener
import com.mbglobal.data.UserState
import com.mbglobal.data.entity.user.UserEntity

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {

    var actionButtonListeners= mutableListOf<OnActionButtonClickListener>()
    var onUserItemClickListener: OnUserItemClickListener? = null

    var data: MutableList<UserEntity> = object : ArrayList<UserEntity>() {

        init {
            notifyDataSetChanged()
        }

        override fun add(element: UserEntity): Boolean {
            val result = super.add(element)
            notifyDataSetChanged()
            return result
        }

        override fun set(index: Int, element: UserEntity): UserEntity {
            val result = super.set(index, element)
            notifyItemChanged(index)
            return result
        }

        override fun add(index: Int, element: UserEntity) {
            super.add(index, element)
            notifyItemInserted(index)
        }
    }
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val inflate: (Int) -> View = {
            LayoutInflater.from(parent.context).inflate(it, parent, false)
        }

        return when (viewType) {
            UserState.FOLLOWING.value,
            UserState.NOT_FOLLOWING.value,
            UserState.REQUESTED.value -> UserViewHolder(inflate(R.layout.item_user))
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
        holder.bind(data[position], onUserItemClickListener!!, findListenerByCurrentState(data[position].state))
    }

    fun updateUserState(userEntity: UserEntity, state: UserState) {
        data.forEachIndexed { index, it ->
            if (it == userEntity) {
                data[index] = userEntity.copy(state = state)
                notifyItemChanged(index)
            }
        }
    }

    private fun findListenerByCurrentState(userState: UserState): OnActionButtonClickListener {
        return actionButtonListeners.filter { it.stateTag == userState }[0]
    }
}