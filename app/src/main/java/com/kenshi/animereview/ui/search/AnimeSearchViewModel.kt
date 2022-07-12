package com.kenshi.animereview.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.domain.repository.AnimeRepository
import com.kenshi.animereview.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeSearchViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
) : ViewModel() {

    fun searchAnime(title: String): StateFlow<UiState<List<AnimeInfo>>> =
        animeRepository.fetchSearchAnime(title)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = UiState.Loading
            )


    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private fun event(event: Event) = viewModelScope.launch {
        _eventFlow.emit(event)
    }


    fun backIconClick() = viewModelScope.launch {
        event(Event.BackIconClick)
    }

    sealed class Event {
        object BackIconClick : Event()
    }
}

