package com.kenshi.animereview.domain.repository

import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.ui.base.UiState
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
//    fun fetchAnimeListById(): Flow<UiState<List<KitsuAnimeInfo>>>
//
//    fun fetchAnimeById(id: String): Flow<UiState<KitsuAnimeInfo>>
//
//    fun fetchGenreAnime(category: String): Flow<UiState<List<AnimeInfo>>>
//
//    fun fetchTrendingAnime(): Flow<UiState<List<KitsuAnimeInfo>>>
//
//    fun fetchSearchAnime(title: String): Flow<UiState<List<KitsuAnimeInfo>>>

    fun fetchAnimeListById(): Flow<UiState<List<AnimeInfo>>>

    //fun fetchAnimeById(id: String): Flow<UiState<AnimeInfo>>

    fun fetchGenreAnime(category: String): Flow<UiState<List<AnimeInfo>>>

    fun fetchTrendingAnime(): Flow<UiState<List<AnimeInfo>>>

    fun fetchSearchAnime(title: String): Flow<UiState<List<AnimeInfo>>>
}