package com.mbglobal.artoutandroid.ui.users

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
import timber.log.Timber
import javax.inject.Inject

class SocialViewModel @Inject constructor(
    private val socialRepository: SocialRepository,
    private val sessionLocalDataSource: SessionLocalDataSource
) : BaseViewModel() {

    private val _followers: MutableLiveData<List<UserEntity>> = MutableLiveData()
    val followers: LiveData<List<UserEntity>> = _followers

    private val _followRequests: MutableLiveData<List<FollowRequestEntity>> = MutableLiveData()
    val followRequests = _followRequests

    private val _followings: MutableLiveData<List<UserEntity>> = MutableLiveData()
    val followings: LiveData<List<UserEntity>> = _followings

    fun loadFollowers(userId: String?) {
        socialRepository.getUserFollowers(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ followers: List<UserEntity> ->
                _followers.postValue(followers)
            }, {
                Timber.e("Throwable followers ${it.message}")
            }).also {
                compositeDisposable.add(it)
            }
    }

    fun loadFollowings(userId: String?) {
        socialRepository.getUserFollowings(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ followers: List<UserEntity> ->
                _followings.postValue(followers)
            }, {

            }).also {
                compositeDisposable.add(it)
            }
    }

    fun loadPendingFollowRequests() {
        socialRepository.getFollowRequests()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ followRequestEntities: List<FollowRequestEntity> ->
                println(followRequestEntities)
                _followRequests.postValue(followRequestEntities)
            }, {
                Timber.e("Throwable pending ${it.message}")
            }).also {
                compositeDisposable.add(it)
            }
    }

    fun acceptRequest(requester: UserEntity) {

        socialRepository.acceptRequest(requester.id.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .retry(5)
            .subscribe({}, {}).also {
                compositeDisposable.add(it)
            }

    }

    fun rejectRequest(requester: UserEntity) {

        socialRepository.rejectRequest(requester.id.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .retry(5)
            .subscribe({}, {}).also {
                compositeDisposable.add(it)
            }

    }

    fun followUser(userEntity: UserEntity) {

        socialRepository.follow(userEntity.id.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .retry(5)
            .subscribe({}, {}).also {
                compositeDisposable.add(it)
            }

    }

    fun unfollowUser(userEntity: UserEntity) {

        socialRepository.unfollow(userEntity.id.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .retry(5)
            .subscribe({}, {}).also {
                compositeDisposable.add(it)
            }

    }
}