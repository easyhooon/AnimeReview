package com.kenshi.animereview.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kenshi.animereview.data.model.jikan.AnimeInfo
import com.kenshi.animereview.data.model.jikan.AnimeList
import com.kenshi.animereview.data.model.kitsu.KitsuAnimeInfo
import com.kenshi.animereview.data.network.service.AnimeService
import com.kenshi.animereview.data.network.service.KitsuAnimeService
import com.kenshi.animereview.domain.repository.AnimeRepository
import com.kenshi.animereview.exceptions.EmptyBodyException
import com.kenshi.animereview.exceptions.NetworkFailureException
import com.kenshi.animereview.ui.base.UiState
import com.kenshi.animereview.util.Constants.PAGING_SIZE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val kitsuAnimeService: KitsuAnimeService,
    private val animeService: AnimeService,
    private val ioDispatcher: CoroutineDispatcher,
) : AnimeRepository {

    // 맞춤 추천 api 가 없으므로 임의의 애니메이션을 선택하여 골라 list 에 담아서 emit 하는 방식 선택
    // 순서대로 카우보이 비밥, 스파이 패밀리, 진격의 거인, 귀멸의 칼날, 슈타인즈게이트, 카구야, 비스타즈, 비스크돌, 강연금, 좀비랜드 사가
    // 데스노트, 주술회전, 히로아카, 원펀맨, 모브사이코, 코드기어스, 바이올렛 에버가든, 메이드 인 어비스, 나만이 없는 거리, 사이코패스
    // 하이큐, 에반게리온, 4구라, 헬싱 ova, 기생수, 너의 이름은, 센과 치히로, 시달소, 오드택시, 나루토 질풍전

    // Kitsu API
    private val recommendAnimeIdList: MutableList<String> = mutableListOf(
        "1", "45398", "7442", "41370", "5646", "41373", "42147", "44382", "3936", "41459",
        "1376", "42765", "11469", "10740", "11578", "1415", "12230", "13273", "11110", "7000",
        "8133", "21", "8403", "695", "8147", "11614", "176", "2027", "43932", "1555"
    )

    // Jikan API --> 3 Request / second 이거로는 못써먹겄다
//    private val recommendAnimeIdList: MutableList<String> = mutableListOf(
//        "1", "50265", "16498", "38000", "9253", "41373", "39195", "48736", "121", "37976"
//    )

    // "1376", "42765", "11469", "10740", "11578", "1415", "12230", "13273", "11110", "7000",
    //        "8133", "21", "8403", "695", "8147", "11614", "176", "2027", "43932", "1555"

        //TODO 효율적으로 호출 방법으로 개선                                                                                   ㄴㄴ
    override fun fetchAnimeListById(): Flow<UiState<List<KitsuAnimeInfo>>> =
        flow<UiState<List<KitsuAnimeInfo>>> {
            var recommendAnimeList: MutableList<KitsuAnimeInfo> = mutableListOf()
            val randomIndexList = getRandomNumber()
            Timber.tag("randomIndexList").d("$randomIndexList")

            //TODO 비동기 처리, Combine 연산자로 변경해볼 것
            //TODO ioDispatcher 로 CoroutineContext 변환 안해줘도됨
            withContext(ioDispatcher) {
                val response1 =
                    async { kitsuAnimeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[0]]) }
                val response2 =
                    async { kitsuAnimeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[1]]) }
                val response3 =
                    async { kitsuAnimeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[2]]) }
                val response4 =
                    async { kitsuAnimeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[3]]) }
                val response5 =
                    async { kitsuAnimeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[4]]) }
                val response6 =
                    async { kitsuAnimeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[5]]) }
                val response7 =
                    async { kitsuAnimeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[6]]) }
                val response8 =
                    async { kitsuAnimeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[7]]) }
                val response9 =
                    async { kitsuAnimeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[8]]) }
                val response10 =
                    async { kitsuAnimeService.fetchAnimeById(recommendAnimeIdList[randomIndexList[9]]) }

                recommendAnimeList = mutableListOf(
                    response1.await().body()!!.anime,
                    response2.await().body()!!.anime,
                    response3.await().body()!!.anime,
                    response4.await().body()!!.anime,
                    response5.await().body()!!.anime,
                    response6.await().body()!!.anime,
                    response7.await().body()!!.anime,
                    response8.await().body()!!.anime,
                    response9.await().body()!!.anime,
                    response10.await().body()!!.anime,
                )
            }

            Timber.tag("recommendAnimeList").d("$recommendAnimeList")
            emit(UiState.Success(recommendAnimeList))
        }.catch { emit(UiState.Error(it)) }


    override fun fetchAnimeById(id: String): Flow<UiState<KitsuAnimeInfo>> = flow<UiState<KitsuAnimeInfo>> {
        val response = kitsuAnimeService.fetchAnimeById(id)
        if (response.isSuccessful) {
            Timber.tag("response.body()").d("${response.body()}")
            val anime: KitsuAnimeInfo =
                response.body()?.anime
                    ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")
            Timber.tag("anime").d("$anime")
            emit(UiState.Success(anime))
        } else {
            Timber.tag("request fail").d("${response.errorBody()}")
            throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
        }
    }.catch { emit(UiState.Error(it)) }


    // flow 는 coroutine 이므로 flow block 내부에서는 suspend 함수를 호출할 수 있다.
    override fun fetchGenreAnime(category: String): Flow<UiState<List<AnimeInfo>>> =
        flow<UiState<List<AnimeInfo>>> {
            val response = animeService.fetchGenreAnime(category)
            if (response.isSuccessful) {
                Timber.tag("response.body()").d("${response.body()}")
                val animeList: List<AnimeInfo> =
                    ((response.body()?.animeList
                        ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")))
                Timber.tag("genreAnimeList").d("$animeList")
                emit(UiState.Success(animeList))
            } else {
                Timber.tag("request fail").d("${response.errorBody()}")
                throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
            }
        }.catch { emit(UiState.Error(it)) }


    override fun fetchTrendingAnime(): Flow<UiState<List<KitsuAnimeInfo>>> =
        flow<UiState<List<KitsuAnimeInfo>>> {
            val response = kitsuAnimeService.fetchTrendingAnime()
            if (response.isSuccessful) {
                Timber.tag("response.body()").d("${response.body()}")
                val animeList: List<KitsuAnimeInfo> =
                    ((response.body()?.animeList
                        ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")))
                Timber.tag("animeList").d("$animeList")
                emit(UiState.Success(animeList))
            } else {
                Timber.tag("request fail").d("${response.errorBody()}")
                throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
            }
        }.catch { emit(UiState.Error(it)) }


    override fun fetchSearchAnime(query: String): Flow<PagingData<AnimeInfo>> {
        val pagingSourceFactory = { AnimeSearchPagingSource(animeService, query) }

        return Pager(
            // pager 를 구현하기 위해서는
            // pagingConfig 를 통해 parameter 를 전달 해줘야함
            config = PagingConfig(
                //어떤 기기로 동작 시키든 뷰홀더에 표시할 데이터가 모자르지 않을 정도의 값으로 설정
                pageSize = PAGING_SIZE,
                //true -> repository 의 전체 데이터 사이즈를 받아와서 recyclerview 의 placeholder 를 미리 만들어놓음
                //화면에 표시되지 않는 항목은 null로 표시
                //필요할때 필요한 만큼만 로딩하려면 false
                enablePlaceholders = false,
                //페이저가 메모리에 가지고 있을 수 있는 최대 개수, 페이지 사이즈의 2~3배 정도
                maxSize = PAGING_SIZE * 3
            ),
            //api 호출 결과를 팩토리에 전달
            pagingSourceFactory = pagingSourceFactory
            // 결과를 flow 로 변환
        ).flow
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