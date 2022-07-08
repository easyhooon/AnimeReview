package com.kenshi.animereview.data.remote

import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.data.model.JikanAnimeInfo
import com.kenshi.animereview.data.network.service.AnimeService
import com.kenshi.animereview.data.network.service.JikanAnimeService
import com.kenshi.animereview.domain.repository.AnimeRepository
import com.kenshi.animereview.ui.base.UiState
import com.kenshi.animereview.exceptions.EmptyBodyException
import com.kenshi.animereview.exceptions.NetworkFailureException
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
    private val jikanAnimeService: JikanAnimeService,
    private val ioDispatcher: CoroutineDispatcher,
) : AnimeRepository {

    // 맞춤 추천 api 가 없으므로 임의의 애니메이션을 선택하여 골라 list 에 담아서 emit 하는 방식 선택
    // 순서대로 카우보이 비밥, 스파이 패밀리, 진격의 거인, 귀멸의 칼날, 슈타인즈게이트, 카구야, 비스타즈, 비스크돌, 강연금, 좀비랜드 사가
    // 데스노트, 주술회전, 히로아카, 원펀맨, 모브사이코, 코드기어스, 바이올렛 에버가든, 메이드 인 어비스, 나만이 없는 거리, 사이코패스
    // 하이큐, 에반게리온, 4구라, 헬싱 ova, 기생수, 너의 이름은, 센과 치히로, 시달소, 오드택시, 나루토 질풍전
    private val recommendAnimeIdList: MutableList<String> = mutableListOf(
        "1", "45398", "7442", "41370", "5646", "41373", "42147", "44382", "3936", "41459",
        "1376", "42765", "11469", "10740", "11578", "1415", "12230", "13273", "11110", "7000",
        "8133", "21", "8403", "695", "8147", "11614", "176", "2027", "43932", "1555"
    )

    //TODO 효율적으로 호출 방법으로 개선                                                                                   ㄴㄴ
    override fun fetchAnimeListById(): Flow<UiState<List<AnimeInfo>>> =
        flow<UiState<List<AnimeInfo>>> {
            var recommendAnimeList: MutableList<AnimeInfo> = mutableListOf()
            val randomIndexList = getRandomNumber()
            Timber.tag("randomIndexList").d("$randomIndexList")

            //TODO 비동기 처리, Combine 연산자로 변경해볼 것
            withContext(ioDispatcher) {
                val response1 =
                    async { animeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[0]]) }
                val response2 =
                    async { animeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[1]]) }
                val response3 =
                    async { animeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[2]]) }
                val response4 =
                    async { animeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[3]]) }
                val response5 =
                    async { animeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[4]]) }
                val response6 =
                    async { animeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[5]]) }
                val response7 =
                    async { animeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[6]]) }
                val response8 =
                    async { animeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[7]]) }
                val response9 =
                    async { animeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[8]]) }
                val response10 =
                    async { animeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[9]]) }

                recommendAnimeList = mutableListOf(
                    response1.await().body()!!.Anime,
                    response2.await().body()!!.Anime,
                    response3.await().body()!!.Anime,
                    response4.await().body()!!.Anime,
                    response5.await().body()!!.Anime,
                    response6.await().body()!!.Anime,
                    response7.await().body()!!.Anime,
                    response8.await().body()!!.Anime,
                    response9.await().body()!!.Anime,
                    response10.await().body()!!.Anime,
                )
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
    override fun fetchGenreAnime(category: String): Flow<UiState<List<JikanAnimeInfo>>> =
        flow<UiState<List<JikanAnimeInfo>>> {
            val response = jikanAnimeService.fetchGenreAnime(category)
            if (response.isSuccessful) {
                Timber.tag("response.body()").d("${response.body()}")
                val animeList: List<JikanAnimeInfo> =
                    ((response.body()?.jikanAimeList
                        ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")))
                Timber.tag("genreAnimeList").d("$animeList")
                emit(UiState.Success(animeList))
            } else {
                Timber.tag("request fail").d("${response.errorBody()}")
                throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
            }
        }.catch { emit(UiState.Error(it)) }

    override fun fetchTrendingAnime(): Flow<UiState<List<AnimeInfo>>> =
        flow<UiState<List<AnimeInfo>>> {
            val response = animeService.fetchTrendingAnime()
            if (response.isSuccessful) {
                Timber.tag("response.body()").d("${response.body()}")
                val animeList: List<AnimeInfo> =
                    ((response.body()?.animeList
                        ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")))
                Timber.tag("animeList").d("$animeList")
                emit(UiState.Success(animeList))
            } else {
                Timber.tag("request fail").d("${response.errorBody()}")
                throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
            }
        }.catch { emit(UiState.Error(it)) }



    override fun fetchSearchAnime(title: String): Flow<UiState<List<AnimeInfo>>> {
        TODO("Not yet implemented")
    }

    private fun getRandomNumber(): List<Int> {
        val numberList = mutableListOf<Int>().apply {
            for (i in 0 until 30) {
                this.add(i)
            }
            this.shuffle()
        }

        val newList = numberList.subList(0, 10)
        return newList.sorted()
    }
}