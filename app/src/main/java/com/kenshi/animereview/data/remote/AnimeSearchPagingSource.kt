package com.kenshi.animereview.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kenshi.animereview.data.model.jikan.AnimeInfo
import com.kenshi.animereview.data.network.service.AnimeService
import com.kenshi.animereview.util.Constants.PAGING_SIZE
import retrofit2.HttpException
import java.io.IOException

class AnimeSearchPagingSource(
    private val animeService: AnimeService,
    private val query: String,
) : PagingSource<Int, AnimeInfo>() {

    //페이지를 갱신할때 수행되는 함수
    override fun getRefreshKey(state: PagingState<Int, AnimeInfo>): Int? {
        //가장 최근의 접근한 page를 state.anchorPosition 으로 받고
        //그 주위의 페이지를 읽어오도록 키를 반환해주는 역할
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)

        }
    }

    //pager 가 데이터를 호출할 때마다 불리우는 함수
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeInfo> {
        return try {
            val pageNumber = params.key ?: STARTING_PAGE_INDEX
            // 이걸 직접 핸들링 할 수 있나?
            // 이런 query가 api parameter 에 포함되어있으면 그냥 넣어주면 되는데 아닌 경우엔...
            //val response = animeService.fetchSearchAnime(query, pageNumber, params.loadSize)
            val response = animeService.fetchSearchAnime(query)
            //api response parameter 로 isEnd 를 제공해줌 이 값을 통해 마지막 페이지인 여부를 판단할 수 있음
            val endOfPaginationReached = response.body()?.pagination?.hasNextPage!!

            val data = response.body()?.animeList!!
            val prevKey = if (pageNumber == STARTING_PAGE_INDEX)
            //첫번째 키 이기 때문에 그 전 key null
                null else pageNumber - 1
            val nextKey = if (endOfPaginationReached) {
                //마지막 키 이기때문에 그 다음 key null
                null
            } else {
                pageNumber + (params.loadSize / PAGING_SIZE)
            }
            //반환
            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    //key 의 초기값은 null, load 함수 참고
    companion object {
        const val STARTING_PAGE_INDEX = 1
    }
}