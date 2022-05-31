package com.kenshi.animereview.ui

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
import com.kenshi.animereview.ui.base.UiState
import com.kenshi.animereview.ui.common.Event
import com.kenshi.animereview.ui.common.hideKeyboard
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import timber.log.Timber

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

    val reviewList: StateFlow<UiState<List<Review>>> = fetchReviewList()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = UiState.Loading
        )

    //TODO 수정 해야 함
    private fun fetchReviewList(): Flow<UiState<List<Review>>> = flow<UiState<List<Review>>> {
        emit(UiState.Loading)
        val reviewRef = db.collection(REVIEW_PATH)
        val reviewList =
            // query 가 잘못된거같기도
            // 초기값으로 null 값을 받아서 못받았다가 후에 변화된 값이 mapping 되는거 같음
            // 것도 그런게 home -> detail로 넘어올때는 bundle 로 넘어오지 animeInfo 를 set 하지 않음
            // 왜? 같은 뷰모델이 아니니깐
            reviewRef.whereEqualTo("animeId", animeInfo.value.id).get().await().toObjects<Review>()
        Timber.tag("fetch Success").d("$reviewList")
        emit(UiState.Success(reviewList))
    }

    fun registerReview() {
        val ref = db.collection(REVIEW_PATH).document()
        val refId = ref.id

        val reviewInfo = Review(
            refId,
            _animeInfo.value.id,
            User(
                userId = currentUser.uid,
                name = currentUser.displayName,
                email = currentUser.email,
                profileImageUrl = currentUser.photoUrl.toString()),
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

    private val _openRegisterReviewEvent = MutableLiveData<Event<Boolean>>()
    val openRegisterReviewEvent: LiveData<Event<Boolean>> = _openRegisterReviewEvent

    private val _returnHomeEvent = MutableLiveData<Event<Boolean>>()
    val returnHomeEvent: LiveData<Event<Boolean>> = _returnHomeEvent

    fun openRegisterReview() {
        _openRegisterReviewEvent.value = Event(true)
    }

    fun returnHome() {
        _returnHomeEvent.value = Event(true)
    }

    private val _rating = MutableStateFlow<Float>(0F)
    val rating: StateFlow<Float> = _rating.asStateFlow()

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

