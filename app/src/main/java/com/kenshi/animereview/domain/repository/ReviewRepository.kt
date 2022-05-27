package com.kenshi.animereview.domain.repository

import com.kenshi.animereview.data.model.Review

interface ReviewRepository {

    fun saveReview(anime: Review)

    fun fetchReviews()
}