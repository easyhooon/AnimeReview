package com.kenshi.animereview.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kenshi.animereview.R
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.databinding.ItemRecommendAnimeBinding

class RecommendAnimeAdapter(
    private val whenItemClicked: (AnimeInfo) -> Unit
) : ListAdapter<AnimeInfo, RecommendAnimeAdapter.RecommendAnimeViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendAnimeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemRecommendAnimeBinding>(layoutInflater, R.layout.item_recommend_anime, parent, false)
        return RecommendAnimeViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener

                whenItemClicked(
                    getItem(position)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecommendAnimeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RecommendAnimeViewHolder(
        private val binding: ItemRecommendAnimeBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AnimeInfo) {
            binding.apply {
                anime = item
                executePendingBindings()
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<AnimeInfo>() {
            override fun areItemsTheSame(oldItem: AnimeInfo, newItem: AnimeInfo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem:AnimeInfo, newItem: AnimeInfo): Boolean =
                oldItem == newItem
        }
    }
}