package com.kenshi.animereview.ui.my


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kenshi.animereview.R
import com.kenshi.animereview.data.model.review.AnimeReview
import com.kenshi.animereview.databinding.ItemMyReviewBinding

class MyReviewAdapter: ListAdapter<AnimeReview, MyReviewAdapter.ReviewViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemMyReviewBinding>(layoutInflater, R.layout.item_my_review, parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ReviewViewHolder(
        private val binding: ItemMyReviewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AnimeReview) {
            binding.apply {
                review = item
                executePendingBindings()
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<AnimeReview>() {

            override fun areItemsTheSame(oldItem: AnimeReview, newItem: AnimeReview): Boolean =
                oldItem.reviewId == newItem.reviewId

            override fun areContentsTheSame(oldItem: AnimeReview, newItem: AnimeReview): Boolean =
                oldItem == newItem
        }
    }
}