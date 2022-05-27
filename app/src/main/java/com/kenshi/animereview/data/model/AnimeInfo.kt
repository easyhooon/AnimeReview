package com.kenshi.animereview.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

//@JsonClass(generateAdapter = true)
//data class AnimeInfo(
//    @field:Json(name = "id")
//    val id: String = "",
//    @field:Json(name = "attributes")
//    val attributes: Attributes? = null,
//) {
//    @JsonClass(generateAdapter = true)
//    data class Attributes(
//        @field:Json(name = "ageRatingGuide")
//        val ageRatingGuide: String?,
//        @field:Json(name = "averageRating")
//        val averageRating: String?,
//        @field:Json(name = "canonicalTitle")
//        val canonicalTitle: String?,
//        @field:Json(name = "coverImage")
//        val coverImage: CoverImage?,
//        @field:Json(name = "episodeCount")
//        val episodeCount: Int?,
//        @field:Json(name = "posterImage")
//        val posterImage: PosterImage?,
//        @field:Json(name = "synopsis")
//        val synopsis: String?,
//        @field:Json(name = "episodeLength")
//        val episodeLength: Int?,
//        @field:Json(name = "showType")
//        val showType: String?,
//    ) {
//
//        @JsonClass(generateAdapter = true)
//        data class CoverImage(
//            @field:Json(name = "large")
//            val large: String?,
//            @field:Json(name = "original")
//            val original: String?,
//            @field:Json(name = "small")
//            val small: String?,
//            @field:Json(name = "tiny")
//            val tiny: String?,
//        )
//
//        @JsonClass(generateAdapter = true)
//        data class PosterImage(
//            @field:Json(name = "large")
//            val large: String?,
//            @field:Json(name = "medium")
//            val medium: String?,
//            @field:Json(name = "original")
//            val original: String?,
//            @field:Json(name = "small")
//            val small: String?,
//            @field:Json(name = "tiny")
//            val tiny: String?,
//        )
//    }
//
//    companion object {
//        val EMPTY = AnimeInfo()
//    }
//}

@Parcelize
data class AnimeInfo(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("attributes")
    val attributes: Attributes? = null,
) : Parcelable {

    @Parcelize
    data class Attributes(
        @SerializedName("ageRatingGuide")
        val ageRatingGuide: String?,
        @SerializedName("averageRating")
        val averageRating: String?,
        @SerializedName("canonicalTitle")
        val canonicalTitle: String?,
        @SerializedName("coverImage")
        val coverImage: CoverImage?,
        @SerializedName("episodeCount")
        val episodeCount: Int?,
        @SerializedName("posterImage")
        val posterImage: PosterImage?,
        @SerializedName("synopsis")
        val synopsis: String?,
        @SerializedName("showType")
        val showType: String?,
        @SerializedName("status")
        val status: String?,
    ) : Parcelable {

        @Parcelize
        data class CoverImage(
            @SerializedName("large")
            val large: String?,
            @SerializedName("small")
            val small: String?,
        ) : Parcelable

        @Parcelize
        data class PosterImage(
            @SerializedName("large")
            val large: String?,
            @SerializedName("small")
            val small: String?,
            @SerializedName("medium")
            val medium: String?,
            @SerializedName("original")
            val original: String?
        ) : Parcelable
    }

    companion object {
        val EMPTY = AnimeInfo()
    }
}