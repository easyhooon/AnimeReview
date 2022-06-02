package com.kenshi.animereview.ui.anime_review

import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.data.model.Review
import com.kenshi.animereview.data.model.User
import com.kenshi.animereview.data.model.UserReview
import com.kenshi.animereview.ui.base.UiState
import com.kenshi.animereview.ui.common.Event
import com.kenshi.animereview.ui.common.hideKeyboard
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class AnimeReviewViewModel: ViewModel() {

    private val REVIEW_PATH = "reviews"
    private val USER_PATH = "users"
    private var db = FirebaseFirestore.getInstance()

    private val firebaseAuth: FirebaseAuth by lazy {
        Firebase.auth
    }
    private val currentUser by lazy {
        firebaseAuth.currentUser!!
    }


    private val _animeInfo = MutableStateFlow(AnimeInfo())
    val animeInfo: StateFlow<AnimeInfo> = _animeInfo.asStateFlow()

    private val _review = MutableStateFlow<Review>(Review())
    val review: StateFlow<Review> = _review.asStateFlow()

    val reviewText = MutableLiveData<String>()

    private val _openRegisterReviewEvent = MutableLiveData<Event<Boolean>>()
    val openRegisterReviewEvent: LiveData<Event<Boolean>> = _openRegisterReviewEvent

    private val _returnHomeEvent = MutableLiveData<Event<Boolean>>()
    val returnHomeEvent: LiveData<Event<Boolean>> = _returnHomeEvent

    private val _rating = MutableStateFlow<Float>(0F)
    val rating: StateFlow<Float> = _rating.asStateFlow()

    fun resetReviewText() {
        reviewText.value = ""
    }

    val reviewList: StateFlow<UiState<List<UserReview>>> = fetchReviewList()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = UiState.Loading
        )

    private fun fetchReviewList(): Flow<UiState<List<UserReview>>> = flow<UiState<List<UserReview>>> {
        emit(UiState.Loading)
        val reviewRef = db.collection(REVIEW_PATH)
        val userRef = db.collection(USER_PATH)
        val userReviewList = mutableListOf<UserReview>()
        val reviewList =
            reviewRef.whereEqualTo("animeId", animeInfo.value.id).get().await().toObjects<Review>()
        for(review in reviewList) {
            val userInfo
                    = userRef.whereEqualTo("userId", review.userId).get().await().toObjects<User>()
            userReviewList.add(UserReview(
                reviewId = review.reviewId,
                animeId = review.animeId,
                user = userInfo[0],
                rating = review.rating,
                reviewText = review.reviewText
            ))
        }
        Timber.tag("fetch Success").d("$reviewList")
        Timber.tag("fetch Success").d("$userReviewList")
        //emit(UiState.Success(reviewList))
        emit(UiState.Success(userReviewList))
    }

    fun registerReview() {
        val ref = db.collection(REVIEW_PATH).document()
        val refId = ref.id

        val reviewInfo = Review(
            refId,
            _animeInfo.value.id,
            currentUser.uid,
            _rating.value.toString(),
            reviewText.value
        )

        ref.set(reviewInfo)
            .addOnSuccessListener {
                _review.value = reviewInfo
                Timber.d("Save Success")
                returnHome()
            }
            .addOnFailureListener {
                Timber.d("Save Fail")
            }
    }

    fun openRegisterReview() {
        _openRegisterReviewEvent.value = Event(true)
    }

    private fun returnHome() {
        _returnHomeEvent.value = Event(true)
    }

    fun setRating(rating: Float) {
        _rating.value = rating
    }

    fun setAnimeInfo(animeInfo: AnimeInfo) {
        _animeInfo.value = animeInfo
    }

    //bindingAdapter 로 해결한것은 아니지만 우선 기능 구현
    fun onDoneClicked(view: View, actionId: Int, event: KeyEvent?): Boolean {
        if(actionId == EditorInfo.IME_ACTION_DONE) {
            view.hideKeyboard()
            return true
        }
        return false
    }
}