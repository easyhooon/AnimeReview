package com.kenshi.animereview.ui.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kenshi.animereview.R
import com.kenshi.animereview.data.model.AnimeInfo
import com.kenshi.animereview.data.model.Review
import com.kenshi.animereview.databinding.FragmentRegisterReviewBinding
import com.kenshi.animereview.ui.MainViewModel
import com.kenshi.animereview.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterReviewFragment : BaseFragment<FragmentRegisterReviewBinding>(R.layout.fragment_register_review) {

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
            // 뒤로 가기
            findNavController().navigateUp()
        }
    }

    private fun initRatingBar() {
        binding.rbRegisterReview.setOnRatingBarChangeListener { _, rating, _ ->
            viewModel.setRating(rating)
        }
    }
}
