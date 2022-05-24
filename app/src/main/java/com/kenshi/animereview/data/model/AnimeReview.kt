package com.kenshi.animereview.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class AnimeReview(
    val anime: AnimeInfo,
    var rating: Int,
    val review: String?
) : Parcelable