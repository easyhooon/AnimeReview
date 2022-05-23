package com.kenshi.animereview.common

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview
import com.kenshi.animereview.GlideApp
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.data.model.MockAnimeInfo
import com.kenshi.animereview.ui.base.UiState
import com.kenshi.animereview.ui.base.successOrNull
import com.kenshi.animereview.ui.home.MockAnimeAdapter
import com.kenshi.animereview.ui.home.RecommendAnimeAdapter

@BindingAdapter("imageUrl")
fun AppCompatImageView.loadImage(imageUrl: String?) {
    if(!imageUrl.isNullOrEmpty()) {
        GlideApp.with(context)
            .load(imageUrl)
            .centerCrop()
            .into(this)
    }
}

@BindingAdapter("adapter")
fun RecyclerView.bindAdapter(adapter: RecyclerView.Adapter<*>) {
    this.adapter = adapter
}

@BindingAdapter("animeList")
fun RecyclerView.bindAnimeList(animeList: UiState<List<AnimeInfo>>) {
    val boundAdapter = this.adapter
    if (boundAdapter is RecommendAnimeAdapter) {
        boundAdapter.submitList(animeList.successOrNull())
    }
}

@BindingAdapter("mockAnimeList")
fun RecyclerView.bindMockAnimeList(animeList: MutableList<MockAnimeInfo>) {
    val boundAdapter = this.adapter
    if (boundAdapter is MockAnimeAdapter) {
        boundAdapter.submitList(animeList)
    }
}

@BindingAdapter("itemDecoration")
fun RecyclerView.bindItemDecoration(itemDecoration: RecyclerView.ItemDecoration) {
    if (itemDecorationCount == 0) {
        addItemDecoration(itemDecoration)
    }
}

@BindingAdapter("animeAdapter")
fun CarouselRecyclerview.bindAnimeAdapter(adapter: RecyclerView.Adapter<*>) {
    this.adapter = adapter

    setInfinite(true)
    setAlpha(true)
//    setFlat(false)
    setIntervalRatio(0.5f)
    isNestedScrollingEnabled = false
}

@BindingAdapter("show")
fun ProgressBar.bindShow(uiState: UiState<*>) {
    visibility = if (uiState is UiState.Loading) View.VISIBLE else View.GONE
}

@BindingAdapter("toast")
fun View.bindToast(throwable: Throwable?) {
    throwable?.message?.let { errorMessage ->
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}

//TODO 평점 카운트 ~8.9점

//TODO 화수 카운트 ~화

//TODO 연령 제한 ~세 이상
