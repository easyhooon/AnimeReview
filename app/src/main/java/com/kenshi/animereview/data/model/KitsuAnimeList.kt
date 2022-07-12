package com.kenshi.animereview.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class KitsuAnimeList (
    @field:Json(name = "data")
    val animeList: List<KitsuAnimeInfo>
)