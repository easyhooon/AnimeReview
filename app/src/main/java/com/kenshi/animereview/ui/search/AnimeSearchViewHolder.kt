package com.kenshi.animereview.ui.search

import androidx.recyclerview.widget.RecyclerView
import com.kenshi.animereview.data.model.jikan.AnimeInfo
import com.kenshi.animereview.databinding.ItemAnimePreviewBinding

class AnimeSearchViewHolder(
    private val binding: ItemAnimePreviewBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(anime: AnimeInfo) {
        // 좌우 꺽쇄 날림
//        val author = book.authors.toString().removeSurrounding("[", "]")
//        val publisher = book.publisher
//        // nullable 하기 때문에 방어코드 작성
//        val date = if (book.datetime.isNotEmpty()) book.datetime.substring(0, 10) else ""

//        itemView.apply {
//            binding.apply {
//                ivArticleImage.load(book.thumbnail)
//                tvTitle.text = book.title
//                tvAuthor.text = "$author | $publisher"
//                tvDatetime.text = date
//            }
//        }

    }
}