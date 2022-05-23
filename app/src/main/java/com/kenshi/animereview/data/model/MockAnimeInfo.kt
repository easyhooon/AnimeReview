package com.kenshi.animereview.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MockAnimeInfo(
    val canonicalTitle: String?,
    val posterImage: PosterImage?,
    ): Parcelable {
    @Parcelize
    data class PosterImage(
        val tiny: String?,
        val large: String?,
        val small: String?,
        val medium: String?,
    ): Parcelable
}
