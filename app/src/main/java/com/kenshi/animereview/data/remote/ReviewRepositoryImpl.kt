package com.kenshi.animereview.data.remote

import com.kenshi.animereview.data.model.Review
import com.kenshi.animereview.data.network.service.AnimeService
import com.kenshi.animereview.data.network.service.FirebaseReviewService
import com.kenshi.animereview.data.network.service.FirebaseUserService
import com.kenshi.animereview.domain.repository.ReviewRepository
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
): ReviewRepository {
    override fun saveReview(anime: Review) {

    }

    override fun fetchReviews() {

    }
}