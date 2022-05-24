package com.kenshi.animereview.data.remote

import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.data.network.service.AnimeService
import com.kenshi.animereview.domain.repository.AnimeRepository
import com.kenshi.animereview.ui.base.UiState
import com.ready.lolchamps.exceptions.EmptyBodyException
import com.ready.lolchamps.exceptions.NetworkFailureException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeService: AnimeService,
    private val ioDispatcher: CoroutineDispatcher,
) : AnimeRepository {

    // 자동 추천 api 가 없으므로 임의의 애니메이션을 선택하여 골라 list 에 담아서 emit 하는 방식 선택
    // 순서대로 카우보이 비밥, 스파이 패밀리, 진격의 거인, 귀멸의 칼날, 슈타인즈게이트, 카구야, 비스타즈, 비스크돌, 86, 좀비랜드 사가
    private val recommendAnimeIdList: MutableList<String> = mutableListOf(
        "1", "45398", "7442", "41370", "5646", "41373", "42147", "44382", "42147", "41459"
    )

    override fun fetchAnimeListById(): Flow<UiState<List<AnimeInfo>>> =
        flow<UiState<List<AnimeInfo>>> {
            val recommendAnimeList: MutableList<AnimeInfo> = mutableListOf()
            withContext(ioDispatcher) {
                for (id in recommendAnimeIdList) {
                    val response = async { animeService.fetchAnimeById(id) }
                    response.await().body()?.let { recommendAnimeList.add(it.Anime) }
                }
            }
            Timber.tag("recommendAnimeList").d("$recommendAnimeList")
            emit(UiState.Success(recommendAnimeList))
        }.catch { emit(UiState.Error(it)) }


    override fun fetchAnimeById(id: String): Flow<UiState<AnimeInfo>> = flow<UiState<AnimeInfo>> {
        val response = animeService.fetchAnimeById(id)
        if (response.isSuccessful) {
            Timber.tag("response.body()").d("${response.body()}")
            val anime: AnimeInfo =
                response.body()?.Anime
                    ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")
            Timber.tag("anime").d("$anime")
            emit(UiState.Success(anime))
        } else {
            Timber.tag("request fail").d("${response.errorBody()}")
            throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
        }
    }.catch { emit(UiState.Error(it)) }


    // flow 는 coroutine 이므로 flow block 내부에서는 suspend 함수를 호출할 수 있다.
    override fun fetchGenreAnime(value: String): Flow<UiState<List<AnimeInfo>>> =
        flow<UiState<List<AnimeInfo>>> {
            val response = animeService.fetchGenreAnime(value)
            if (response.isSuccessful) {
                Timber.tag("response.body()").d("${response.body()}")
                val animeList: List<AnimeInfo> =
                    ((response.body()?.AnimeList
                        ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")))
                Timber.tag("animeList").d("$animeList")
                emit(UiState.Success(animeList))
            } else {
                Timber.tag("request fail").d("${response.errorBody()}")
                throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
            }
        }.catch { emit(UiState.Error(it)) }
}