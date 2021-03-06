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
    fun providesUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    fun providesTokenService(retrofit: Retrofit): TokenService {
        return retrofit.create(TokenService::class.java)
    }

    @Provides
    fun providesTimelineService(retrofit: Retrofit): TimelineService {
        return retrofit.create(TimelineService::class.java)
    }

    @Provides
    fun provideS3Service(okHttpLoggingInterceptor: HttpLoggingInterceptor): S3Service{
        return Retrofit.Builder()
            .baseUrl("http://194.5.193.99:9000/")
            .client(OkHttpClient.Builder().addInterceptor(okHttpLoggingInterceptor).build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(S3Service::class.java)
//        return retrofit.create(S3Service::class.java)
    }
}