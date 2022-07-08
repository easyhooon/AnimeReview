package com.kenshi.animereview.data.network.service

import com.kenshi.animereview.data.model.JikanAnimeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JikanAnimeService {

    // 장르 검색
    @GET("anime")
    suspend fun fetchGenreAnime(
        @Query("genre") genre: String,
    ): Response<JikanAnimeList>
}