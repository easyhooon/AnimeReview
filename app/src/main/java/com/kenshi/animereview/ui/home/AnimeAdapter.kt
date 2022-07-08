package com.kenshi.animereview.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kenshi.animereview.R
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.data.model.JikanAnimeInfo
import com.kenshi.animereview.databinding.ItemGenreAnimeBinding
import com.kenshi.animereview.databinding.ItemRecommendAnimeBinding

////TODO 하나의 어댑터를 통해 viewType 을 나눠 관리
////Model을 어쨌든간에 통일해야지 가능한 방법
//class AnimeAdapter(
//    private val bodyType: BodyType,
//    private val whenItemClicked: (JikanAnimeInfo) -> Unit
//) : ListAdapter<AnimeInfo, AnimeAdapter.AnimeViewHolder>(diffCallback) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeAdapter.AnimeViewHolder {
////        val layoutInflater = LayoutInflater.from(parent.context)
////        val binding = DataBindingUtil.inflate<ItemGenreAnimeBinding>(layoutInflater, R.layout.item_genre_anime, parent, false)
////        return AnimeAdapter.AnimeViewHolder(binding).apply {
////            binding.root.setOnClickListener {
////                // 이 코드의 의미
////                val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener
////
////                whenItemClicked(
////                    getItem(position)
////                )
////            }
////        }
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding = DataBindingUtil.
//        val view = when(bodyType) {
//            BodyType.RECOMMEND -> {
////                LayoutInflater.from(parent.context)
////                    .inflate(R.layout.item_recommend_anime, parent, false)
//                val binding = DataBindingUtil.inflate<ItemRecommendAnimeBinding>(layoutInflater, R.layout.item_genre_anime, parent, false)
//
//            }
//            BodyType.TRENDING -> {
//                val binding = DataBindingUtil.inflate<ItemRecommendAnimeBinding>(layoutInflater, R.layout.item_genre_anime, parent, false)
//            }
//
//            BodyType.ACTION_GENRE -> {
//                val binding = DataBindingUtil.inflate<ItemGenreAnimeBinding>(layoutInflater, R.layout.item_genre_anime, parent, false)
//            }
//        }
//
//        return AnimeViewHolder(bodyType, binding)
//    }
//
//    override fun onBindViewHolder(holder: AnimeAdapter.AnimeViewHolder, position: Int) {
//        holder.bind(getItem(position))
//    }
//
//    class AnimeViewHolder(
//        private val bodyType: BodyType,
//        private val binding: ItemGenreAnimeBinding,
//    ) : RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(item: AnimeInfo) {
//            binding.apply {
//                anime = item
//                executePendingBindings()
//            }
//        }
//    }
//
//    companion object {
//        private val diffCallback = object : DiffUtil.ItemCallback<AnimeInfo>() {
//            override fun areItemsTheSame(oldItem: AnimeInfo, newItem: AnimeInfo): Boolean =
//                oldItem.id == newItem.id
//
//            override fun areContentsTheSame(oldItem: AnimeInfo, newItem: AnimeInfo): Boolean =
//                oldItem == newItem
//        }
//    }
//
//    enum class BodyType {
//        RECOMMEND,
//        TRENDING,
//        ACTION_GENRE
//    }
//}