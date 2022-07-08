package com.kenshi.animereview.di

import com.kenshi.animereview.BuildConfig
import com.kenshi.animereview.data.network.RequestDebugInterceptor
import com.kenshi.animereview.data.network.service.AnimeService
import com.kenshi.animereview.data.network.service.FirebaseReviewService
import com.kenshi.animereview.data.network.service.FirebaseUserService
import com.kenshi.animereview.data.network.service.JikanAnimeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val timeoutRead = 30L
    private const val timeoutConnect = 30L

    @Provides
    @Singleton
    fun provideRequestDebugInterceptor(): RequestDebugInterceptor {
        return RequestDebugInterceptor()
    }

//    @Provides
//    @Singleton
//    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
//        return HttpLoggingInterceptor().apply {
//            if(BuildConfig.DEBUG) {
//                level = HttpLoggingInterceptor.Level.BODY
//            }
//        }
//    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: RequestDebugInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
            connectTimeout(timeoutConnect, TimeUnit.SECONDS)
            readTimeout(timeoutRead, TimeUnit.SECONDS)
        }.build()
    }

//    @Provides
//    @Singleton
//    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(interceptor)
//            .build()

    @Provides
    @Singleton
    @Named("Kitsu")
    fun provideKitsuRetrofit(
        okHttpClient: OkHttpClient
    ):  Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BuildConfig.KITSU_ANIME_API_BASE_URL)
            client(okHttpClient)
            addConverterFactory(MoshiConverterFactory.create())
        }.build()
    }

    @Provides
    @Singleton
    @Named("Jikan")
    fun provideJikanRetrofit(
        okHttpClient: OkHttpClient
    ):  Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BuildConfig.JIKAN_ANIME_API_BASE_URL)
            client(okHttpClient)
            addConverterFactory(MoshiConverterFactory.create())
        }.build()
    }

    @Provides
    @Singleton
    fun provideKitsuAnimeService(@Named("Kitsu")retrofit: Retrofit): AnimeService {
        return retrofit.create(AnimeService::class.java)
    }

    @Provides
    @Singleton
    fun provideJikanAnimeService(@Named("Jikan")retrofit: Retrofit): JikanAnimeService {
        return retrofit.create(JikanAnimeService::class.java)
    }
}