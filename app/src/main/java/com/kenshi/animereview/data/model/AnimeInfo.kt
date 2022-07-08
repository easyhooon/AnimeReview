package com.kenshi.animereview.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
data class AnimeInfo(
    val id: String = "",
    val attributes: Attributes? = null,
) : Parcelable {

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Attributes(
        val ageRatingGuide: String?,
        val averageRating: String?,
        val canonicalTitle: String?,
        val coverImage: CoverImage?,
        val episodeCount: Int?,
        val posterImage: PosterImage?,
        val synopsis: String?,
        val episodeLength: Int?,
        val showType: String?,
        val status: String?,
    ) : Parcelable {

        @Parcelize
        @JsonClass(generateAdapter = true)
        data class CoverImage(
            val large: String?,
            val original: String?,
            val small: String?,
            val tiny: String?,
        ) : Parcelable

        @Parcelize
        @JsonClass(generateAdapter = true)
        data class PosterImage(
            val large: String?,
            val medium: String?,
            val original: String?,
            val small: String?,
            val tiny: String?,
        ) : Parcelable
    }

    companion object {
        val EMPTY = AnimeInfo()
    }
}

//@Parcelize
//data class AnimeInfo(
//    @SerializedName("id")
//    val id: String? = null,
//    @SerializedName("attributes")
//    val attributes: Attributes? = null,
//) : Parcelable {
//
//    @Parcelize
//    data class Attributes(
//        @SerializedName("ageRatingGuide")
//        val ageRatingGuide: String?,
//        @SerializedName("averageRating")
//        val averageRating: String?,
//        @SerializedName("canonicalTitle")
//        val canonicalTitle: String?,
//        @SerializedName("coverImage")
//        val coverImage: CoverImage?,
//        @SerializedName("episodeCount")
//        val episodeCount: Int?,
//        @SerializedName("posterImage")
//        val posterImage: PosterImage?,
//        @SerializedName("synopsis")
//        val synopsis: String?,
//        @SerializedName("showType")
//        val showType: String?,
//        @SerializedName("status")
//        val status: String?,
//    ) : Parcelable {
//
//        @Parcelize
//        data class CoverImage(
//            @SerializedName("large")
//            val large: String?,
//            @SerializedName("small")
//            val small: String?,
//        ) : Parcelable
//
//        @Parcelize
//        data class PosterImage(
//            @SerializedName("large")
//            val large: String?,
//            @SerializedName("small")
//            val small: String?,
//            @SerializedName("medium")
//            val medium: String?,
//            @SerializedName("original")
//            val original: String?
//        ) : Parcelable
//    }
//
//    companion object {
//        val EMPTY = AnimeInfo()
//    }
//}