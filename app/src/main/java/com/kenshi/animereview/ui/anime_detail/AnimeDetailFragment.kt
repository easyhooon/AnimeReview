package com.kenshi.animereview.ui.anime_detail

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kenshi.animereview.R
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.data.model.Review
import com.kenshi.animereview.databinding.FragmentAnimeDetailBinding
import com.kenshi.animereview.ui.MainViewModel
import com.kenshi.animereview.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import com.kenshi.animereview.ui.common.EventObserver

@AndroidEntryPoint
class AnimeDetailFragment: BaseFragment<FragmentAnimeDetailBinding>(R.layout.fragment_anime_detail) {

    lateinit var animeInfo: AnimeInfo

    companion object {
        const val ANIME_INFO = "animeInfo"
    }

    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.apply {
            animeInfo = getParcelable(ANIME_INFO)!!
        }

        bind {
            anime = animeInfo
            vm = viewModel
        }

        initNavigation()
        initRatingBar()
    }


    private fun initNavigation() {
        binding.tbAnimeDetail.setOnClickListener {
            // TODO 뒤로 가기 Shoppi 참고 구현
            findNavController().navigateUp()
        }

        viewModel.openRegisterReviewEvent.observe(viewLifecycleOwner, EventObserver {
            openRegisterReview()
        })
    }

    private fun openRegisterReview() {
        viewModel.setAnimeInfo(animeInfo)
        findNavController().navigate(R.id.action_animeDetailFragment_to_registerReviewFragment)
    }

    private fun initRatingBar() {
        binding.rbAnimeDetail.setOnRatingBarChangeListener { _, rating, _ ->
            viewModel.setRating(rating)
        }
    }
}