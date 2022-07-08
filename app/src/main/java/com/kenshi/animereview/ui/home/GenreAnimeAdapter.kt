package com.kenshi.animereview.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kenshi.animereview.R
import com.kenshi.animereview.data.model.JikanAnimeInfo
import com.kenshi.animereview.databinding.ItemGenreAnimeBinding
import com.kenshi.animereview.databinding.ItemRecommendAnimeBinding

class GenreAnimeAdapter(
    private val whenItemClicked: (JikanAnimeInfo) -> Unit
) : ListAdapter<JikanAnimeInfo, GenreAnimeAdapter.GenreAnimeViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreAnimeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemGenreAnimeBinding>(layoutInflater, R.layout.item_genre_anime, parent, false)
        return GenreAnimeViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener

                whenItemClicked(
                    getItem(position)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: GenreAnimeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GenreAnimeViewHolder(
        private val binding: ItemGenreAnimeBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: JikanAnimeInfo) {
            binding.apply {
                anime = item
                executePendingBindings()
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<JikanAnimeInfo>() {
            override fun areItemsTheSame(oldItem: JikanAnimeInfo, newItem: JikanAnimeInfo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: JikanAnimeInfo, newItem: JikanAnimeInfo): Boolean =
                oldItem == newItem
        }
    }
}