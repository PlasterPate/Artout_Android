package com.mbglobal.artoutandroid.di.module

import com.mbglobal.remote.api.TokenService
import com.mbglobal.remote.api.UserService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun providesOkHttp() : OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder().baseUrl(UserService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun providesUserService(retrofit: Retrofit) : UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    fun providesTokenService() : TokenService {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(TokenService.BASE_URL)
            .build()
            .create(TokenService::class.java)
    }
}