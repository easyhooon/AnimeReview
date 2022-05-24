package com.kenshi.animereview.di

import com.kenshi.animereview.BuildConfig
import com.kenshi.animereview.data.network.RequestDebugInterceptor
import com.kenshi.animereview.data.network.service.AnimeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
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
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ):  Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BuildConfig.ANIME_API_BASE_URL)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
            //addConverterFactory(MoshiConverterFactory.create())
        }.build()
    }

    @Provides
    @Singleton
    fun provideAnimeService(retrofit: Retrofit): AnimeService {
        return retrofit.create(AnimeService::class.java)
    }
}