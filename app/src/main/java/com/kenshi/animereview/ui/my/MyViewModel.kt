package com.kenshi.animereview.ui.my

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.kenshi.animereview.data.model.*
import com.kenshi.animereview.domain.repository.AnimeRepository
import com.kenshi.animereview.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

//TODO Firebase Repository 는 @Inject 가 아닌 Hilt Module 을 통해 주입받아야 함
@HiltViewModel
class MyViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
//    private val userRepository: UserRepository,
//    private val reviewRepository: ReviewRepository
) : ViewModel() {

    private val REVIEW_PATH = "reviews"
    private val USER_PATH = "users"
    private var db = FirebaseFirestore.getInstance()

    private val firebaseAuth: FirebaseAuth by lazy {
        Firebase.auth
    }
    private val currentUser by lazy {
        firebaseAuth.currentUser!!
    }

    private val _reviews = MutableLiveData<List<Review>>()
    val reviews: LiveData<List<Review>> = _reviews

    private val _animeInfo = MutableStateFlow(AnimeInfo())
    val animeInfo: StateFlow<AnimeInfo> = _animeInfo.asStateFlow()

//    init {
//        viewModelScope.launch {
//            fetchUserInfo(currentUser.uid)
//            _reviews.value = FirebaseProfileService.getReviews()
//        }
//        fetchUserInfo()
//    }

    val userInfo: StateFlow<UiState<User>> = fetchUserInfo()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = UiState.Loading
        )

    private fun fetchUserInfo(): Flow<UiState<User>> = flow<UiState<User>> {
        val userRef = db.collection(USER_PATH).document(currentUser.uid)
        val userInfo = userRef.get().await().toObject<User>()!!
        Timber.tag("fetch Success").d("$userInfo")
        emit(UiState.Success(userInfo))
    }

    // 이런식으로 반복되는거 보면 UseCase 의 필요성이 느껴지긴 한다.
    val reviewList: StateFlow<UiState<List<AnimeReview>>> = fetchReviewList()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = UiState.Loading
        )

    //전체 리뷰 중에 로그인한 유저가 작성한 리뷰 가져오기
    private fun fetchReviewList(): Flow<UiState<List<AnimeReview>>> =
        flow<UiState<List<AnimeReview>>> {
            emit(UiState.Loading)
            val reviewRef = db.collection(REVIEW_PATH)
            val userRef = db.collection(USER_PATH)
            val animeReviewList = mutableListOf<AnimeReview>()
            val reviewList =
                reviewRef.whereEqualTo("userId", currentUser.uid).get().await().toObjects<Review>()
            for (review in reviewList) {
                val userInfo =
                    userRef.whereEqualTo("userId", review.userId).get().await().toObjects<User>()
//TODO AnimeTitle 가져오기
//                val anime =
//                   animeRepository.fetchAnimeById(review.animeId!!)
//                    animeReviewList.add(UserReview(
//                        animeTitle = anime,
//                        reviewId = review.reviewId,
//                        animeId = review.animeId,
//                        user = userInfo[0],
//                        rating = review.rating,
//                        reviewText = review.reviewText
//                    ))
                animeReviewList.add(AnimeReview(
                    reviewId = review.reviewId,
                    animeId = review.animeId,
                    user = userInfo[0],
                    rating = review.rating,
                    reviewText = review.reviewText
                ))
            }
            Timber.tag("my review fetch Success").d("$reviewList")
            Timber.tag("my review fetch Success").d("$animeReviewList")

            emit(UiState.Success(animeReviewList))
        }
}