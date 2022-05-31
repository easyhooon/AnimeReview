package com.kenshi.animereview.ui.my


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kenshi.animereview.R
import com.kenshi.animereview.data.model.Review
import com.kenshi.animereview.databinding.ItemAnimeReviewBinding

class MyReviewAdapter(
    private val whenItemClicked: (Review) -> Unit
) : ListAdapter<Review, MyReviewAdapter.ReviewViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemAnimeReviewBinding>(layoutInflater, R.layout.item_recommend_anime, parent, false)
        return ReviewViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener

                whenItemClicked(
                    getItem(position)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ReviewViewHolder(
        private val binding: ItemAnimeReviewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Review) {
            binding.apply {
                review = item
                executePendingBindings()
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Review>() {
            
            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean =
                oldItem.reviewId == newItem.reviewId

            override fun areContentsTheSame(oldItem:Review, newItem: Review): Boolean =
                oldItem == newItem
        }
    }
}