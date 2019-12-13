package com.mbglobal.artoutandroid.app

import com.mbglobal.data.datasource.SessionLocalDataSource
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject


class RequestInterceptor @Inject constructor(private val sessionLocalDataSource: SessionLocalDataSource) :
    Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val token = sessionLocalDataSource.getSession().map {
            it.access
        }.blockingGet()

        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "bearer $token")
            .build()

        Timber.v("movahedisstupid${newRequest.headers}")
        return chain.proceed(newRequest)
    }
}