package com.kenshi.animereview.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.domain.repository.AnimeRepository
import com.kenshi.animereview.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
) : ViewModel() {
    private val firebaseAuth: FirebaseAuth by lazy {
        Firebase.auth
    }
    private val currentUser by lazy {
        firebaseAuth.currentUser!!
    }

    private val _userName = MutableStateFlow<String>(currentUser.displayName.toString())
    val userName: StateFlow<String> = _userName.asStateFlow()

    val animeListById: StateFlow<UiState<List<AnimeInfo>>> = animeRepository.fetchAnimeListById()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = UiState.Loading
        )

//    private val _searchIconClickEvent = MutableSharedFlow<Unit>()
//    val searchIconClickEvent: SharedFlow<Unit> = _searchIconClickEvent.asSharedFlow()

//    val trendingAnimeList: StateFlow<UiState<List<KitsuAnimeInfo>>> = animeRepository.fetchTrendingAnime()
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000L),
//            initialValue = UiState.Loading
//        )
//
//    val genreAnimeList: StateFlow<UiState<List<AnimeInfo>>> = animeRepository.fetchGenreAnime("action")
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000L),
//            initialValue = UiState.Loading
//        )
//
//    fun searchAnime(title: String): StateFlow<UiState<List<KitsuAnimeInfo>>> = animeRepository.fetchSearchAnime(title)
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000L),
//            initialValue = UiState.Loading
//        )

//    val trendingAnimeList: StateFlow<UiState<List<AnimeInfo>>> = animeRepository.fetchTrendingAnime()
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000L),
//            initialValue = UiState.Loading
//        )

    val genreAnimeList: StateFlow<UiState<List<AnimeInfo>>> =
        animeRepository.fetchGenreAnime("action")
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = UiState.Loading
            )

//    val mockAnimeList: MutableList<MockAnimeInfo> = mutableListOf(
//        MockAnimeInfo(
//            "Cowboy Bebop",
//            MockAnimeInfo.PosterImage(
//                "https://media.kitsu.io/anime/poster_images/1/tiny.jpg",
//                "https://media.kitsu.io/anime/poster_images/1/large.jpg",
//                "https://media.kitsu.io/anime/poster_images/1/small.jpg",
//                "https://media.kitsu.io/anime/poster_images/1/medium.jpg",
//            )
//        ),
//        MockAnimeInfo(
//            "SPY×FAMILY",
//            MockAnimeInfo.PosterImage(
//                "https://media.kitsu.io/anime/45398/poster_image/tiny-ecc79ffcea955906b4bb7a2cf71dccbc.jpeg",
//                "https://media.kitsu.io/anime/45398/poster_image/large-c26c1e0b77e881fcd66475af6bd22c57.jpeg",
//                "https://media.kitsu.io/anime/45398/poster_image/small-d2d2ddd1b7f5a9c20bbb69b2b476a0d6.jpeg",
//                "https://media.kitsu.io/anime/45398/poster_image/medium-f2f30d957a12e8269655c6036b460c90.jpeg",
//            )
//        ),
//        MockAnimeInfo(
//            "Steins;Gate",
//            MockAnimeInfo.PosterImage(
//                "https://media.kitsu.io/anime/poster_images/5646/tiny.jpg",
//                "https://media.kitsu.io/anime/poster_images/5646/large.jpg",
//                "https://media.kitsu.io/anime/poster_images/5646/small.jpg",
//                "https://media.kitsu.io/anime/poster_images/5646/medium.jpg",
//            )
//        ),
//        MockAnimeInfo(
//            "Attack on Titan",
//            MockAnimeInfo.PosterImage(
//                "https://media.kitsu.io/anime/poster_images/7442/tiny.jpg",
//                "https://media.kitsu.io/anime/poster_images/7442/large.jpg",
//                "https://media.kitsu.io/anime/poster_images/7442/small.jpg",
//                "https://media.kitsu.io/anime/poster_images/7442/medium.jpg",
//            )
//        ),
//        MockAnimeInfo(
//            "Kaguya-sama wa Kokurasetai: Ultra Romantic",
//            MockAnimeInfo.PosterImage(
//                "https://media.kitsu.io/anime/poster_images/42632/tiny.jpg",
//                "https://media.kitsu.io/anime/poster_images/42632/large.jpg",
//                "https://media.kitsu.io/anime/poster_images/42632/small.jpg",
//                "https://media.kitsu.io/anime/poster_images/42632/medium.jpg",
//            )
//        ),
//        MockAnimeInfo(
//            "Beastars 2nd Season",
//            MockAnimeInfo.PosterImage(
//                "https://media.kitsu.io/anime/poster_images/42904/tiny.jpg",
//                "https://media.kitsu.io/anime/poster_images/42904/large.jpg",
//                "https://media.kitsu.io/anime/poster_images/42904/small.jpg",
//                "https://media.kitsu.io/anime/poster_images/42904/medium.jpg",
//            )
//        ),
//        MockAnimeInfo(
//            "Kimetsu no Yaiba",
//            MockAnimeInfo.PosterImage(
//                "https://media.kitsu.io/anime/poster_images/41370/tiny.jpg",
//                "https://media.kitsu.io/anime/poster_images/41370/large.jpg",
//                "https://media.kitsu.io/anime/poster_images/41370/small.jpg",
//                "https://media.kitsu.io/anime/poster_images/41370/medium.jpg",
//            )
//        ),
//        MockAnimeInfo(
//            "Sono Bisque Doll wa Koi wo Suru",
//            MockAnimeInfo.PosterImage(
//                "https://media.kitsu.io/anime/44382/poster_image/tiny-c6eedf248b1efa3a603b82ada61acf30.jpeg",
//                "https://media.kitsu.io/anime/44382/poster_image/large-278c3c9f61f2fd4213ba5529e4655eab.jpeg",
//                "https://media.kitsu.io/anime/44382/poster_image/small-85545d20cafbe0885f9b3955da6eacb8.jpeg",
//                "https://media.kitsu.io/anime/44382/poster_image/medium-e711cf37fa55da2f9518bbb306bfcf36.jpeg",
//            )
//        ),
//        MockAnimeInfo(
//            "86",
//            MockAnimeInfo.PosterImage(
//                "https://media.kitsu.io/anime/poster_images/43066/tiny.jpg",
//                "https://media.kitsu.io/anime/poster_images/43066/large.jpg",
//                "https://media.kitsu.io/anime/poster_images/43066/small.jpg",
//                "https://media.kitsu.io/anime/poster_images/43066/medium.jpg",
//            )
//        ),
//        MockAnimeInfo(
//            "Zombieland Saga",
//            MockAnimeInfo.PosterImage(
//                "https://media.kitsu.io/anime/poster_images/41459/tiny.jpg",
//                "https://media.kitsu.io/anime/poster_images/41459/large.jpg",
//                "https://media.kitsu.io/anime/poster_images/41459/small.jpg",
//                "https://media.kitsu.io/anime/poster_images/41459/medium.jpg",
//            )
//        )
//    )

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun searchIconClick() = viewModelScope.launch {
        event(Event.SearchIconClick)
    }

    private fun event(event: Event) = viewModelScope.launch {
        _eventFlow.emit(event)
    }


    sealed class Event {
        object SearchIconClick : Event()
        //data class ShowToast(val text: String) : Event()
    }
}