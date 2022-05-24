package com.kenshi.animereview.ui.anime_detail

import android.media.Rating
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kenshi.animereview.R
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.data.model.AnimeReview
import com.kenshi.animereview.databinding.FragmentAnimeDetailBinding
import com.kenshi.animereview.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import com.kenshi.animereview.ui.common.EventObserver

@AndroidEntryPoint
class AnimeDetailFragment: BaseFragment<FragmentAnimeDetailBinding>(R.layout.fragment_anime_detail) {

    lateinit var animeInfo: AnimeInfo
    lateinit var animeReviewWithRating: AnimeReview
    var rate = 0

    companion object {
        const val ANIME_INFO = "animeInfo"
        const val ANIME_REVIEW = "animeReview"
        const val RATING = "rating"
    }

    private val viewModel: AnimeDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.apply {
            animeInfo = getParcelable(ANIME_INFO)!!
        }

        animeReviewWithRating = AnimeReview(animeInfo , rate, "")

        bind {
            vm = viewModel
            anime = animeInfo
            animeReview = animeReviewWithRating
        }

        initNavigation()
        initRatingBar()
    }


    private fun initNavigation() {
        binding.tbAnimeDetail.setOnClickListener {
            // TODO 뒤로 가기 Shoppi 참고 구현
            findNavController().navigateUp()
        }
        // 이러한 코드가 데이터바인딩을 쓰면 필요가 없다는데 음..
        viewModel.openRegisterReviewEvent.observe(viewLifecycleOwner, EventObserver { anime ->
            openRegisterReview(anime)
        })
    }

    private fun openRegisterReview(animeReview: AnimeReview) {
        findNavController().navigate(
            //data 는 bundle 객체에 담아 보냄
            R.id.action_animeDetailFragment_to_registerReviewFragment, bundleOf(
                //"key" to "value"
                ANIME_REVIEW to animeReview,
            )
        )
    }

    private fun initRatingBar() {
        binding.rbAnimeDetail.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            animeReviewWithRating.rating = rating.toInt()
        }
    }
}