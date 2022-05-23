package com.kenshi.animereview.data.model

import com.google.gson.annotations.SerializedName

class AnimeList (
    @SerializedName("data")
    val AnimeList: List<AnimeInfo>
)