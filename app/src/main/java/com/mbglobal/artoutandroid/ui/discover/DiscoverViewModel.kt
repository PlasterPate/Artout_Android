package com.mbglobal.artoutandroid.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.event.EventSearchEntity
import com.mbglobal.data.entity.user.UserEntity
import com.mbglobal.data.entity.user.UserSearchEntity
import com.mbglobal.data.repository.EventRepository
import com.mbglobal.data.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

//DiscoverViewModel is shared between different fragment, remember to init them with activity scope
class DiscoverViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val userRepository: UserRepository
) :
    BaseViewModel() {

    private val _previewSearchResults: MutableLiveData<Pair<List<EventEntity>, List<UserEntity>>> = MutableLiveData()
    val previewSearchResults: LiveData<Pair<List<EventEntity>, List<UserEntity>>> = _previewSearchResults

    private val _searchResults: MutableLiveData<Pair<List<EventEntity>, List<UserEntity>>> = MutableLiveData()
    val searchResults: LiveData<Pair<List<EventEntity>, List<UserEntity>>> = _searchResults

    private val _showSearchResultsLoading: MutableLiveData<Boolean> = MutableLiveData()
    val showSearchResultsLoading: LiveData<Boolean> = _showSearchResultsLoading

    private val _searchQuery: MutableLiveData<String> = MutableLiveData()
    val searchQuery: LiveData<String> = _searchQuery

    fun queryChange(searchQuery: String) {
        if (searchQuery.isBlank()) {
            _previewSearchResults.value = Pair(listOf(), listOf())
            return
        }
        eventRepository.searchEvent(
            EventSearchEntity(
                event = searchQuery,
                category = null
            )
        ).zipWith(
            userRepository.searchUser(
                UserSearchEntity(
                    search = searchQuery
                )
            ), BiFunction{ a: List<EventEntity>, b: List<UserEntity> -> Pair(a, b) }
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ searchResults ->
                _previewSearchResults.value = searchResults
            }, {

            }).also {
                compositeDisposable.add(it)
            }
    }

    fun submitSearch(searchQuery: String) {
        eventRepository.searchEvent(
            EventSearchEntity(
                event = searchQuery,
                category = null
            )
        ).zipWith(
            userRepository.searchUser(
                UserSearchEntity(
                    search = searchQuery
                )
            ), BiFunction{ a: List<EventEntity>, b: List<UserEntity> -> Pair(a, b) }
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ searchResults ->
                _searchResults.value = searchResults
                _showSearchResultsLoading.value = false
            }, {

            }).also {
                compositeDisposable.add(it)
            }
        _searchQuery.postValue(searchQuery)
    }
}
