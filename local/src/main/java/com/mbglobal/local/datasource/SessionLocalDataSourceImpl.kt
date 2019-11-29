package com.mbglobal.local.datasource

import android.content.SharedPreferences
import android.security.KeyChainAliasCallback
import com.mbglobal.data.datasource.SessionLocalDataSource
import com.mbglobal.data.entity.session.SessionEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SessionLocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SessionLocalDataSource {

    override fun getSession(): Single<SessionEntity> {
        val access = sharedPreferences.getString(ACCESS_TOKEN_KEY, "")
        val refresh = sharedPreferences.getString(REFRESH_TOKEN_KEY, "")
        val userId = sharedPreferences.getString(USER_ID_KEY, "")
        if (access!!.isBlank() || refresh!!.isBlank() || userId!!.isBlank()) {
            return Single.just(SessionEntity(access = "", refresh = "", userId = ""))
        }
        return Single.just(SessionEntity(access, refresh, userId))
    }

    override fun saveSession(sessionEntity: SessionEntity): Completable {
        return Completable.fromAction {
            sharedPreferences.edit().apply {
                putString(ACCESS_TOKEN_KEY, sessionEntity.access).apply()
                putString(REFRESH_TOKEN_KEY, sessionEntity.refresh).apply()
                putString(USER_ID_KEY, sessionEntity.userId).apply()
            }
        }
    }

    override fun removeSession(): Completable {
        return Completable.fromAction {
            sharedPreferences.edit().apply {
                remove(ACCESS_TOKEN_KEY).apply()
                remove(REFRESH_TOKEN_KEY).apply()
                remove(USER_ID_KEY).apply()
            }
        }
    }

    companion object {
        const val ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY"
        const val REFRESH_TOKEN_KEY = "REFRESH_TOKEN_KEY"
        const val USER_ID_KEY = "USER_ID_KEY"
    }
}