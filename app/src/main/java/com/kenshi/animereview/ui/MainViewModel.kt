package com.kenshi.animereview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.data.model.Review
import com.kenshi.animereview.data.model.Review.Companion.EMPTY
import com.kenshi.animereview.domain.repository.ReviewRepository
import com.kenshi.animereview.ui.common.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject

//class MainViewModel @Inject constructor(
//    private val reviewRepository: ReviewRepository
class MainViewModel : ViewModel() {

    private val _animeInfo = MutableStateFlow(AnimeInfo())
    val animeInfo: StateFlow<AnimeInfo> = _animeInfo.asStateFlow()

    private val _review = MutableStateFlow<Review>(Review())
    val review: StateFlow<Review> = _review.asStateFlow()

    private val REVIEW_PATH = "reviews"
    private var db = FirebaseFirestore.getInstance()

    private val firebaseAuth: FirebaseAuth by lazy {
        Firebase.auth
    }
    private val currentUser by lazy {
        firebaseAuth.currentUser!!
    }

    val reviewText = MutableLiveData<String>()


    fun registerReview()  {
        //_reviewText.value = inputText.value.toString()

        val ref = db.collection(REVIEW_PATH).document()
        val refId = ref.id

        val reviewInfo = Review(
            refId,
            //_animeId.value,
            _animeInfo.value.id,
            currentUser.uid,
            _rating.value.toString(),
            //_reviewText.value
            reviewText.value
        )

        ref.set(reviewInfo)
            .addOnSuccessListener {
                _review.value = reviewInfo
                Timber.d("Save Success")
            }
            .addOnFailureListener {
                Timber.d("Save Fail")
            }
    }

    private val _openRegisterReviewEvent = MutableLiveData<Event<Boolean>>()
    val openRegisterReviewEvent: LiveData<Event<Boolean>> = _openRegisterReviewEvent

    fun openRegisterReview() {
        _openRegisterReviewEvent.value = Event(true)
    }

    private val _rating = MutableStateFlow<Float>(0F)
    val rating: StateFlow<Float> = _rating.asStateFlow()

    fun setRating(rating: Float) {
        _rating.value = rating
    }

    fun setAnimeInfo(animeInfo: AnimeInfo) {
        _animeInfo.value = animeInfo
    }
}