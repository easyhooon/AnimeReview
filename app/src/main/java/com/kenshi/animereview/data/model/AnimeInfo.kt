package com.kenshi.animereview.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

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
) : Parcelable {

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Image(
        val webp: Webp? = null,
    ) : Parcelable {

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

    companion object {
        val EMPTY = AnimeInfo()
    }
}

//@Parcelize
//data class JikanAnimeInfo(
//    @SerializedName("mal_id")
//    val id: String? = null,
//    @SerializedName("images")
//    val images: Image? = null,
//    @SerializedName("title")
//    val title: String? = null,
//    @SerializedName("type")
//    val type: String? = null,
//    @SerializedName("episodes")
//    val episodes: Int? = 0,
//    @SerializedName("status")
//    val status: String? = "",
//    @SerializedName("rating")
//    val rating: String? = "",
//    @SerializedName("score")
//    val score: Float? = 0F,
//    @SerializedName("synopsis")
//    val synopsis: String? = ""
//) : Parcelable {
//
//    @Parcelize
//    data class Image(
//        @SerializedName("webp")
//        val webp: Webp? = null
//    ) : Parcelable {
//
//       @Parcelize
//       data class Webp (
//           @SerializedName("image_url")
//           val imageUrl: String? = null,
//           @SerializedName("small_image_url")
//           val smallImageUrl: String? = null,
//           @SerializedName("large_image_url")
//           val largeImageUrl: String? = null,
//       ) : Parcelable
//    }
//
//    companion object {
//        val EMPTY = JikanAnimeInfo()
//    }
//}