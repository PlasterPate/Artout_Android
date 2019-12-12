package com.mbglobal.artoutandroid.ui.users.followings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.datasource.SessionLocalDataSource
import com.mbglobal.data.entity.user.UserEntity
import com.mbglobal.data.repository.SocialRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FollowingsViewModel @Inject constructor(
    private val socialRepository: SocialRepository,
    private val sessionLocalDataSource: SessionLocalDataSource
) : BaseViewModel() {

    private val _followings: MutableLiveData<List<UserEntity>> = MutableLiveData()
    val followings: LiveData<List<UserEntity>> = _followings

    fun loadFollowings(userId: String?) {
        val id = userId?.let {
            Single.just(it)
        } ?: sessionLocalDataSource.getSession()
            .map {
                it.userId
            }

        id.flatMap {
            socialRepository.getUserFollowings(it)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { followers: List<UserEntity> ->
                _followings.postValue(followers)
            }.also {
                compositeDisposable.add(it)
            }
    }

}