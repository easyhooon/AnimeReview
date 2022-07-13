package com.kenshi.animereview.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kenshi.animereview.data.model.jikan.AnimeInfo
import com.kenshi.animereview.data.model.kitsu.KitsuAnimeInfo
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

    val animeListById: StateFlow<UiState<List<KitsuAnimeInfo>>> =
        animeRepository.fetchAnimeListById()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = UiState.Loading
            )

    val trendingAnimeList: StateFlow<UiState<List<KitsuAnimeInfo>>> =
        animeRepository.fetchTrendingAnime()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = UiState.Loading
            )

    val genreAnimeList: StateFlow<UiState<List<AnimeInfo>>> =
        animeRepository.fetchGenreAnime(GENRE_ACTION)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = UiState.Loading
            )

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

    companion object {
        const val GENRE_ACTION = "action"
        const val GENRE_ADVENTURE = "adventure"
    }
}