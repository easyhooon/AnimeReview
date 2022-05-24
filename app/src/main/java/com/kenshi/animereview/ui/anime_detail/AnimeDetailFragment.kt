package com.kenshi.animereview.ui.anime_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kenshi.animereview.R
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.databinding.FragmentAnimeDetailBinding
import com.kenshi.animereview.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeDetailFragment: BaseFragment<FragmentAnimeDetailBinding>(R.layout.fragment_anime_detail) {

    lateinit var animeInfo: AnimeInfo

    companion object {
        const val ANIME_INFO = "animeInfo"
    }

    private val viewModel: AnimeDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.apply {
            animeInfo = getParcelable(ANIME_INFO)!!
        }

        bind {
            vm = viewModel
            anime = animeInfo
        }

        initNavigation()
    }


    private fun initNavigation() {
        binding.tbAnimeDetail.setOnClickListener {
            // 뒤로 가기
            findNavController().navigateUp()
        }
    }
}