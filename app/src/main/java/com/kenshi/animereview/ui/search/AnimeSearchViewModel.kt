package com.kenshi.animereview.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kenshi.animereview.data.model.jikan.AnimeInfo
import com.kenshi.animereview.domain.repository.AnimeRepository
import com.kenshi.animereview.ui.base.UiState
import com.kenshi.animereview.util.Constants.SAVE_STATE_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeSearchViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _searchPagingResult = MutableStateFlow<PagingData<AnimeInfo>>(PagingData.empty())
    val searchPagingResult: StateFlow<PagingData<AnimeInfo>> = _searchPagingResult.asStateFlow()

    fun searchAnime(query: String) {
        viewModelScope.launch {
            animeRepository.fetchSearchAnime(query)
                .cachedIn(viewModelScope)
                .collect {
                    _searchPagingResult.value = it
                }
        }
    }


    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private fun event(event: Event) = viewModelScope.launch {
        _eventFlow.emit(event)
    }


    fun backIconClick() = viewModelScope.launch {
        event(Event.BackIconClick)
    }

    //SaveState
    var query = String()
        set(value) {
            field = value
            savedStateHandle.set(SAVE_STATE_KEY, value)
        }

    init {
        query = savedStateHandle.get<String>(SAVE_STATE_KEY) ?: ""
    }

    sealed class Event {
        object BackIconClick : Event()
    }
}

