package com.kenshi.animereview.data.model.kitsu

import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@JsonClass(generateAdapter = true)
data class KitsuAnimeInfo(
    val id: String = "",
    val attributes: Attributes? = null,
) : Parcelable {

    @Keep
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

        @Keep
        @Parcelize
        @JsonClass(generateAdapter = true)
        data class CoverImage(
            val large: String?,
            val original: String?,
            val small: String?,
            val tiny: String?,
        ) : Parcelable

        @Keep
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
        val EMPTY = KitsuAnimeInfo()
    }
}