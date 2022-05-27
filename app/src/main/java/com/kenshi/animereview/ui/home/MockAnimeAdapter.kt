package com.kenshi.animereview.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kenshi.animereview.R
import com.kenshi.animereview.data.model.MockAnimeInfo
import com.kenshi.animereview.databinding.ItemMockAnimeBinding

class MockAnimeAdapter(
    private val whenItemClicked: (MockAnimeInfo) -> Unit
) : ListAdapter<MockAnimeInfo, MockAnimeAdapter.MockAnimeViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MockAnimeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemMockAnimeBinding>(layoutInflater, R.layout.item_mock_anime, parent, false)
        return MockAnimeViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener

                whenItemClicked(
                    getItem(position)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: MockAnimeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MockAnimeViewHolder(
        private val binding: ItemMockAnimeBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MockAnimeInfo) {
            binding.apply {
                mockAnime = item
                executePendingBindings()
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MockAnimeInfo>() {
            override fun areItemsTheSame(oldItem: MockAnimeInfo, newItem: MockAnimeInfo): Boolean =
                oldItem.canonicalTitle == newItem.canonicalTitle

            override fun areContentsTheSame(oldItem: MockAnimeInfo, newItem: MockAnimeInfo): Boolean =
                oldItem == newItem
        }
    }
}