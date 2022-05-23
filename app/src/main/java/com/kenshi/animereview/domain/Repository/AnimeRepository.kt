package com.kenshi.animereview.domain.Repository

import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.ui.base.UiState
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun fetchRecommendAnime(): Flow<UiState<List<AnimeInfo>>>
}