package com.kenshi.animereview.data.network.service

import com.kenshi.animereview.data.model.Anime
import com.kenshi.animereview.data.model.AnimeList
import com.kenshi.animereview.data.model.JikanAnimeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeService {

    // 추천 기능
    // 알고리즘을 통한 추천 기능이 api 에 존재하지 않으므로 내가 선택한 anime list 를 화면에 띄워주도록
    // anime id 검색
    @GET("anime/{id}")
    suspend fun fetchAnimeById(
        @Path("id") id: String
    ): Response<Anime>

    @GET("trending/anime")
    suspend fun fetchTrendingAnime(): Response<AnimeList>


    // 제목 검색
    @GET("anime")
    suspend fun fetchSearchAnime(
        @Query("filter[text]") title: String,
    ): Response<AnimeList>
}
