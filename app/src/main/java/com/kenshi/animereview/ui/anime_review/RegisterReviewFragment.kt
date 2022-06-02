package com.kenshi.animereview.ui.anime_review

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kenshi.animereview.R
import com.kenshi.animereview.databinding.FragmentRegisterReviewBinding
import com.kenshi.animereview.ui.base.BaseFragment
import com.kenshi.animereview.ui.common.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterReviewFragment : BaseFragment<FragmentRegisterReviewBinding>(R.layout.fragment_register_review) {

    private val viewModel: AnimeReviewViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind {
            anime = viewModel.animeInfo.value
            vm = viewModel
        }
        initNavigation()
        initRatingBar()
    }

    private fun initNavigation() {
        binding.tbRegisterReview.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.returnHomeEvent.observe(viewLifecycleOwner, EventObserver {
            returnHome()
        })
    }

    private fun returnHome() {
        showToast("리뷰가 등록되었습니다")
        viewModel.resetReviewText()
        requireActivity().finish()
    }

    private fun initRatingBar() {
        binding.rbRegisterReview.setOnRatingBarChangeListener { _, rating, _ ->
            viewModel.setRating(rating)
        }
    }
}
