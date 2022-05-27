package com.kenshi.animereview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenshi.animereview.data.model.Review
import com.kenshi.animereview.data.model.Review.Companion.EMPTY
import com.kenshi.animereview.domain.repository.ReviewRepository
import com.kenshi.animereview.ui.common.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository
): ViewModel() {

    // UiState 필요 이유(초기값을 통한 상태 처리)
//    private val _review = MutableStateFlow<Review>(EMPTY)
//    val review: StateFlow<Review> = _review

    private val _review = MutableLiveData<Review>()
    val review: LiveData<Review> = _review

//    fun setReview(review: Review) = with(review) {
//        _review.value = Review(
//            reviewId,
//            userId,
//            rating,
//            reviewText
//        )
//    }

//    private val _openRegisterReviewEvent = MutableLiveData<Event<Review>>()
//    val openRegisterReviewEvent: LiveData<Event<Review>> = _openRegisterReviewEvent
//
//    fun openRegisterReview(anime: Review) {
//        _openRegisterReviewEvent.value = Event(anime)
//    }

    private val _openRegisterReviewEvent = MutableLiveData<Event<Boolean>>()
    val openRegisterReviewEvent: LiveData<Event<Boolean>> = _openRegisterReviewEvent

    fun openRegisterReview() {
        _openRegisterReviewEvent.value = Event(true)
    }

    private val _rating = MutableStateFlow<Float>(0F)
    val rating: StateFlow<Float> = _rating

    fun setRating(rating: Float) {
        _rating.value = rating
    }
}