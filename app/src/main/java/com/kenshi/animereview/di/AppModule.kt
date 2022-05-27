package com.kenshi.animereview.di

import com.kenshi.animereview.data.remote.AnimeRepositoryImpl
import com.kenshi.animereview.data.remote.ReviewRepositoryImpl
import com.kenshi.animereview.data.remote.UserRepositoryImpl
import com.kenshi.animereview.domain.repository.AnimeRepository
import com.kenshi.animereview.domain.repository.ReviewRepository
import com.kenshi.animereview.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideAnimeRepository(animeRepositoryImpl: AnimeRepositoryImpl): AnimeRepository

    @Binds
    @Singleton
    abstract fun provideReviewRepository(reviewRepositoryImpl: ReviewRepositoryImpl): ReviewRepository

    @Binds
    @Singleton
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

}