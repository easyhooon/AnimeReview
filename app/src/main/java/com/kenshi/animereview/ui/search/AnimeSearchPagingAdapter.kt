package com.kenshi.animereview.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.kenshi.animereview.data.model.jikan.AnimeInfo
import com.kenshi.animereview.data.model.jikan.AnimeList
import com.kenshi.animereview.databinding.ItemAnimePreviewBinding

class AnimeSearchPagingAdapter : PagingDataAdapter<AnimeInfo, AnimeSearchViewHolder>(AnimeDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeSearchViewHolder {
        return AnimeSearchViewHolder(
            ItemAnimePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AnimeSearchViewHolder, position: Int) {
        //nullable
        val pagedAnime = getItem(position)
        //null 대응
        pagedAnime?.let { book ->
            holder.bind(book)
            holder.itemView.setOnClickListener {
                onItemClickListener?.let { it(book) }
            }
        }
    }

    private var onItemClickListener: ((AnimeInfo) -> Unit)? = null
    fun setOnItemClickListener(listener: (AnimeInfo) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        private val AnimeDiffCallback = object : DiffUtil.ItemCallback<AnimeInfo>() {
            override fun areItemsTheSame(
                oldItem: AnimeInfo,
                newItem: AnimeInfo,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: AnimeInfo,
                newItem: AnimeInfo,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}