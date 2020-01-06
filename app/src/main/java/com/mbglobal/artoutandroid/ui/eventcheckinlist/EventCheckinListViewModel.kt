package com.mbglobal.artoutandroid.ui.eventcheckinlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.UserState
import com.mbglobal.data.entity.user.UserEntity
import com.mbglobal.data.repository.UserRepository
import javax.inject.Inject

class EventCheckinListViewModel @Inject constructor(private val userRepository: UserRepository): BaseViewModel() {

    private var _users = MutableLiveData<List<UserEntity>>()
    var users: LiveData<List<UserEntity>> = _users

    fun loadCheckedInUsers(){
        _users.value = listOf(
            UserEntity(4, "", "Ali", "Movahed", "amma", UserState.NOT_FOLLOWING),
            UserEntity(12, "", "Pooya", "Kiri", "pkpkpk", UserState.FOLLOWING),
            UserEntity(4, "", "Nibom", "Chaghal", "dalidali", UserState.REQUESTED)
        )
    }
}