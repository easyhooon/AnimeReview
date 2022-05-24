package com.kenshi.animereview.domain.repository

import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.ui.base.UiState
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun fetchAnimeListById(): Flow<UiState<List<AnimeInfo>>>

    fun fetchAnimeById(id: String): Flow<UiState<AnimeInfo>>

    fun fetchGenreAnime(value: String): Flow<UiState<List<AnimeInfo>>>
}