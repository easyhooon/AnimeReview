package com.kenshi.animereview.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class JikanAnimeList (
    @field:Json(name = "data")
    val jikanAimeList: List<JikanAnimeInfo>
)