package com.mbglobal.artoutandroid.ui.users.followers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.datasource.SessionLocalDataSource
import com.mbglobal.data.entity.user.FollowRequestEntity
import com.mbglobal.data.entity.user.UserEntity
import com.mbglobal.data.repository.SocialRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FollowersViewModel @Inject constructor(
    private val socialRepository: SocialRepository,
    private val sessionLocalDataSource: SessionLocalDataSource
) : BaseViewModel() {

    private val _followers: MutableLiveData<List<UserEntity>> = MutableLiveData()
    val followers: LiveData<List<UserEntity>> = _followers

    private val _followRequests: MutableLiveData<List<FollowRequestEntity>> = MutableLiveData()
    val followRequests = _followRequests

    fun loadFollowers(userId: String?) {
        val id = userId?.let {
            Single.just(it)
        } ?: sessionLocalDataSource.getSession()
            .map {
                it.userId
            }

        id.flatMap {
            socialRepository.getUserFollowers(it)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { followers: List<UserEntity> ->
                _followers.postValue(followers)
            }.also {
                compositeDisposable.add(it)
            }
    }

    fun loadPendingFollowRequests() {
        socialRepository.getFollowRequests()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { followRequestEntities: List<FollowRequestEntity> ->
                _followRequests.postValue(followRequestEntities)
            }.also {
                compositeDisposable.add(it)
            }
    }

}