package com.kenshi.animereview.data.model.jikan

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
//data class Anime(
//    val pagination: Pagination,
//    @field:Json(name = "data")
//    val anime: AnimeInfo,
//) {
//    @JsonClass(generateAdapter = true)
//    data class Pagination(
//        @field:Json(name = "last_visible_page")
//        val lastVisiblePage: Int,
//        @field:Json(name = "has_nest_page")
//        val hasNextPage: Boolean,
//        @field:Json(name = "current_page")
//        val currentPage: Int,
//        val items: Item
//    ) {
//        @JsonClass(generateAdapter = true)
//        data class Item (
//            val count: Int,
//            val total: Int,
//            @field:Json(name="per_page")
//            val perPage: Int
//        )
//    }
//}

//@JsonClass(generateAdapter = true)
//class Anime(
//    @field:Json(name = "data")
//    val anime: AnimeInfo,
//)