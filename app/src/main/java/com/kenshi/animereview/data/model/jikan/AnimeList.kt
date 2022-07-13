package com.kenshi.animereview.data.model.jikan

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class AnimeList(
    val pagination: Pagination,
    @field:Json(name = "data")
    val animeList: List<AnimeInfo>
)