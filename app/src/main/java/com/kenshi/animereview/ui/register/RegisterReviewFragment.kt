package com.kenshi.animereview.ui.register


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kenshi.animereview.R
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.data.model.AnimeReview
import com.kenshi.animereview.databinding.FragmentRegisterReviewBinding
import com.kenshi.animereview.ui.anime_detail.AnimeDetailFragment.Companion.ANIME_REVIEW
import com.kenshi.animereview.ui.base.BaseFragment
import com.kenshi.animereview.ui.common.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterReviewFragment : BaseFragment<FragmentRegisterReviewBinding>(R.layout.fragment_register_review) {

    lateinit var animefullReview: AnimeReview

    companion object {
        const val ANIME_INFO = "animeInfo"
    }

    private val viewModel: RegisterReviewViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.apply {
            animefullReview = getParcelable(ANIME_REVIEW)!!
            binding.rbRegisterReview.rating = animefullReview.rating.toFloat()
        }

        bind {
            animeReview = animefullReview
            anime = animefullReview.anime
            vm = viewModel
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