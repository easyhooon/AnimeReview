package com.kenshi.animereview.ui.anime_detail

import android.os.Bundle
import android.util.Log
import android.view.View
import com.kenshi.animereview.R
import com.kenshi.animereview.databinding.FragmentAnimeDetailBinding
import com.kenshi.animereview.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AnimeDetailFragment: BaseFragment<FragmentAnimeDetailBinding>(R.layout.fragment_anime_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag("onCreate").d("DetailFragment")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.tag("onViewCreated").d("DetailFragment:%s", this.hashCode())
    }
}