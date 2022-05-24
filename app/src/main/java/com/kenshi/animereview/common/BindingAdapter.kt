package com.kenshi.animereview.common

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview
import com.kenshi.animereview.GlideApp
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.data.model.MockAnimeInfo
import com.kenshi.animereview.ui.base.UiState
import com.kenshi.animereview.ui.base.successOrNull
import com.kenshi.animereview.ui.home.MockAnimeAdapter
import com.kenshi.animereview.ui.home.RecommendAnimeAdapter

@BindingAdapter("imageUrl")
fun ImageView.bindImage(imageUrl: String?) {
    if(!imageUrl.isNullOrEmpty()) {
        GlideApp.with(context)
            .load(imageUrl)
            //.centerCrop()
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

@BindingAdapter("animeAdapter")
fun CarouselRecyclerview.bindAnimeAdapter(adapter: RecyclerView.Adapter<*>) {
    this.adapter = adapter

    setInfinite(true)
    setAlpha(true)
//    setFlat(false)
    setIntervalRatio(0.5f)
    isNestedScrollingEnabled = false
}

@BindingAdapter("skinItems")
fun RecyclerView.bindRecommendAnimeList(animeList: List<AnimeInfo>?) {
    val boundAdapter = this.adapter
    if (boundAdapter is RecommendAnimeAdapter && !animeList.isNullOrEmpty()) {
        boundAdapter.submitList(animeList)
    }
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

//@BindingAdapter("tags")
//fun ChipGroup.bindTags(tags: List<String>?) {
//    tags?.forEach { tag ->
//        val tagView: Chip = Chip(context).apply {
//            text = tag
//            isCheckable = false
//            isCloseIconVisible = false
//            setChipBackgroundColorResource(R.color.purple)
//            setTextAppearanceResource(R.style.TextStyle_Tag)
//        }
//        addView(tagView)
//    }
//}

@BindingAdapter("tag")
fun Chip.bindShow(text: String) {
   visibility = if (text.isNotEmpty()) {
       this.text = text
       View.VISIBLE
   } else
       View.GONE
}

@BindingAdapter("episode")
fun Chip.countEpisode(count: String) {
    if(this.text.isNotEmpty()) {
        "$count episode".also { this.text = it }
    }
}
