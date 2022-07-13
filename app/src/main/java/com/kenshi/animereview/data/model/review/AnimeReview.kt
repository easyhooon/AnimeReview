package com.kenshi.animereview.data.model.review

import android.os.Parcelable
import com.kenshi.animereview.data.model.user.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimeReview(
    val reviewId: String? = null,
    val animeId: String? = null,
    val user: User? = null,
    var rating: String? = null,
    val reviewText: String? = null
) : Parcelable