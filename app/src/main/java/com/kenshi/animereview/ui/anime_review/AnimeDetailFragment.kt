package com.kenshi.animereview.ui.anime_review

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kenshi.animereview.R
import com.kenshi.animereview.databinding.FragmentAnimeDetailBinding
import com.kenshi.animereview.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import com.kenshi.animereview.ui.common.EventObserver

@AndroidEntryPoint
class AnimeDetailFragment: BaseFragment<FragmentAnimeDetailBinding>(R.layout.fragment_anime_detail) {

    private val viewModel: AnimeReviewViewModel by activityViewModels()

    private val animeReviewAdapter: AnimeReviewAdapter by lazy { AnimeReviewAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel.setAnimeInfo(viewModel.animeInfo.value)

        bind {
            anime = viewModel.animeInfo
            reviewAdapter = animeReviewAdapter
            vm = viewModel
        }

        initNavigation()
        initRatingBar()
    }


    private fun initNavigation() {
        binding.tbAnimeDetail.setOnClickListener{
            if(!findNavController().navigateUp())
                requireActivity().finish()
            else
                findNavController().navigateUp()
        }

        viewModel.openRegisterReviewEvent.observe(viewLifecycleOwner, EventObserver {
            openRegisterReview()
        })
    }

    private fun openRegisterReview() {
        findNavController().navigate(R.id.action_animeDetailFragment_to_registerReviewFragment)
    }

    private fun initRatingBar() {
        binding.rbAnimeDetail.setOnRatingBarChangeListener { _, rating, _ ->
            viewModel.setRating(rating)
        }
    }
}