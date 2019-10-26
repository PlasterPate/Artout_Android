package com.mbglobal.local.datasource

import android.content.SharedPreferences
import com.mbglobal.data.datasource.TokenLocalDataSource
import io.reactivex.Single
import javax.inject.Inject

class TokenLocalDataSourceImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    TokenLocalDataSource {
    override fun saveRefreshToken(refresh: String): Single<String> {
        return Single.fromCallable {
            sharedPreferences.edit().putString(REFRESH_TOKEN_KEY, refresh).apply()
            refresh
        }
    }

    override fun saveAccessToken(access: String): Single<String> {
        return Single.fromCallable {
            sharedPreferences.edit().putString(ACCESS_TOKEN_KEY, access).apply()
            access
        }
    }

    override fun getAccessToken(): Single<String?> {
        return Single.fromCallable {
            sharedPreferences.getString(ACCESS_TOKEN_KEY, "")
        }
    }

    override fun getRefreshToken(): Single<String?> {
        return Single.fromCallable {
            sharedPreferences.getString(REFRESH_TOKEN_KEY, "")
        }
    }

    companion object {
        const val ACCESS_TOKEN_KEY = "access_token_key"
        const val REFRESH_TOKEN_KEY = "refresh_token_key"
    }
}