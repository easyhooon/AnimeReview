package com.kenshi.animereview.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review(val reviewId: String? = null,
                  val animeId: String? = null,
                  val userId: String? = null,
                  var rating: String? = null,
                  val reviewText: String? = null
) : Parcelable