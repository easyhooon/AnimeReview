package com.kenshi.animereview.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.data.model.MockAnimeInfo
import com.kenshi.animereview.domain.Repository.AnimeRepository
import com.kenshi.animereview.ui.base.UiState
import com.kenshi.animereview.ui.base.successOrNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
) : ViewModel() {

    val mockAnimeList: MutableList<MockAnimeInfo> = mutableListOf(
        MockAnimeInfo(
            "Cowboy Bebop",
            MockAnimeInfo.PosterImage(
                "https://media.kitsu.io/anime/poster_images/1/tiny.jpg",
                "https://media.kitsu.io/anime/poster_images/1/large.jpg",
                "https://media.kitsu.io/anime/poster_images/1/small.jpg",
                "https://media.kitsu.io/anime/poster_images/1/medium.jpg",
            )
        ),
        MockAnimeInfo(
            "SPY×FAMILY",
            MockAnimeInfo.PosterImage(
                "https://media.kitsu.io/anime/45398/poster_image/tiny-ecc79ffcea955906b4bb7a2cf71dccbc.jpeg",
                "https://media.kitsu.io/anime/45398/poster_image/large-c26c1e0b77e881fcd66475af6bd22c57.jpeg",
                "https://media.kitsu.io/anime/45398/poster_image/small-d2d2ddd1b7f5a9c20bbb69b2b476a0d6.jpeg",
                "https://media.kitsu.io/anime/45398/poster_image/medium-f2f30d957a12e8269655c6036b460c90.jpeg",
            )
        ),
        MockAnimeInfo(
            "Steins;Gate",
            MockAnimeInfo.PosterImage(
                "https://media.kitsu.io/anime/poster_images/5646/tiny.jpg",
                "https://media.kitsu.io/anime/poster_images/5646/large.jpg",
                "https://media.kitsu.io/anime/poster_images/5646/small.jpg",
                "https://media.kitsu.io/anime/poster_images/5646/medium.jpg",
            )
        ),
        MockAnimeInfo(
            "Attack on Titan",
            MockAnimeInfo.PosterImage(
                "https://media.kitsu.io/anime/poster_images/7442/tiny.jpg",
                "https://media.kitsu.io/anime/poster_images/7442/large.jpg",
                "https://media.kitsu.io/anime/poster_images/7442/small.jpg",
                "https://media.kitsu.io/anime/poster_images/7442/medium.jpg",
            )
        ),
        MockAnimeInfo(
            "Kaguya-sama wa Kokurasetai: Ultra Romantic",
            MockAnimeInfo.PosterImage(
                "https://media.kitsu.io/anime/poster_images/42632/tiny.jpg",
                "https://media.kitsu.io/anime/poster_images/42632/large.jpg",
                "https://media.kitsu.io/anime/poster_images/42632/small.jpg",
                "https://media.kitsu.io/anime/poster_images/42632/medium.jpg",
            )
        ),
        MockAnimeInfo(
            "Beastars 2nd Season",
            MockAnimeInfo.PosterImage(
                "https://media.kitsu.io/anime/poster_images/42904/tiny.jpg",
                "https://media.kitsu.io/anime/poster_images/42904/large.jpg",
                "https://media.kitsu.io/anime/poster_images/42904/small.jpg",
                "https://media.kitsu.io/anime/poster_images/42904/medium.jpg",
            )
        ),
        MockAnimeInfo(
            "Kimetsu no Yaiba",
            MockAnimeInfo.PosterImage(
                "https://media.kitsu.io/anime/poster_images/41370/tiny.jpg",
                "https://media.kitsu.io/anime/poster_images/41370/large.jpg",
                "https://media.kitsu.io/anime/poster_images/41370/small.jpg",
                "https://media.kitsu.io/anime/poster_images/41370/medium.jpg",
            )
        ),
        MockAnimeInfo(
            "Sono Bisque Doll wa Koi wo Suru",
            MockAnimeInfo.PosterImage(
                "https://media.kitsu.io/anime/44382/poster_image/tiny-c6eedf248b1efa3a603b82ada61acf30.jpeg",
                "https://media.kitsu.io/anime/44382/poster_image/large-278c3c9f61f2fd4213ba5529e4655eab.jpeg",
                "https://media.kitsu.io/anime/44382/poster_image/small-85545d20cafbe0885f9b3955da6eacb8.jpeg",
                "https://media.kitsu.io/anime/44382/poster_image/medium-e711cf37fa55da2f9518bbb306bfcf36.jpeg",
            )
        ),
        MockAnimeInfo(
            "86",
            MockAnimeInfo.PosterImage(
                "https://media.kitsu.io/anime/poster_images/43066/tiny.jpg",
                "https://media.kitsu.io/anime/poster_images/43066/large.jpg",
                "https://media.kitsu.io/anime/poster_images/43066/small.jpg",
                "https://media.kitsu.io/anime/poster_images/43066/medium.jpg",
            )
        ),
        MockAnimeInfo(
            "Zombieland Saga",
            MockAnimeInfo.PosterImage(
                "https://media.kitsu.io/anime/poster_images/41459/tiny.jpg",
                "https://media.kitsu.io/anime/poster_images/41459/large.jpg",
                "https://media.kitsu.io/anime/poster_images/41459/small.jpg",
                "https://media.kitsu.io/anime/poster_images/41459/medium.jpg",
            )
        )
    )

    val uiState = animeRepository.fetchRecommendAnime()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = UiState.Loading
        )

//    init {
//        loadRecommendData()
//    }

//    private val _uiState = MutableLiveData<UiState<List<AnimeInfo>>>(UiState.Loading)
//    val uiState: LiveData<UiState<List<AnimeInfo>>> = _uiState

//    private val _uiState = MutableLiveData<UiState<AnimeInfo>>(UiState.Loading)
//    val uiState: LiveData<UiState<AnimeInfo>> = _uiState

//    private fun loadRecommendData() = viewModelScope.launch {
//        val animeList = animeRepository.fetchRecommendAnime()
//        _uiState.value = animeList
//    }
}