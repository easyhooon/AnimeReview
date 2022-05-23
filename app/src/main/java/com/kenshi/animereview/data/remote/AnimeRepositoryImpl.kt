package com.kenshi.animereview.data.remote

import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.data.network.service.AnimeService
import com.kenshi.animereview.domain.Repository.AnimeRepository
import com.kenshi.animereview.ui.base.UiState
import com.ready.lolchamps.exceptions.EmptyBodyException
import com.ready.lolchamps.exceptions.NetworkFailureException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeService: AnimeService,
) : AnimeRepository {

    override fun fetchRecommendAnime(): Flow<UiState<List<AnimeInfo>>> = flow<UiState<List<AnimeInfo>>> {
        val response = animeService.fetchRecommendAnime()
        if (response.isSuccessful) {
            Timber.tag("response").d("$response")
            //Timber.tag("response.body()").d("${response.body()}")
            val animeList: List<AnimeInfo> =
                ((response.body()?.AnimeList
                    ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")))
            Timber.tag("animeList").d("$animeList")
            emit(UiState.Success(animeList))
        } else {
            Timber.tag("request fail").d("${response.errorBody()}")
            throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
        }
    }
}