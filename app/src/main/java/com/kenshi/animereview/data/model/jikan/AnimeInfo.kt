package com.kenshi.animereview.data.model.jikan

import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@JsonClass(generateAdapter = true)
data class AnimeInfo(
    @field:Json(name = "mal_id")
    val id: String? = null,
    val images: Image? = null,
    val title: String? = null,
    val type: String? = null,
    val episodes: Int? = 0,
    val status: String? = "",
    val rating: String? = "",
    val score: Float? = 0F,
    val synopsis: String? = "",
    val studios: List<Studio>? = listOf(),
    val genres: List<Genre>?= listOf(),
) : Parcelable {

    @Keep
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Image(
        val webp: Webp? = null,
    ) : Parcelable {

        @Keep
        @Parcelize
        @JsonClass(generateAdapter = true)
        data class Webp(
            @field:Json(name = "image_url")
            val imageUrl: String? = null,
            @field:Json(name = "small_image_url")
            val smallImageUrl: String? = null,
            @field:Json(name = "large_image_url")
            val largeImageUrl: String? = null,
        ) : Parcelable
    }

    @Keep
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Studio(
        val name: String? = null
    ) : Parcelable

    @Keep
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Genre(
        val name: String? = null
    ) : Parcelable

    companion object {
        val EMPTY = AnimeInfo()
    }
}