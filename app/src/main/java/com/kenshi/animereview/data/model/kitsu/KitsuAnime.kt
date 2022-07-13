package com.kenshi.animereview.data.model.kitsu

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class KitsuAnime(
    @field:Json(name = "data")
    val anime: KitsuAnimeInfo,
)