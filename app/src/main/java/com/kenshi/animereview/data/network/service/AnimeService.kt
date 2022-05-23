package com.kenshi.animereview.data.network.service

import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.data.model.AnimeList
import com.kenshi.animereview.data.model.AnimeResponse
import retrofit2.Response
import retrofit2.http.GET

interface AnimeService {

    //TODO ENDPOINT 수정
    @GET("anime?filter%5Bcategories%5D=adventure")
    suspend fun fetchRecommendAnime(): Response<AnimeList>
}
