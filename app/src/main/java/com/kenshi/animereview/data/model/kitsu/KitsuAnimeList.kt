package com.kenshi.animereview.data.model.kitsu

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class KitsuAnimeList (
    @field:Json(name = "data")
    val animeList: List<KitsuAnimeInfo>
)