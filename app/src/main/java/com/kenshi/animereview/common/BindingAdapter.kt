package com.kenshi.animereview.common

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview
import com.kenshi.animereview.GlideApp
import com.kenshi.animereview.data.model.MockAnimeInfo
import com.kenshi.animereview.data.model.jikan.AnimeInfo
import com.kenshi.animereview.data.model.kitsu.KitsuAnimeInfo
import com.kenshi.animereview.data.model.review.AnimeReview
import com.kenshi.animereview.ui.anime_review.AnimeReviewAdapter
import com.kenshi.animereview.ui.base.UiState
import com.kenshi.animereview.ui.base.successOrNull
import com.kenshi.animereview.ui.home.GenreAnimeAdapter
import com.kenshi.animereview.ui.home.RecommendAnimeAdapter
import com.kenshi.animereview.ui.my.MyReviewAdapter
import timber.log.Timber
import java.text.DecimalFormat


@BindingAdapter("imageUrl")
fun ImageView.bindImage(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        GlideApp.with(context)
            .load(imageUrl)
            .into(this)
    }
}

@BindingAdapter("adapter")
fun RecyclerView.bindAdapter(adapter: RecyclerView.Adapter<*>) {
    this.adapter = adapter
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

@BindingAdapter("animeList")
fun RecyclerView.bindAnimeList(animeList: UiState<List<KitsuAnimeInfo>>) {
    val boundAdapter = this.adapter
    if (boundAdapter is RecommendAnimeAdapter) {
        boundAdapter.submitList(animeList.successOrNull())
        Timber.d("RecommendAnimeAdapter: ${animeList.successOrNull()}")
    }
}

@BindingAdapter("genreAnimeList")
fun RecyclerView.bindGenreAnimeList(animeList: UiState<List<AnimeInfo>>) {
    val boundAdapter = this.adapter
    if (boundAdapter is GenreAnimeAdapter) {
        boundAdapter.submitList(animeList.successOrNull())
        Timber.d("GenreAnimeAdapter: ${animeList.successOrNull()}")
    }
}

//@BindingAdapter("reviewList")
//fun RecyclerView.bindReviewList(reviewList: UiState<List<Review>>) {
//    val boundAdapter = this.adapter
//    if (boundAdapter is AnimeReviewAdapter) {
//        boundAdapter.submitList(reviewList.successOrNull())
//    }
//}

@BindingAdapter("animeReviewList")
fun RecyclerView.bindAnimeReviewList(animeReviewList: UiState<List<AnimeReview>>) {
    val boundAdapter = this.adapter
    if (boundAdapter is AnimeReviewAdapter) {
        boundAdapter.submitList(animeReviewList.successOrNull())
    }
}

@BindingAdapter("myReviewList")
fun RecyclerView.bindMyReviewList(animeReviewList: UiState<List<AnimeReview>>) {
    val boundAdapter = this.adapter
    if (boundAdapter is MyReviewAdapter) {
        boundAdapter.submitList(animeReviewList.successOrNull())
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

@BindingAdapter("rating")
fun TextView.bindText(rating: String?) {
    if (!rating.isNullOrEmpty()) {
        val df = DecimalFormat("##0.00")
        val starRating = rating.toFloat().div(20f)
        this.text = df.format(starRating)
    }
}


@BindingAdapter("tag")
fun Chip.bindShow(text: String) {
    visibility = if (text.isNotEmpty()) {
        this.text = text
        View.VISIBLE
    } else
        View.GONE
}

@BindingAdapter("episode")
fun Chip.countEpisode(count: String?) {
    if (!count.isNullOrEmpty()) {
        "$count episode".also { this.text = it }
    }
}