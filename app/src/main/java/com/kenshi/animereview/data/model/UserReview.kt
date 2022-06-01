package com.kenshi.animereview.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserReview(
    val reviewId: String? = null,
    val animeId: String? = null,
    val user: User? = null,
    var rating: String? = null,
    val reviewText: String? = null
) : Parcelable