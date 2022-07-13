package com.kenshi.animereview.domain.repository

import androidx.paging.PagingData
import com.kenshi.animereview.data.model.jikan.AnimeInfo
import com.kenshi.animereview.data.model.jikan.AnimeList
import com.kenshi.animereview.data.model.kitsu.KitsuAnimeInfo
import com.kenshi.animereview.ui.base.UiState
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun fetchAnimeListById(): Flow<UiState<List<KitsuAnimeInfo>>>

    fun fetchAnimeById(id: String): Flow<UiState<KitsuAnimeInfo>>

    fun fetchTrendingAnime(): Flow<UiState<List<KitsuAnimeInfo>>>

    fun fetchGenreAnime(category: String): Flow<UiState<List<AnimeInfo>>>

    fun fetchSearchAnime(query: String): Flow<PagingData<AnimeInfo>>
}