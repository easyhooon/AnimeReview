package com.kenshi.animereview.data.model.jikan

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class Pagination(
    @field:Json(name = "last_visible_page")
    val lastVisiblePage: Int,
    @field:Json(name = "has_next_page")
    val hasNextPage: Boolean,
    @field:Json(name = "current_page")
    val currentPage: Int,
    val items: Item
) {

    @Keep
    @JsonClass(generateAdapter = true)
    data class Item (
        val count: Int,
        val total: Int,
        @field:Json(name="per_page")
        val perPage: Int
    )
}