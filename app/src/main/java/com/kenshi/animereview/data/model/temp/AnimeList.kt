//package com.kenshi.animereview.data.model.temp
//
//
//import androidx.annotation.Keep
//import com.squareup.moshi.Json
//import com.squareup.moshi.JsonClass
//
//@Keep
//@JsonClass(generateAdapter = true)
//data class AnimeList(
//    @Json(name = "pagination")
//    val pagination: Pagination,
//    @Json(name = "data")
//    val animeList: List<AnimeInfo>
//) {
//    @Keep
//    @JsonClass(generateAdapter = true)
//    data class Pagination(
//        @Json(name = "last_visible_page")
//        val lastVisiblePage: Int,
//        @Json(name = "has_next_page")
//        val hasNextPage: Boolean,
//        @Json(name = "current_page")
//        val currentPage: Int,
//        @Json(name = "items")
//        val items: Items
//    ) {
//        @Keep
//        @JsonClass(generateAdapter = true)
//        data class Items(
//            @Json(name = "count")
//            val count: Int,
//            @Json(name = "total")
//            val total: Int,
//            @Json(name = "per_page")
//            val perPage: Int
//        )
//    }
//
//    @Keep
//    @JsonClass(generateAdapter = true)
//    data class AnimeInfo(
//        @Json(name = "mal_id")
//        val malId: Int,
//        @Json(name = "url")
//        val url: String,
//        @Json(name = "images")
//        val images: Images,
//        @Json(name = "trailer")
//        val trailer: Trailer,
//        @Json(name = "title")
//        val title: String,
//        @Json(name = "title_english")
//        val titleEnglish: String,
//        @Json(name = "title_japanese")
//        val titleJapanese: String,
//        @Json(name = "title_synonyms")
//        val titleSynonyms: List<String>,
//        @Json(name = "type")
//        val type: String,
//        @Json(name = "source")
//        val source: String,
//        @Json(name = "episodes")
//        val episodes: Int,
//        @Json(name = "status")
//        val status: String,
//        @Json(name = "airing")
//        val airing: Boolean,
//        @Json(name = "aired")
//        val aired: Aired,
//        @Json(name = "duration")
//        val duration: String,
//        @Json(name = "rating")
//        val rating: String,
//        @Json(name = "score")
//        val score: Double,
//        @Json(name = "scored_by")
//        val scoredBy: Int,
//        @Json(name = "rank")
//        val rank: Int,
//        @Json(name = "popularity")
//        val popularity: Int,
//        @Json(name = "members")
//        val members: Int,
//        @Json(name = "favorites")
//        val favorites: Int,
//        @Json(name = "synopsis")
//        val synopsis: String,
//        @Json(name = "background")
//        val background: String,
//        @Json(name = "season")
//        val season: String,
//        @Json(name = "year")
//        val year: Int,
//        @Json(name = "broadcast")
//        val broadcast: Broadcast,
//        @Json(name = "producers")
//        val producers: List<Producer>,
//        @Json(name = "licensors")
//        val licensors: List<Licensor>,
//        @Json(name = "studios")
//        val studios: List<Studio>,
//        @Json(name = "genres")
//        val genres: List<Genre>,
//        @Json(name = "explicit_genres")
//        val explicitGenres: List<Any>,
//        @Json(name = "themes")
//        val themes: List<Theme>,
//        @Json(name = "demographics")
//        val demographics: List<Demographic>
//    ) {
//        @Keep
//        @JsonClass(generateAdapter = true)
//        data class Images(
//            @Json(name = "jpg")
//            val jpg: Jpg,
//            @Json(name = "webp")
//            val webp: Webp
//        ) {
//            @Keep
//            @JsonClass(generateAdapter = true)
//            data class Jpg(
//                @Json(name = "image_url")
//                val imageUrl: String,
//                @Json(name = "small_image_url")
//                val smallImageUrl: String,
//                @Json(name = "large_image_url")
//                val largeImageUrl: String
//            )
//
//            @Keep
//            @JsonClass(generateAdapter = true)
//            data class Webp(
//                @Json(name = "image_url")
//                val imageUrl: String,
//                @Json(name = "small_image_url")
//                val smallImageUrl: String,
//                @Json(name = "large_image_url")
//                val largeImageUrl: String
//            )
//        }
//
//        @Keep
//        @JsonClass(generateAdapter = true)
//        data class Trailer(
//            @Json(name = "youtube_id")
//            val youtubeId: String,
//            @Json(name = "url")
//            val url: String,
//            @Json(name = "embed_url")
//            val embedUrl: String,
//            @Json(name = "images")
//            val images: Images
//        ) {
//            @Keep
//            @JsonClass(generateAdapter = true)
//            data class Images(
//                @Json(name = "image_url")
//                val imageUrl: String,
//                @Json(name = "small_image_url")
//                val smallImageUrl: String,
//                @Json(name = "medium_image_url")
//                val mediumImageUrl: String,
//                @Json(name = "large_image_url")
//                val largeImageUrl: String,
//                @Json(name = "maximum_image_url")
//                val maximumImageUrl: String
//            )
//        }
//
//        @Keep
//        @JsonClass(generateAdapter = true)
//        data class Aired(
//            @Json(name = "from")
//            val from: String,
//            @Json(name = "to")
//            val to: String,
//            @Json(name = "prop")
//            val prop: Prop,
//            @Json(name = "string")
//            val string: String
//        ) {
//            @Keep
//            @JsonClass(generateAdapter = true)
//            data class Prop(
//                @Json(name = "from")
//                val from: From,
//                @Json(name = "to")
//                val to: To
//            ) {
//                @Keep
//                @JsonClass(generateAdapter = true)
//                data class From(
//                    @Json(name = "day")
//                    val day: Int,
//                    @Json(name = "month")
//                    val month: Int,
//                    @Json(name = "year")
//                    val year: Int
//                )
//
//                @Keep
//                @JsonClass(generateAdapter = true)
//                data class To(
//                    @Json(name = "day")
//                    val day: Int,
//                    @Json(name = "month")
//                    val month: Int,
//                    @Json(name = "year")
//                    val year: Int
//                )
//            }
//        }
//
//        @Keep
//        @JsonClass(generateAdapter = true)
//        data class Broadcast(
//            @Json(name = "day")
//            val day: String,
//            @Json(name = "time")
//            val time: String,
//            @Json(name = "timezone")
//            val timezone: String,
//            @Json(name = "string")
//            val string: String
//        )
//
//        @Keep
//        @JsonClass(generateAdapter = true)
//        data class Producer(
//            @Json(name = "mal_id")
//            val malId: Int,
//            @Json(name = "type")
//            val type: String,
//            @Json(name = "name")
//            val name: String,
//            @Json(name = "url")
//            val url: String
//        )
//
//        @Keep
//        @JsonClass(generateAdapter = true)
//        data class Licensor(
//            @Json(name = "mal_id")
//            val malId: Int,
//            @Json(name = "type")
//            val type: String,
//            @Json(name = "name")
//            val name: String,
//            @Json(name = "url")
//            val url: String
//        )
//
//        @Keep
//        @JsonClass(generateAdapter = true)
//        data class Studio(
//            @Json(name = "mal_id")
//            val malId: Int,
//            @Json(name = "type")
//            val type: String,
//            @Json(name = "name")
//            val name: String,
//            @Json(name = "url")
//            val url: String
//        )
//
//        @Keep
//        @JsonClass(generateAdapter = true)
//        data class Genre(
//            @Json(name = "mal_id")
//            val malId: Int,
//            @Json(name = "type")
//            val type: String,
//            @Json(name = "name")
//            val name: String,
//            @Json(name = "url")
//            val url: String
//        )
//
//        @Keep
//        @JsonClass(generateAdapter = true)
//        data class Theme(
//            @Json(name = "mal_id")
//            val malId: Int,
//            @Json(name = "type")
//            val type: String,
//            @Json(name = "name")
//            val name: String,
//            @Json(name = "url")
//            val url: String
//        )
//
//        @Keep
//        @JsonClass(generateAdapter = true)
//        data class Demographic(
//            @Json(name = "mal_id")
//            val malId: Int,
//            @Json(name = "type")
//            val type: String,
//            @Json(name = "name")
//            val name: String,
//            @Json(name = "url")
//            val url: String
//        )
//    }
//}