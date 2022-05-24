package com.kenshi.animereview.ui.anime_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.data.model.AnimeReview
import com.kenshi.animereview.ui.common.Event

class AnimeDetailViewModel : ViewModel() {

    private val _openRegisterReviewEvent = MutableLiveData<Event<AnimeReview>>()
    val openRegisterReviewEvent: LiveData<Event<AnimeReview>> = _openRegisterReviewEvent

    fun openRegisterReview(anime: AnimeReview) {
        _openRegisterReviewEvent.value = Event(anime)
    }

    //TODO 파이어베이스에 등록된 해당 애니메이션의 리뷰 가져오기
}