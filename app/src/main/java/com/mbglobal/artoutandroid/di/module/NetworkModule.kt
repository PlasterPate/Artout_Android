package com.mbglobal.artoutandroid.di.module

import com.mbglobal.artoutandroid.app.RequestInterceptor
import com.mbglobal.remote.api.*
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor


@Module
class NetworkModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun providesOkHttp(
        okHttpLoggingInterceptor: HttpLoggingInterceptor,
        requestInterceptor: RequestInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(okHttpLoggingInterceptor)
            .build()
    }

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(UserService.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun providesFollowerService(retrofit: Retrofit): FollowerService {
        return retrofit.create(FollowerService::class.java)
    }

    @Provides
    fun providesFollowingService(retrofit: Retrofit): FollowingService {
        return retrofit.create(FollowingService::class.java)
    }

    @Provides
    fun providesEventService(retrofit: Retrofit): EventService {
        return retrofit.create(EventService::class.java)
    }

    @Provides
    fun providesUserService(): UserService {
        return providesRetrofit(OkHttpClient.Builder().addInterceptor
            (provideHttpLoggingInterceptor()).build()).create(UserService::class.java)
    }

    @Provides
    fun providesTokenService(retrofit: Retrofit): TokenService {
        return retrofit.create(TokenService::class.java)
    }
}