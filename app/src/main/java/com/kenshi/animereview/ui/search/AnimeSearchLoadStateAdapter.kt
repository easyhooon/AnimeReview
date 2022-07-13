package com.kenshi.animereview.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.kenshi.animereview.databinding.ItemLoadStateBinding

class AnimeSearchLoadStateAdapter(
    private val retry: () -> Unit,
) : LoadStateAdapter<AnimeSearchLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState,
    ): AnimeSearchLoadStateViewHolder {
        return AnimeSearchLoadStateViewHolder(
            ItemLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            retry
        )
    }

    override fun onBindViewHolder(holder: AnimeSearchLoadStateViewHolder, loadState: LoadState) {
        //binding 시 loadState 를 전달
        holder.bind(loadState)
    }
}