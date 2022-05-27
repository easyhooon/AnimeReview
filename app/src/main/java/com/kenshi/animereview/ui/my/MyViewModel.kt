package com.kenshi.animereview.ui.my

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.data.model.Review
import com.kenshi.animereview.data.model.User
import com.kenshi.animereview.exceptions.EmptyBodyException
import com.kenshi.animereview.exceptions.NetworkFailureException
import com.kenshi.animereview.ui.base.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import timber.log.Timber

//@HiltViewModel
//class MyViewModel @Inject constructor(
class MyViewModel (
//    private val userRepository: UserRepository,
//    private val reviewRepository: ReviewRepository
):ViewModel() {
    private val USER_PATH = "users"
    private var db = FirebaseFirestore.getInstance()

    private val firebaseAuth: FirebaseAuth by lazy {
        Firebase.auth
    }
    private val currentUser by lazy {
        firebaseAuth.currentUser!!
    }

    //초기값에 의해 화면이 빈 칸으로만 채워지는 현상
    private val _user = MutableStateFlow<User>(User())
    val user:StateFlow<User> = _user.asStateFlow()

    private val _reviews = MutableLiveData<List<Review>>()
    val reviews: LiveData<List<Review>> = _reviews

/*    init {
//        viewModelScope.launch {
//            fetchUserInfo(currentUser.uid)
//            _reviews.value = FirebaseProfileService.getReviews()
//        }

        fetchUserInfo()
    }*/

    val userInfo: StateFlow<UiState<User>> = fetchUserInfo()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = UiState.Loading
        )

//    fun fetchUserInfo(): Flow<UiState<User>> = flow<UiState<User>> {
//        emit(UiState.Loading)
//        val userRef = db.collection(USER_PATH).document(currentUser.uid)
//        userRef.get()
//            .addOnSuccessListener { documentSnapshot ->
//                _user.value = documentSnapshot.toObject<User>()!!
//                Timber.tag("fetch Success").d("${_user.value}")
//            }
//            .addOnFailureListener {
//                Timber.d("fetch Fail")
//            }
//    }

    fun fetchUserInfo(): Flow<UiState<User>> = flow<UiState<User>> {
        emit(UiState.Loading)
        val userRef = db.collection(USER_PATH).document(currentUser.uid)
        val userInfo = userRef.get().await().toObject<User>()!!
//        _user.value = userInfo
        Timber.tag("fetch Success").d("${userInfo}")
        emit(UiState.Success(userInfo))
    }
}

//    override fun fetchAnimeById(id: String): Flow<UiState<AnimeInfo>> = flow<UiState<AnimeInfo>> {
//        val response = animeService.fetchAnimeById(id)
//        if (response.isSuccessful) {
//            Timber.tag("response.body()").d("${response.body()}")
//            val anime: AnimeInfo =
//                response.body()?.Anime
//                    ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")
//            Timber.tag("anime").d("$anime")
//            emit(UiState.Success(anime))
//        } else {
//            Timber.tag("request fail").d("${response.errorBody()}")
//            throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
//        }
//    }.catch { emit(UiState.Error(it)) }
//}